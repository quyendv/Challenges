// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
// Tham khảo video: https://www.youtube.com/watch?v=bU-q1OJ0KWw
// Tham khảo solution ở discuss: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/discuss/14699/Clean-iterative-solution-with-two-binary-searches-(with-explanation)

/*
    Tìm vị trí đầu tiên và cuối cùng của 1 số trong sorted array. nếu k có thì return cặp -1, -1
    + Cách 1: BS đặc biệt, giống như tìm pivot (min) của sorted rotated array (discuss)
        Nếu a[mid] = target thì high = mid. (Thu hẹp khoảng lại, vì mid có thể là firstIndex)
        Nếu a[mid] < target thì low = mid + 1. Vì firstIndex nếu có sẽ bên phải mid
        Nếu a[mid] > target thì high = mid - 1. Vì firstIndex nếu có sẽ bên trái mid. Tuy nhiên ta có thể để high = mid vẫn được
        --> target <= a[mid] thì high = mid
            else low = mid + 1;

        TH lastIndex phải lưu ý mid = low + (high - low + 1) / 2 vì cần nghiêng phải mới đúng

    + Cách 2: BS bình thường. Ta dùng 1 biến index để lưu vị trí cần tìm
        Nếu a[mid] == target thì ta gán index = mid (vì có thể đây là firstIndex). rồi update high = mid - 1.
        Nếu a[mid] > target thì high = mid - 1
        Nếu a[mid] < target thì low = mid + 1;
        --> target <= a[mid] thì high = mid - 1. Nếu bằng thì update index
            else low = mid + 1;

        TH lastIndex tương tự thôi
 */
package LeetCode;

import java.util.Arrays;

public class Find_First_and_Last_Position_of_Element_in_Sorted_Array_MEDIUM_34 {
    public static int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums == null || nums.length == 0) return res;

        res[0] = findFirstIndex(nums, target);
        res[1] = findLastIndex(nums, target);
        return res;
    }

    public static int findFirstIndex(int[] arr, int target) {
        // int low = 0, high = arr.length - 1;
        // while (low < high) {
        //     int mid = low + (high - low) / 2;
        //     if (target <= arr[mid]) high = mid;
        //     else low = mid + 1;
        // }
        // return arr[low] == target ? low : -1;

        int low = 0, high = arr.length - 1;
        int firstIndex = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target <= arr[mid]) {
                if (target == arr[mid]) firstIndex = mid;
                high = mid - 1;
            } else low = mid + 1;
        }
        return firstIndex;
    }

    public static int findLastIndex(int[] arr, int target) {
        // int low = 0, high = arr.length - 1;
        // while (low < high) {
        //     int mid = low + (high - low + 1) / 2;
        //     if (arr[mid] <= target) low = mid;
        //     else high = mid - 1;
        // }
        // return arr[low] == target ? low : -1;

        int low = 0, high = arr.length - 1;
        int lastIndex = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= target) {
                if (arr[mid] == target) lastIndex = mid;
                low = mid + 1;
            } else high = mid - 1;
        }
        return lastIndex;
    }

    public static void main(String[] args) {
        int[] a = {0, 1, 1, 3, 4, 5};
        System.out.println(Arrays.toString(searchRange(a, 1)));
    }
}
