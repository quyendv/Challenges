// https://leetcode.com/problems/valid-palindrome/
// check string có phải palindrome hay không, với upper vẫn bằng lower, và bỏ qua kí tự
// "A man, a plan, a canal: Panama" -> true

package LeetCode;

public class ValidPalindrome_125 {
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        char[] S = s.toCharArray();
        for (int i = 0, j = S.length - 1; i < j; i++, j--) {
            while (!Character.isLetterOrDigit(S[j]) && j > i) j--;
            while (!Character.isLetterOrDigit(S[i]) && i < j) i++;
            if (Character.toLowerCase(S[i]) != Character.toLowerCase(S[j])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(".,")); // TH rất đặc biệt
    }
}
