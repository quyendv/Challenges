/*
    https://leetcode.com/problems/powx-n/
    solve pow (x, n):    note n is -2^31 -> 2^31 - 1
*/

package LeetCode;

public class _50_Pow {
    // best algorithm -> n can be negative number -> logN
    public static double myPow(double x, int n) {
        if (n == 0) return 1;
        double temp = myPow(x, n / 2);  // divide and conquer algorithm
        // Note: vì return luôn cần ít nhất 2 lần myPow(x, y / 2) nên cần lưu vào temp để tránh tính lại 2 lần
        if (n % 2 == 0) return temp * temp;

        // else n is odd
        if (n > 0) return temp * temp * x;
        return temp * temp * 1 / x;
    }

    public static void main(String[] args) {
        System.out.println(myPow(594238, -5));
    }
}
