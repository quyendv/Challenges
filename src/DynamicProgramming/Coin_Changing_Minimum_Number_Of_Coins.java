// Solution1(bottom-up): https://www.youtube.com/watch?v=Y0ZqKpToTic (xem tham khảo thôi)
// Solution2(top-down) : https://www.youtube.com/watch?v=NJuKJ8sasGk nâng cấp mảng 1 chiều
// Link Solution: dưới video

// Both: https://www.youtube.com/watch?v=jgiZlGzXMBw, code: https://www.geeksforgeeks.org/coin-change-dp-7/

/*
    Bài toán: Cho 1 số tiền, mà các loại tiền với mệnh giá khác nhau. Tìm số tờ tiền tối thiểu rút ra từ các loại mệnh giá
    trên cho đủ số tiền đưa ra ban đầu. (Với điều kiện số lượng tờ tiền các mệnh giá vô hạn)

    Gần giống với knapsack: <knapsack unbounded>
    if (j < coin[i]) dp[i][j] = dp[i - 1][j]        // can't get
    else
        dp[i][j] = min(dp[i - 1][j], 1 + dp[i][j - coin[i]])   // min(get, don't get). Để ý nếu lấy sẽ khác so với bài knapsack
    ==> NHƯNG: ta chỉ hiểu pp bài toán là thế (cài đặt khác, vì nhiều vấn đề cho i == 0, ...
    ==> ta sẽ gộp các hàng của bảng thành 1 hàng [total + 1] duy nhất, và mỗi lần duyệt i ta ghi đè trực tiếp vào nó
 */

package DynamicProgramming;

import java.util.Arrays;

public class Coin_Changing_Minimum_Number_Of_Coins {
    // Solution 1: bottom-up
    public static int minimumCoin(int total, int[] coins) {
        int[] dp = new int[total + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1); // trừ 1 để có thể +1 lên k bị tràn
        dp[0] = 0;

        for (int i = 0; i < coins.length; i++) {        // vẫn duyệt như bảng
            for (int j = 1; j <= total; j++) {          // từ 0 vẫn được, nhưng thừa. Chuẩn nhất là từ coins[i] luôn
                if (j >= coins[i]) {                    // can get
                    dp[j] = Math.min(dp[j - coins[i]] + 1, dp[j]);  // min (get, don't get): thực chất dp[j] chính là dp[i - 1][j] k đổi
                }
                // else: can't get: bằng hàng trước, mà hàng trước chính là ô hiện tại nên k đổi (k cần xét)
            }
        }
        return dp[total];
    }

    public static int minimumCoinUpdate(int total, int[] coins) {
        int[] dp = new int[total + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1); // còn cải tiến nữa trên GFG, k cần fill bằng Max_value - 1 => tiết kiệm thêm O(n)
        dp[0] = 0;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= total; j++) {
                dp[j] = Math.min(dp[j - coins[i]] + 1, dp[j]);
            }
        }
        return dp[total];
    }

    /**
     * Bài toán tìm số lượng xu tối thiểu để tạo ra 1 giá trị Value, nếu không return -1. (Gần giống bài trên nhưng bài trên là quy đổi,
     * tức luôn có kết quả quy đổi, bài này có thể k). VD: value: 15, coins = {4}
     * Solution: https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
     *
     * If V == 0, then 0 coins required.
     * If V > 0     (V < 0 thì nhỏ hơn mọi coins[i] luôn rồi)
     *    minCoins(coins[0..m-1], V) = min {1 + minCoins(V-coin[i])}
     *                                where i varies from 0 to m-1
     *                                and coin[i] <= V
     */

    public static int findMinNumberOfCoinsThatMakeAGivenValue(int value, int[] coins) {
        int[] table = new int[value + 1];
        table[0] = 0;
        for (int i = 1; i < value + 1; i++) table[i] = Integer.MAX_VALUE;

        for (int i = 1; i <= value; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    int sub_res = table[i - coins[j]];
                    if (sub_res != Integer.MAX_VALUE && sub_res + 1 < table[i]) table[i] = sub_res + 1;
                }
            }
        }
        return table[value] == Integer.MAX_VALUE ? -1 : table[value];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 3, 5};
        int total = 10;
        System.out.println(minimumCoin(total, coins));  // 2 -> 5, 5
        total = 11;
        System.out.println(minimumCoin(total, coins));  // 3 -> 5 5 1
        total = 7;
        System.out.println(minimumCoin(total, coins));  // 2 -> 2 5

        System.out.println(findMinNumberOfCoinsThatMakeAGivenValue(15, new int[]{4}));
    }
}
