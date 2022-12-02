package LeetCode;// https://leetcode.com/problems/string-to-integer-atoi/

public class String_To_Integer_Atoi_8 {
    public static int myAtoi(String s) {

        char[] arr = s.toCharArray();
        int i = 0, sign = 1, result = 0, n = arr.length;
        // spaces
        while (i < n && arr[i] == ' ') {
            i++;
        }
        if (i >= n) return 0;

        // sign
        if (arr[i] == '-' || arr[i] == '+') {
            if (arr[i] == '-') sign = -1;
            i++;
        }

        // convert
        while (i < n) {
            int digit = arr[i] - '0';
            if (digit < 0 || digit > 9) break;

            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }
        return result * sign;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("            -15          "));
        System.out.println(myAtoi("1847297285792357923573948"));
    }
}
