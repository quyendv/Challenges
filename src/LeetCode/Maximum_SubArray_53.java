// DP, link solution: https://leetcode.com/problems/maximum-subarray/discuss/20193/DP-solution-and-some-thoughts
// tìm dãy con liên tiếp có tổng lớn nhất, trả về tổng đó
// -> dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0). Khó giải thích quá :<, cứ đọc lại sẽ hiểu

package LeetCode;

public class Maximum_SubArray_53 {
    public int maxSubArray(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = arr[0];
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = arr[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    // tương tự ý tưởng nhưng gọn hơn, tối ưu hơn
    public int maxSubArray1(int[] nums) {
        int current = nums[0];
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            current = Math.max(nums[i], current + nums[i]);
            max = Math.max(max, current);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Maximum_SubArray_53().maxSubArray(new int[]{1, 5, 3, -7, 6}));
    }
}
