// Khác bài toán Window_Sliding_Technique (tính dãy con liên tiếp có tổng dài nhất)
// Gần giống bài D:\IntelliJ\Challenges\src\LeetCode\Maximum_SubArray_53.java nhưng bài này cần dãy tăng nữa


// https://www.youtube.com/watch?v=99ssGWhLPUE
// https://www.geeksforgeeks.org/maximum-sum-increasing-subsequence-dp-14/

package DynamicProgramming;

public class Maximum_Sum_Increasing_Subsequence {
    public static int MSIS(int[] arr) {
        // int[] dp = arr.clone(); // O(n)

        int[] dp = new int[arr.length];
        int[] indexBeforeValue = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i] = arr[i];
            indexBeforeValue[i] = i;
        }

        int max = dp[0]; // result
        int maxIndex = 0;   // nếu dùng maxIndex có thể bỏ max đi

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {  // dãy con tăng
                    if (dp[j] + arr[i] > dp[i]) {   // tổng lớn hơn
                        dp[i] = dp[j] + arr[i];     // dp[i] = Math.max(dp[j] + arr[i], dp[i]);
                        indexBeforeValue[i] = j;
                    }
                }
            }

            // max = Math.max(max, dp[i]);
            if (dp[i] > max) {
                max = dp[i];
                maxIndex = i;
            }
        }

        // print reverse
        for (int i = maxIndex; ; i = indexBeforeValue[i]) {
            System.out.print(arr[i] + " ");
            if (i == indexBeforeValue[i]) break;    // ktra điều kiện sau khi in, ktra trước (trong đk for) sẽ mất số đầu
        }
        System.out.println();

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1, 101, 2, 3, 100, 4, 5};
        System.out.println(MSIS(arr)); // 1 2 3 100 -> 106
    }
}
