/*
    https://www.geeksforgeeks.org/java-program-for-dynamic-programming-set-10-0-1-knapsack-problem/
    
    Bài toán cái túi chứa được w (weight), n vật phẩm có giá trị V[i] và khối lượng W[i] (value, weight)
    Tính và in ra giá trị lớn nhất có thể lấy được sao cho chứa đủ trong cái túi.
    -> Cách giải xem chi tiết trong folder ảnh, video ...
    Dùng bảng 2 chiều: int[][] dp = new [n + 1][w + 1];
        if (i == 0 || j == 0) dp[i][j] = 0;
        else if (j < W[i]) dp[i][j] = dp[i - 1][j];                         // can't get item i
        else dp[i][j] = Math.max(V[i] + dp[i-1][j - W[i]], dp[i - 1][j]);   // max(get, don't get)

    ==> Đó là theo ý hiểu trên bảng, tuy nhiên khi làm ta sẽ throw exception vì V[] và W[] đánh số từ 0 chứ k phải 1
    nên ta giảm index của V[] và W[] đi 1
    -> Hay hiểu là thứ i nhưng index là i - 1
 */

package DynamicProgramming;

public class Knapsack {
    // time, space: O(n.w)
    // có thể bỏ qua i, j = 0 vì cũng chỉ gán nó = 0
    // Thử suy nghĩ gộp thành mảng 1 chiều như: Coin Changing Minimum Number Of Coins
    public static int knapsack(int n, int w, int[] Value, int[] Weight) {
        int[][] dp = new int[n + 1][w + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= w; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;
                else if (j < Weight[i - 1]) dp[i][j] = dp[i - 1][j];                                   // can't get item i
                else dp[i][j] = Math.max(Value[i - 1] + dp[i - 1][j - Weight[i - 1]], dp[i - 1][j]);   // max(get, don't get)
            }
        }
        return dp[n][w];
    }

    public static void main(String[] args) {
        int n = 5;
        int[] value = new int[]{1, 4, 3, 5, 6};
        int[] weight = new int[]{1, 3, 4, 5, 7};
        int W = 16;
        System.out.println(knapsack(n, W, value, weight));
        // lấy đồ thứ 1, 2, 4, 5 => value total: 16
    }
}
