package LeetCode;

import java.util.*;

public class _448_FindAllNumbersDisappearednAnArray {
    // Cách 1: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/discuss/1261617/Javaor-Logic-Explained-in-Simple-English-or-O(n)-or-O(1)
    /* Ta quy ước tại chỉ số i thì có val là a[i] = i + 1
       -> nếu có val a[i] thì vị trí (chỉ số) đúng của val đó là i = a[i] - 1
       Ta duyệt mảng:
            + nếu tại chỉ số i, val a[i] == i + 1 tức là đã đúng vị trí -> xét vị trí tiếp (i++)
            + nếu tại chỉ số i, val a[i] != i + 1 nhưng a[a[i] - 1] == a[i] -> tức val tại i chưa đúng vị trí của nó
            nhưng vị trí đúng của nó đã xuất hiện 1 val bằng nó đúng vị trí ( VD số 3 sai vị trí nhưng vị trí đúng của
            nó (vị trí i = 2) đã có 1 số 3 đứng đó -> nó bị thừa) thì ta cũng next i++
            + Vậy nên: Khi val tại i chưa đúng vị trí và vị trí đúng chưa có phần tử nào bằng nó đứng đo -> ta đổi giá trị
            tại vị trí i (hiện tại) với a[i] - 1 (vị trí đúng)
    */
//    public static List<Integer> findDisappearedNumbers(int[] a) {
//        List<Integer> list = new LeetCode.LinkedList<Integer>();
//        for(int i = 0; i < a.length; i++) {
//            while(a[i] != i+1 && a[i] != a[a[i] -1]) {
//                swap(a, i , a[i]-1);
//            }
//        }
//        for(int i = 0; i < a.length; i++) {
//            if(a[i] != i+1) list.add(i+1);
//        }
//        return list;
//    }
//
//    private static void swap(int[] a, int i, int j) {
//        int temp = a[i]; a[i] = a[j]; a[j] = temp;
//    }

    // Cách 2: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/discuss/777978/Java-time-O(n)-space-O(1)
    /*  Gần giống cách trên: ta duyệt qua từng val của mảng:
        + Mỗi lần duyệt đến từng val, ta đánh dấu arr[val - 1] bằng âm của nó (đánh dấu _giá trị tại vị trí đúng_ của
        nó để biết rằng đã có số đúng (i + 1 hay val hiện tại) ở vị trí đó). VD ta duyệt đến số 2, thì sẽ đánh dấu giá
        trị tại i = 1 là -arr[1] -> để sau cùng chỉ cần kiểm tra tại vị trí nào val nó âm là số đã có
        + Như đã nói: ta duyệt lại mảng, tại vị trí i nào mà arr[i] âm tức là đã tồn tại số i + 1, còn nếu arr[i] dương
        tức là còn thiếu số i + 1 --> add (i + 1) vào list

        => khác biệt với cách trên là chỉ cần đánh dấu, k cần đổi về đúng vị trí -> O(n) -> nhanh hơn
    */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        for (int val : nums) {
            int absVal = Math.abs(val);  // vì có bước gán giá trị âm nên cần lấy dương lại để xét
            if (nums[absVal - 1] > 0) {
                nums[absVal - 1] *= -1;
            }
        }
        List<Integer> res = new ArrayList<>(); // ArrayList truy xuất tốt hơn, LeetCode.LinkedList thao tác (thêm, xóa,..) tốt hơn
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] arr = {4,3,2,7,8,2,3,1};
        System.out.println(findDisappearedNumbers(arr));
    }
}