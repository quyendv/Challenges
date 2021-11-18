// https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2481/Share-my-O(log(min(mn)))-solution-with-explanation
// Video: https://www.youtube.com/watch?v=LPFhl65R7ww
/* ********************************************************************
 * Giải thích chi tiết: O(log(min(lengthX, lengthY)))
 * Ta xét 2 mảng X (bên trên) và Y (bên dưới). Tìm điểm partition X (với length x < length y) thỏa mãn maxLeft <= mỉnight
 *
 * + partition(x or y) là cách chia sao cho nửa bên trái có partirion(x or y) phần tử. VD partitionX = 2 tức chia bên trái 2 phần tử.
 * --> partitionX [0, lengthX] && partitionY ... hay có lengthX + 1 cách chia mảng X
 *
 * + Ý tưởng là khi combine 2 mảng lại thành 1 mảng sắp xếp thì median là điểm chính giữa (hoặc average 2 điểm ở giữa) --> ta chia
 * thành 2 phần là partLeft và partRight với partLeft.length >= partRight.length (giống bài findRunningMedian với 2 PriorityQueue: luôn
 * ưu tiên maxHeap hơn) --> nếu size của partLeft là (x + y + 1) / 2  <cộng 1 để chẵn hay lẻ thì left luôn >= right)
 *
 * + Tại sao lengthY cần >= lengthX: vì biểu thức partitionY cần thế, nếu không partitionY có thể âm
 *
 * + Các đk if nên xem video và hình dung sẽ rõ (khó giải thích)
 ******************************************************************** */

package LeetCode;

public class Median_of_Two_Sorted_Arrays_4_HARD {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);

        int x = nums1.length, y = nums2.length;
        int low = 0;    // low & high là giới hạn của partitionX [0, lengthX]
        int high = x;   // low & high là giới hạn của partitionX [0, lengthX]

        while (low <= high) {
            int partitionX = low + (high - low) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            int maxLeftX = partitionX == 0 ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = partitionX == x ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = partitionY == 0 ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = partitionY == y ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((x + y) % 2 == 0) return average(Math.max(maxLeftX, maxLeftY), Math.min(minRightX, minRightY));
                else return Math.max(maxLeftX, maxLeftY) * 1.0;
            } else if (maxLeftX > minRightY) {
                high = partitionX - 1;
            } else { //maxLeftY > minRightX
                low = partitionX + 1;
            }
        }

        // tới đây chưa return tức k có, k tồn tại --> throw Ex or return specialNumber.
        throw new IllegalArgumentException("Not found.");
    }

    private double average(int a, int b) {
        return (a + b) / 2.0;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7};
        int[] b = {2, 4, 6};
        System.out.println(new Median_of_Two_Sorted_Arrays_4_HARD().findMedianSortedArrays(a, b));
    }
}
