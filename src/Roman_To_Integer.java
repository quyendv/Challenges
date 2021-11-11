// Cho số La Mã, trả về số nguyên tương ứng
// https://algorithms.tutorialhorizon.com/convert-roman-number-to-integer/

import java.util.HashMap;
import java.util.Map;

public class Roman_To_Integer {
    public static int convert(String roman) {
        if (roman == null || roman.length() == 0) return -1;

        char[] s = roman.toCharArray(); // char[] cho nhanh hon
        int n = s.length;

        // nên cho map và init vào thuộc tính của lớp, chứ k phải convert lại khởi tạo lại
        Map<Character, Integer> map = new HashMap<>();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);        // I V X L C D M

        int result = map.get(s[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            if (map.get(s[i]) < map.get(s[i + 1])) { // IV
                result -= map.get(s[i]);
            } else { // >=. luu y = thi cong
                result += map.get(s[i]);
            }
        }
        return result;
    }

    // Cách 2: sử dụng switch thay vì Map (tiết kiệm bộ nhớ và nhanh hơn), time O(n), space O(1)
    public int convert1(String s) {
        if (s == null || s.length() == 0) return -1;
        char[] chars = s.toCharArray();
        int n = chars.length;
        int result = values(chars[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            if (values(chars[i]) < values(chars[i + 1])) result -= values(chars[i]);
            else result += values(chars[i]);
        }
        return result;
    }

    private int values(char c) {
        switch (c) {
            case 'M':
                return 1000;
            case 'D':
                return 500;
            case 'C':
                return 100;
            case 'L':
                return 50;
            case 'X':
                return 10;
            case 'V':
                return 5;
            case 'I':
                return 1;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(convert("MDCLXVI"));
    }
}
