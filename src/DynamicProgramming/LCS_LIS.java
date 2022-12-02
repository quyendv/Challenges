// Bài toán tìm xâu con chung của 2 xâu nhưng tăng dài nhất. (Chỉ cần in số kí tự của xâu đó)
// Lưu ý: xâu con chung dài nhất (LCS) chắc chắn chứa xâu con chung tăng dài nhất ==> chỉ cần tìm được LCS rồi tính LIS trên xâu đó

package DynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

public class LCS_LIS {
    public static char[] LCS(char[] s1, char[] s2) {
        int[][] dp = new int[s1.length + 1][s2.length + 1];

        for (int i = 1; i <= s1.length; i++) {
            for (int j = 1; j <= s2.length; j++) {
                if (s1[i - 1] == s2[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int lenLCS = dp[s1.length][s2.length];
        char[] result = new char[dp[s1.length][s2.length]];
        int i = s1.length, j = s2.length;
        while (i > 0 && j > 0) {
            if (s1[i - 1] == s2[j - 1]) {
                result[--lenLCS] = s1[i - 1];
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) i--;
            else j--;
        }

        return result;
    }

    public static int LIS(char[] arr) {
        int ans = -1;
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next(), s2 = sc.next();
        System.out.println(LIS(LCS(s1.toCharArray(), s2.toCharArray())));
    }
}
