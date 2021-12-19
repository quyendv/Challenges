// https://www.youtube.com/watch?v=BysNXJHzCEs
// Link solution: dưới mô tả video

// Độ dài xâu con chung liên tiếp dài nhất <k phổ biến bằng xâu con chung k liên tiếp dài nhất>
// Vẫn bảng 2 chiều như knapsack và lưu ý tương tự: trên bảng là từ 1 nhưng index thực tế từ 0

// if (input[i] == input[j]) dp[i][j] = dp[i-1][j - 1] + 1; else dp[i][j] = 0. Lưu ý index sẽ trừ 1 so với bảng
// Gần giống với LCS, nếu cần in ra kết quả chỉ cần lùi i, j về dần từ dp[i][j] = len


package DynamicProgramming;

import java.util.Scanner;

public class Longest_Common_Substring {

    // O(n1 * n2)
    public static int longestCommonSubstring(char[] s1, char[] s2) {
        int[][] dp = new int[s1.length + 1][s2.length + 1];
        int max = 0;

        // có thể bỏ qua i, j = 0 vì cũng chỉ gán nó = 0 ==> có thể bỏ qua, bài knapsack cũng như vậy
        for (int i = 1; i <= s1.length; i++) {
            for (int j = 1; j <= s2.length; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
                // else dp[i][j] = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next(), s2 = sc.next();
        System.out.println(longestCommonSubstring(s1.toCharArray(), s2.toCharArray()));
    }
}
