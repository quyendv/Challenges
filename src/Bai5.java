import java.util.Scanner;

public class Bai5 {
    public static int solve(int m, int n, int[] w) {
        int[][] dp = new int[w.length + 1][m + 1];

        for (int i = 0; i <= w.length; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;
                else if (j < w[i - 1]) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j - w[i - 1]] + w[i - 1], dp[i - 1][j]);
            }
        }

        return dp[w.length][m];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) w[i] = sc.nextInt();

        System.out.println(solve(m, n, w));
    }
}
