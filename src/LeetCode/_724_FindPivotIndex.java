package LeetCode;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/find-the-middle-index-in-array/
public class _724_FindPivotIndex {
    public static int findMiddleIndex(int[] a) {
//    // Cách 1: tạo mảng sum[i] = a[0] + ... + a[i] -> so sánh sum[i - 1] == sum[n-1] - sum[i] <tổng trái và tổng phải a[i] return i;
//        int n = a.length;
//        int[] sum = new int[n];
//        for (int i = 0; i < n; i++)
//            sum[i] = i == 0 ? a[i] : sum[i - 1] + a[i];
//        for (int i = 0; i < n; i++) {
//            if ((sum[i] - a[i]) == (sum[n - 1] - sum[i])) return i;
//        }
//        return -1;

        /*
         * Cách 2: sử dụng leftSum (k bao gồm a[i])
         *  leftSum = rightSum - nums [i] && leftSum + rightSum = total
         *  -> leftSum = (totalSum - leftSum) - nums [i] -> leftSum * 2 = totalSum-nums [i]
         */

        int total = 0, leftSum = 0;
        for (int i : a) total += i;
        for (int i = 0; i < a.length; leftSum += a[i++]) {
            if (leftSum * 2 == total - a[i]) return i;
        }
        return -1;

        /* Cách 3: dùng Map: ý tưởng gần giống cách 2
           + duyệt từ đầu đến cuối mảng để tính leftSum
           + với mỗi a[i], putIfAbsent(leftSum * 2 + a[i], i): tức là ta coi mỗi a[i] là 1  pivot của 1 dãy số, và total
           mỗi dãy số đó phải bằng leftSum * 2 + a[i] và lưu chỉ số i của mỗi pivot đó. Lưu ý phải dùng putIfAbsent
           thay vì put. Bởi dãy input có thể có nhiều pivot thỏa mãn nhưng chỉ lấy pivot trái cùng (tức là phần tử đc
           put trước) VD: 1 2 4 -1 1 0 3 có 2 pivot có val là 4 và 0 nhưng index là 2 và 5 -> chỉ lấy 2, 5 k thêm vào nữa
           + hết vòng lặp leftSum == total dãy input -> return getOrDefault(leftSum, -1) : vì ta cần pivot trái cùng khi dãy
           có tổng là total ==> có total -> index đã lưu
           ==> nói dài khó hiểu thì nhìn code rồi tự ngẫm ra!
        */
//         int leftSum = 0;
//         Map<Integer, Integer> m = new HashMap<>();
//         for (int i = 0; i < a.length; leftSum += a[i++]) {
//             m.putIfAbsent(leftSum * 2 + a[i], i);
//         }
//         return m.getOrDefault(leftSum, -1);
    }

    public static void main(String[] args) {
        int[] a = {2, 1, -1};
        System.out.println(findMiddleIndex(a));
    }
}
