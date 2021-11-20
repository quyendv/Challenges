// https://leetcode.com/problems/longest-palindromic-substring/
// có thể xem discuss hoặc video: https://www.youtube.com/watch?v=y2BD4MJqV20
/*
    Bài toán tìm xâu con liên tiếp là Palindrome dài nhất.
    + cách thường: tìm tất cả xâu con liên tiếp O(n) và ktra nó có phải palindrome (x O(n/2)) ==> O(n^3)
    + Giải thuật: với palindrome  'aba', 'aa' thì ta có 'XabaX' và 'XaaX' cũng là palindrome ==> kéo sang 2 bên
    + Ta tìm độ dài mỗi palindrome rồi từ i (ở nửa trái- <substr chẵn> hoặc giữa <substr lẻ>) suy ra start và end của substr đó
    --> 'racecar' thì i ở 'e', ta trừ i cho size/2 ra start và cộng i cho size/2 ra end
    --> 'abba' thì i ở 'b', ta trừ i cho (size - 1)/2 ra start và cộng size / 2 ra end.
    ==> start = i - (len - 1) / 2 và end = i + len / 2.
    TimeComplexity O(n^2) and SpaceComplexity O(1)
 */

package LeetCode;

public class Longest_Palindrome_Substring_5_Medium {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;

        int start = 0, end = 0; // điểm đầu, cuối string
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromMiddle(s, i, i);           // odd: racecar
            int len2 = expandFromMiddle(s, i, i + 1);  // even: abba
            int len = Math.max(len1, len2);
            if (end - start + 1 < len) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    // return length of substring.
    private int expandFromMiddle(String s, int left, int right) {  // or extend
        if (s == null || left > right) return 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;  // (right - 1) - (left + 1) + 1 = ...
    }
}
