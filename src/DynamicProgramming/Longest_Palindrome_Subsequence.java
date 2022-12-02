// Tushar Roy: https://www.youtube.com/watch?v=_nCsPn7_OgI&t=29s
// Link solution dưới video
/*
    Tìm độ dài xâu Palindrome dài nhất của 1 string. (Xem thêm bài Longest_Palindrome_Substring ở leetcode)
    ý tưởng: Dùng DP với i, j là vị trí bắt đầu, kết thúc của palindrome đang xét.
    + Nếu s[i] == s[j] thì DP[i][j] = dp[i + 1][j - 1] + 2 (tức là B...B sẽ bằng 2 + dp của xâu bên trong bao bởi 2 kí tự B)
    + Else: sẽ là max (dp[i + 1][j], dp[i][j - 1]), (tức là max của 2 xâu bên trong từ [i, j-1] và [i + 1, j]
        -> Lưu ý TH lenLPS = 2;
 */

package DynamicProgramming;

public class Longest_Palindrome_Subsequence {
    public static int LPS(char[] s) {
        int[][] dp = new int[s.length][s.length];

        // LPS length = 1 từ [i, i] sẽ là 1
        for (int i = 0; i < s.length; i++) dp[i][i] = 1;

        // other case: length >= 2
        for (int length = 2; length <= s.length; length++) {
            for (int i = 0; i <= s.length - length; i++) { // từ 0 đến i last
                int j = i + length - 1;
                if (length == 2 && s[i] == s[j]) {
                    dp[i][j] = 2;
                } else if (s[i] == s[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(LPS("agbdba".toCharArray()));    // abdba
    }
}
