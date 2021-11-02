// https://www.hackerrank.com/challenges/new-year-chaos/problem
/*
    Có n người xếp hàng và được đánh số lần lượt từ 1 đến n (theo thứ tự từ trái qua phải với mảng). Mỗi người phía sau được phép hối lộ người phía trước để được
    đổi vị trí với người phía trước đó và tối đa 2 lần. (tức là người bên phải HỐI LỘ để đổi vị trí với người bên trái, và người bên trái ĐƯỢC NHẬN HỐI LỘ để nhận
    vị trí phía sau tức bên phải--> đặc biệt lưu ý giữa hối lộ và được hối lộ để hiểu rõ)
    -> Yêu cầu: Nếu ai hối lộ hơn 2 lần in ra "Too chaotic", còn k tính tổng số lần hối lộ
    ** Vẫn là bài toán a[i] = i + 1 và i = a[i] - 1 khi đúng vị trí **
*/
package HackerRank;

import java.util.Scanner;

public class New_Year_Chaos {
    /*      Clever Solution: Discussions: k cần swap gì cả, chỉ tính thôi
     * Duyệt từ đầu đến cuối mảng và kiểm tra 2 điều quan trọng nhất:
     * 1. người ở vị trí i đã hối lộ quá 2 lần chưa: nếu a[i] - (i + 1) > 2 thì đã quá 2 lần (so sánh a[i] với a[i] ban đầu(tức i + 1))
     * 2. vậy người ở vị trí i đã NHẬN HỐI LỘ bao nhiêu lần (tức là dịch về sau bao nhiêu lần). Ta k quan tâm người HỐI LỘ, ta chỉ để ý người NHẬN HỐI LỘ thôi vì số
     * lần hối lộ cũng bằng số lần được hối lộ nên chỉ cần xét 1 trong 2 -> xét 2 cho dễ
     *  <lưu ý vì chỉ xét số lần được hối lộ nên chỉ xét nhưng val nào nhỏ hơn i + 1 thôi, chứ > i + 1 thì nó là thằng hối lộ rồi>
     * -> chỉ cần kiểm tra phía trước nó có bao nhiêu số lớn hơn thì là số lần nhận hối lộ. Nhưng ktra từ đâu :D ?
     * -> ktra từ vị trí index - 1 của val hiện tại ở vị trí i đã xét đến vị trí i - 1. VD ta đang xét tại i có val = a[i] thì ta sẽ check từ (a[i] - 1) - 1
     * tới i - 1: Bởi vì mọi số phía sau ta xét k thể đi đến trước vị trí index-1 được (tức k thể về index -2, -3, ..)
     * VD: 1 2 3 4 5 hối lộ thành 2 1 5 3 4. Nếu ta xét i = 4 (cuối mảng) thì ta sẽ check từ j = 2 đến j = 3 xem a[j] > a[i] thì ans++
     * => bởi vì val = 4 thì index của nó là 3. Vậy nên số 5 (i = 4) chỉ có thể hối lộ 2 lần đế tới i = 2 tức index - 1 đó :D, nên ta chỉ xét từ index - 1 đến i - 1
     * thôi vì 5 đâu thể tới đc index -2, -3... <Xem hình minh họa ở đề bài để hiểu>
     * ==> lưu ý thêm Math.max(0, a[i] - 2) vì tránh trường hợp j âm. VD a[i] = 1 thì vị trí trước index đúng của nó là -1, nhưng ta sẽ chỉ chạy j từ 0
     */
    public static void solve(int[] a) {
        int ans = 0;
        for (int i = a.length - 1; i >= 0; i--)  {
            if (a[i] - (i + 1) > 2) {
                System.out.println("Too chaotic");
                return;
            }
            for (int j = Math.max(0, a[i] - 2); j < i; j++) {
                if (a[j] > a[i]) ans++;
            }
        }
        System.out.println(ans);
    }

    /*      Cách 2: Editorial. dễ hiểu hơn nhiều, nhưng phải swap
     * Cũng duyệt từ cuối mảng. với mỗi giá trị tại i, nếu a[i] != i + 1 thì ktra 2 TH
     * 1. a[i-1] == i + 1: tức là a[i-1] đã hối lộ để về phía trước -> cần đổi lại -> swap a[i-1] và a[i]
     * 2. a[i-2] == i + 1: tức là a[i-2] đã hối lộ 2 lần -> đưa về vị trí ban đầu: swap a[i - 2] với a[i - 1] và a[i - 1] với a[i]
     * -> chỉ cần ktra 2 TH này thôi vì k lên đc i - 3 đâu
     * 3. nếu k vào 2 TH đầu thì in "Too chaotic" + return;
     */
    public static void solve1 (int[] a) {
        int ans = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] == i + 1) continue;
            if (i - 1 >= 0 && a[i - 1] == i + 1) {
                a[i - 1] += a[i] - (a[i] = a[i - 1]);
                ans++;
            } else if (i - 2 >= 0 && a[i - 2] == i + 1) {
                a[i - 2] += a[i - 1] - (a[i - 1] = a[i - 2]);
                a[i - 1] += a[i] - (a[i] = a[i - 1]);
                ans += 2;
            } else {
                System.out.println("Too chaotic");
                return;
            }
        }
        System.out.println(ans);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            // solve(a);
            solve1(a);
        }
        sc.close();
    }
}
