// https://leetcode.com/problems/trapping-rain-water/
// Mô tả xem kĩ trong link trên. Tính lượng nước trũng

package LeetCode;

public class Trapping_Rain_Water_42_HARD {

    // Tham khảo solution: comment đầu tiên trong link:
    // https://leetcode.com/problems/trapping-rain-water/discuss/17391/Share-my-short-solution.
    public int trap(int[] height) {
        int water = 0;
        int left = 0, right = height.length - 1;
        int maxLeft = 0, maxRight = 0;
        while (left < right) {
            maxLeft = Math.max(maxLeft, height[left]);
            maxRight = Math.max(maxRight, height[right]);
            if (maxLeft <= maxRight) {
                water += maxLeft - height[left];
                left++;
            } else {
                water += maxRight - height[right];
                right--;
            }
        }
        return water;
    }


    // optimize: https://www.youtube.com/watch?v=E0-Vy6g5wYg&list=PLyiioioEJSxGl_AEyiKSAeoeH_0-GvV4b&index=11
    // giảm số lần lấy max đi, chỉ lấy max 1 lần mỗi vòng lặp thay vì cả 2. Nhưng cần cách trên để hiểu
    public int trap1(int[] height) {
        int water = 0;
        int left = 0, right = height.length - 1;
        int maxLeft = height[left], maxRight = height[right];
        while (left < right) {
            if (maxLeft <= maxRight) {
                water += maxLeft - height[left];
                left++;
                maxLeft = Math.max(maxLeft, height[left]);
            } else {
                water += maxRight - height[right];
                right--;
                maxRight = Math.max(maxRight, height[right]);
            }
        }
        return water;
    }
}
