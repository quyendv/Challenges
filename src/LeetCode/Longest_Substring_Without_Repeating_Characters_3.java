// Tham khảo GFG: https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/

// https://leetcode.com/problems/longest-substring-without-repeating-characters/
// solution from discuss: https://leetcode.com/problems/longest-substring-without-repeating-characters/discuss/1729/11-line-simple-Java-solution-O(n)-with-explanation
// -> cùng chung ý tưởng là lưu lastIndex của các kí tự (bằng mảng or hashMap)

package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Longest_Substring_Without_Repeating_Characters_3 {
    // Solution 1: from GFG, k nên vì k thấy hiệu quả lắm, mặc dù ý tưởng giống cách dưới
    public static int longestUniqueSubstr(String str) {
        String test = "";
        // Result
        int maxLength = -1;

        // Return zero if string is empty
        if (str.isEmpty()) {
            return 0;
        }
        // Return one if string length is one
        else if (str.length() == 1) {
            return 1;
        }
        for (char c : str.toCharArray()) {
            String current = String.valueOf(c);

            // If string already contains the character
            // Then substring after repeating character
            if (test.contains(current)) {
                test = test.substring(test.indexOf(current)
                        + 1);
            }
            test = test + String.valueOf(c);
            maxLength = Math.max(test.length(), maxLength);
        }
        return maxLength;
    }

    // Solution 2: discuss, ý tưởng tương tự method 4.1 trong GFG.Nhưng HashMap sẽ không tốn O(n) để fill -1 vào và mảng có thể k dùng hết
    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        if (s.length() == 1) return 1;

        Map<Character, Integer> map = new HashMap<>();
        int max = 0;        // maxLength of substrings
        int start = 0;          // start: startIndex of current window
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                start = Math.max(start, map.get(c) + 1); // tức kí tự lặp lại nằm sau startIndex mới phải cập nhật start
            }
            // start = Math.max(start, map.getOrDefault(c, -1) + 1); // có thể thay if trên bằng lệnh nè -> chậm hơn vì lấy max nhiều lần
            map.put(c, end);
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    public int recode(String s) {
//        if (s == null || s.length() == 0) return 0;
//        StringBuilder test = new StringBuilder(); // stringBuilder luôn nhanh hơn String
//        int max = 0;
//        for (char c : s.toCharArray()) {
//            if (test.toString().contains(c + "")) {
//                test = new StringBuilder(test.substring(test.toString().indexOf(c) + 1));
//            }
//            test.append(c);
//            max = Math.max(max, test.length());
//        }
//        return max;

//        if (s == null || s.length() == 0) return 0;
//        Map<Character, Integer> map = new HashMap<>();
//        int max = 0;
//        int start = 0; // startWindow
//        for (int end = 0; end < s.length(); end++) {
//            char c = s.charAt(end);
//            // start = Math.max(start, map.getOrDefault(c, -1) + 1);
//            if (map.containsKey(c)) {
//                start = Math.max(start, map.get(c) + 1);
//            }
//            map.put(c, end);
//            max = Math.max(max, end - start + 1);
//        }
//        return max;

        // using arrays: nhanh nhất.

        // if (s == null || s.length() == 0) return 0;
        // int[] lastIndex = new int[128];
        // Arrays.fill(lastIndex, -1);
        // int max = 0, start = 0;
        // for (int end = 0; end < s.length(); end++) {
        //     char c = s.charAt(end);
        //     start = Math.max(start, lastIndex[c] + 1);
        //     max = Math.max(max, end - start + 1);
        //     lastIndex[c] = end;
        // }
        // return max;

        // best: k cần O(n) để fill và hạn chế tính length()
        Integer[] lastIndex = new Integer[128];
        int max = 0, start = 0, end = 0, n = s.length();
        while (end < n) {
            char c = s.charAt(end);
            // if (lastIndex[c] != null && lastIndex[c] >= start) start = lastIndex[c] + 1;
            Integer index = lastIndex[c];
            if (index != null && index >= start) start = index + 1;
            max = Math.max(max, end - start + 1);
            lastIndex[c] = end;
            end++;
        }
        return max;
    }

}
