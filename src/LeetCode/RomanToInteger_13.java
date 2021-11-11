package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger_13 {
    Map<Character, Integer> map = new HashMap<>();  // hoặc static và static block

    RomanToInteger_13() {
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);
    }

    public int romanToInt(String s) {
        if (s == null || s.length() == 0) return -1;

        char[] chars = s.toCharArray();
        int n = chars.length;
        int result = map.get(chars[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            if (map.get(chars[i]) < map.get(chars[i + 1])) {
                result -= map.get(chars[i]);
            } else {
                result += map.get(chars[i]);
            }
        }
        return result;
    }

    // Cách 2: Thay vì sử dụng map ta sử dụng switch từng kí tự (tiết kiệm bộ nhớ và nhanh hơn từ 5->3ms)
    public int romanToInt1(String s) {
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
        System.out.println(new RomanToInteger_13().romanToInt("MD"));
    }
}
