import java.util.Arrays;
import java.util.Scanner;

public class Bai3 {
    public static int minimumCoinUpdate(int total, int[] coins) {
        int[] dp = new int[total + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= total; j++) {
                dp[j] = Math.min(dp[j - coins[i]] + 1, dp[j]);
            }
        }
        return dp[total];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5, 10, 20, 50};
        Scanner sc = new Scanner(System.in);
        int value = sc.nextInt();
        System.out.println(minimumCoinUpdate(value, coins));
    }
}