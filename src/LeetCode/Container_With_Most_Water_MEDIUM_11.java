// https://leetcode.com/problems/container-with-most-water/
// Có solution ở trong bài đó rồi.

/*
    Tóm tắt: cho 1 mảng height, xác định diện tích lớn nhất (xem trong link rõ hơn, giống bài Trapping_Rain_Water)
 */

package LeetCode;

public class Container_With_Most_Water_MEDIUM_11 {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                maxArea = Math.max(maxArea, height[left] * (right - left));
                left++;
            } else {
                maxArea = Math.max(maxArea, height[right] * (right - left));
                right--;
            }
        }
        return maxArea;
    }
}
