// https://www.hackerrank.com/challenges/missing-numbers/problem
/*
    Cho 2 list arr brr. Tìm các giá trị có trong B nhưng k có trong A (hoặc có trong A nhưng xuất hiện ít hơn) THEO THỨ TỰ SX
        max(brr) - min(brr) <= 100
        len_arr <= len_brr
        1 <= brr_i <= 10^4
    VD: arr 1 1 2 5 6 7     ==> arr có thừa 7 mà brr k có thì cũng k quan tâm
        brr 1 1 1 2 4 3 5 6 ==> dư 1 4 3 ==> return {1, 3, 4}
    - Cách 1: Vì kết quả in ra theo thứ tự sx của brr nên dùng TreeSet or TreeMap cho brr (tránh phải sort(result_List)
    + Duyệt qua brr ta ++ số lần xuất hiện
    + Duyệt qua arr và trừ -- số lần xuất hiện CHỈ các số có trong brr (các số khác của arr k quan tâm) nếu như freq > 1. else remove
    + Các số còn lại trong Map là kết quả -> add vào result.
    https://www.youtube.com/watch?v=1g3WmjMG2OM&t=612s
    (ta tính freq của brr trước vì ta chỉ xét đến các số trong brr thôi, k put thêm các value khác của arr)
    --> có thể dùng HashMap thôi và đi từ min đến max của B để add như cách dùng mảng
    --> ta có thể làm tương tự thay map bằng 1 mảng rồi duyệt từ minValue đến maxValue của brr rồi add vào result. (tuy nhiên nhiều ô mảng
        thừa gây thất thoát bộ nhớ)

    - Cách 2: https://www.hackerrank.com/rest/contests/master/challenges/missing-numbers/hackers/brucardoso2/download_solution
                <có thể làm trực tiếp trong main để giảm tgian thực hiện>
    + dùng hashMap để tính tần số các số trong arr (k cần tree vì k cần theo thứ tự của arr)
    + dùng treeSet(rỗng) (vì ta cần thứ tự). sau đó duyệt từng phần tử của brr: Nếu có trong hashMap của arr thì giảm freq, else add vào Set.
    + các số trong Set chính là kết quả luôn (đã theo thứ tự)

    - Cách 3: Dùng 2 mảng lưu tần số các giá trị của arr, brr. Sau đó duyệt từ minValue đến maxValue của brr (vì cần theo thứ tự) và so
    sánh freq của mảng B so với mảng A: https://www.hackerrank.com/challenges/missing-numbers/editorial
    --> tuy nhiên cách này giống cách biến thể dùng mảng ở cách 1 và tốn thêm mảng phụ thứ 2
 */
package HackerRank;

import java.util.*;

public class MissingNumbers {
    // O(n2.log(n2)), n2 = len_brr
    public static List<Integer> missingNumbers1(List<Integer> arr, List<Integer> brr) { // cũng có thể duyệt min -> max như cách 2
        // Map<Integer, Integer> mapA = new TreeMap<>() {{
        //     for (int i : brr) {
        //         if (!containsKey(i)) put(i, 1);
        //         else put(i, get(i) + 1);
        //     }
        // }};

        Map<Integer, Integer> map = new TreeMap<>();
        for (int i : brr) {
            // map.compute(i, (k, v) -> v == null ? 1 : v + 1);
            map.put(i, map.getOrDefault(i, 0) + 1);     // O(n2.log(n2))
        }

        for (int i : arr) {                                        // O(n1)
            if (map.containsKey(i)) {       // chỉ xét phần tử có trong B
                if (map.get(i) > 1) map.put(i, map.get(i) - 1);
                else map.remove(i);
            }
        }
        List<Integer> ans = new ArrayList<>();                     // O(map.size())
        for (Map.Entry<Integer, Integer> i : map.entrySet()) ans.add(i.getKey());
        return ans;
    }

    // O(n1 + n2)
    public static List<Integer> missingNumbers2(List<Integer> arr, List<Integer> brr) {
        int[] frequency = new int[10001];
        for (int i : arr) frequency[i]--;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i : brr) {
            frequency[i]++;
            if (i < min) min = i;
            if (i > max) max = i;
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (frequency[i] >= 1) ans.add(i);
        }
        return ans;
    }

    public static List<Integer> missingNumbers3(List<Integer> arr, List<Integer> brr) { // tốn bộ nhớ hơn cách 2: k nên dùng
        int[] frequencyA = new int[10001];
        int[] frequencyB = new int[10001];
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i : arr) frequencyA[i]++;
        for (int i : brr) {
            frequencyB[i]++;
            if (i < min) min = i;
            if (i > max) max = i;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (frequencyB[i] > frequencyA[i]) ans.add(i);      // > thôi chứ k >= vì tránh số k xuất hiện thì freq == 0
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len1 = sc.nextInt();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < len1; i++) arr.add(sc.nextInt());

        int len2 = sc.nextInt();
        List<Integer> brr = new ArrayList<>();
        for (int i = 0; i < len2; i++) brr.add(sc.nextInt());

        List<Integer> result = missingNumbers1(arr, brr);
        for (int i : result) System.out.print(i + " ");
    }
}
