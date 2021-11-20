// https://leetcode.com/problems/reverse-integer/
// https://leetcode.com/problems/reverse-integer/discuss/4060/My-accepted-15-lines-of-code-for-Java
// Xem thêm cách 2: ở comment đầu link trên.
/*
    Đảo ngược 1 số int. VD: 123 -> 321, -123 -> -321, ... cái quan trọng nhất là tràn số: 2^31 - 1, ...  --reverse--> sẽ tràn số
 */

package LeetCode;

public class Reverse_Integer_7_Median {
    public int reverse(int x) {
        int result = 0;
        int temp = 0; // Nhân thử xem có tràn k, nếu tràn return 0 (giữ lại result để compare)
        while (x != 0) { // thường quen tay đk là x > 0. Nhưng ta CHỈ CẦN != là đủ, nếu thêm > thì x âm sẽ k chạy --> lưu ý x != 0
            temp = result * 10 + x % 10;
            if ((temp - x % 10) / 10 != result) return 0; // điều kiện này dựa vào quy luật tràn số. INT_MAX + 1 --> INT_MIN, ...
            result = temp;
            x /= 10;
        }
        return result;
    }
}
