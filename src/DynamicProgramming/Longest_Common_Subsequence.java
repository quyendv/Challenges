// https://www.youtube.com/watch?v=NnD96abizww
// Link Solution: dưới video

// Tìm độ dài xâu con chung k liên tiếp dài nhất: abcdef & abcf -> abcf (4)

/*

    if (input[i] == input[j]) dp[i][j] = dp[i - 1][j - 1] + 1
    else dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]  // tức mà max của s1.pop_back với s2 và s1 với s2.pop_back

    // Lưu ý index giảm 1 so với CT trên bảng, bỏ qua i, j == 0 trên bảng vì mặc định = 0 rồi.

    In sẽ lần ngược về. từ dp[s1.length][s2.length]: https://www.youtube.com/watch?v=VNdLaLBLYkw&t=635s
 */

package DynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

public class Longest_Common_Subsequence {    // (LCS)

    // có cách khác là dùng đệ quy, đỡ phải dùng mảng 2D nhưng khó trong việc in lại LCS đó (đệ quy có nhớ thì bảng ngược với cách này)
    public static int LCS(char[] s1, char[] s2) {
        int[][] dp = new int[s1.length + 1][s2.length + 1];

        for (int i = 1; i <= s1.length; i++) {
            for (int j = 1; j <= s2.length; j++) {
                if (s1[i - 1] == s2[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // In sẽ lần ngược về. từ dp[s1.length][s2.length]: https://www.youtube.com/watch?v=VNdLaLBLYkw&t=635s
        int lenLCS = dp[s1.length][s2.length];
        char[] result = new char[dp[s1.length][s2.length]];
        int i = s1.length, j = s2.length;   // i, j là dòng, cột trong bảng LCS thôi, index vẫn trừ 1
        while (i > 0 && j > 0) {
            if (s1[i - 1] == s2[j - 1]) {
                result[--lenLCS] = s1[i - 1];
                i--;
                j--;
            }
            else if (dp[i - 1][j] > dp[i][j - 1]) i--;
            else j--;
        }

        System.out.println(Arrays.toString(result));
        // len of result
        return dp[s1.length][s2.length];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next(), s2 = sc.next();
        System.out.println(LCS(s1.toCharArray(), s2.toCharArray()));

        // abacdgh && abcdhg -> abcdh or abcdg nhưng sẽ chỉ in đc 1 LCS thôi
    }
}
