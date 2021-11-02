/*
    https://leetcode.com/problems/largest-number-at-least-twice-of-others/
    // Cho mảng số nguyên, ktra xem có tồn tại 1 số lớn nhất trong mảng mà lớn hơn các phần tử còn lại ít nhất 2 lần k?
    // -> check max1 >= ma2 * 2 --> return index of max1 or -1
*/
package LeetCode;

public class _747_Largest_Number_At_Least_Twice_of_Others {
    public int dominantIndex(int[] a) {
        if (a.length == 1) return 0;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, maxIndex = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max1) {
                max2 = max1; max1 = a[i];
                maxIndex = i;
            } else if (a[i] > max2) {
                max2 = a[i];
            }
        }
        return (max1 >= max2 * 2) ? maxIndex : -1;  // cực cù lưu ý không dùng max1 / max2 >= 2 vì max2 có thể = 0
    }
}
