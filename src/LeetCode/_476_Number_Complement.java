// https://leetcode.com/problems/number-complement/

package LeetCode;

import static java.lang.Integer.*;
import static java.lang.Integer.toBinaryString;

public class _476_Number_Complement {
    /*
        Cho số nguyên, viết hàm trả về yêu cầu cần tìm. VD: 10 (1010) trả về số có dạng nhị phần là 0101 hay 101 (số 5)
        Lưu ý: nhiệm vụ ta cần là chuyển 1010 thành 0101 chứ không phải 0000 1010 -> 1111 0101
        --> cần xor 0000 1010 với 0000 1111         (mask ^ num)
                hoặc ~num (1111 0101) & 0000 1111   (mask & ~num)
                hoặc 1111 0101 & 0000 0111          (newMask & ~num)
    */
    // https://leetcode.com/problems/number-complement/discuss/96017/3-line-C%2B%2B
    public int findComplement(int num) {
        int mask = ~0;
        while ((mask & num) != 0) {
            mask <<= 1;
        }
        return ~mask ^ num; // mask ~= mask --> return mask ^ num hoặc mask & ~num
    }

    // https://leetcode.com/problems/number-complement/discuss/95992/Java-1-line-bit-manipulation-solution
    public int findComplement1(int num) {
        // int mask = (Integer.highestOneBit(num) << 1) - 1;
        // return mask ^ num;  // or return mask & ~num;
        int mask = Integer.highestOneBit(num) - 1;  // newMask: 111 thay vì 1111 -> khi & ~num sẽ tương đương
        return mask & ~num;
    }

    public static void main(String[] args) {
        // highestBitOne trả về số nguyên (k phải binary) có dạng binary chứa duy nhất 1 bit 1 ở vị trí cao nhất của num
        //  101 -> 100, 111000 -> 100000, ...

        System.out.println(Integer.parseInt("1010", 2) + "\n");  // chuyển số sang số nguyên từ string dạng binary
        System.out.println(toBinaryString(5));
        System.out.println(toBinaryString(highestOneBit(5)) + "\n");

        System.out.println(toBinaryString(10));
        System.out.println(toBinaryString(highestOneBit(10)));
        System.out.println(toBinaryString(highestOneBit(10) << 1));
        System.out.println(toBinaryString((highestOneBit(10) << 1) - 1));  // mẹo tạo mask rất hay
        System.out.println(toBinaryString(highestOneBit(10) - 1));

    }
}
