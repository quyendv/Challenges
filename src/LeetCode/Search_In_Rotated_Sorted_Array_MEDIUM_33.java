/*
    https://leetcode.com/problems/search-in-rotated-sorted-array/
    Cho mảng gồm các số riêng biệt, đc sx tăng dần. Sau đó xoay mảng tại index k bất kì [1, arr.length)
    tạo thành mảng (a[k], a[k + 1], a[k + 2], ... a[n - 1], a[0], a[1], ... a[k - 1])
    VD: 1 2 3 4 5, k = 2 ==> 3 4 5 1 2 (giống dịch trái 2 vị trí, hoặc xoay mảng 3 lần với k = 2 - thầy Long)

    --> Yêu cầu: cho mảng sau khi xoay. Cho số nguyên target. Tìm vị trí (index) của target trong mảng sau xoay. Nếu k có trả về -1
    nums = [4,5,6,7,0,1,2], target = 0 -> return 4
                            target = 3 -> return -1
    --> Hướng giải: https://leetcode.com/problems/search-in-rotated-sorted-array/discuss/14425/Concise-O(log-N)-Binary-search-solution
             video: https://www.youtube.com/watch?v=QdVrY3stDD4
    - Cách 1:
    + Tìm k-index
    + search với việc mở rộng mảng ra, hoặc có thể lọc ra part cần search luôn k cần expansion

    - Cách 2: không cần tìm k-index https://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/
 */

package LeetCode;

public class Search_In_Rotated_Sorted_Array_MEDIUM_33 {
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        // find k-index: phần này khó hiểu quá
        int n = nums.length;
        int lo = 0, hi = n - 1;
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else hi = mid;
        }
        int k = lo;     // lo = hi = pivot
        lo = 0; hi = n - 1;

        // binary search find target với cách mở rộng mảng. Ta cũng có thể làm cách khác bằng việc lọc ra target sẽ ở phần nào
//        while(lo <= hi) {
//            int mid = (lo + hi) / 2;
//            int readMid = (mid + k) % n;
//            if (target == nums[readMid]) return readMid;
//            if (target < nums[readMid]) hi = mid - 1;
//            else lo = mid + 1;
//        }

        if (target >= nums[k] && target <= nums[n - 1]) lo = k; // phần trước
        else hi = k - 1; // thuộc phần còn lại
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (target == nums[mid]) return mid;
            if (target < nums[mid]) hi = mid - 1;
            else lo = mid + 1;
        }
        return -1;
    }

    // Cách 2:
    public static int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int lo = 0, hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return mid;

            // left part is sorted
            if (nums[lo] <= nums[mid]) {
                if (target >= nums[lo] && target <= nums[mid]) hi = mid - 1;
                else lo = mid + 1;
            }
            // right part is sorted
            else {
                if (target >= nums[mid] && target <= nums[hi]) lo = mid + 1;
                else hi = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(search(a, 0));
        System.out.println(search(a, 3));
    }
}
