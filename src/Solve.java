import java.util.*;

public class Solve {
    // https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};

        if (nums[0] == target && nums[nums.length - 1] == target) return new int[]{0, nums.length - 1};

        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                int[] res = new int[2];

                int i = mid;
                while (i > 0 && nums[i] == nums[i - 1]) i--;
                res[0] = i;

                i = mid;
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
                res[1] = i;

                return res;
            }
            if (target < nums[mid]) hi = mid - 1;
            else lo = mid + 1;
        }

        return new int[]{-1, -1};
    }

    // https://leetcode.com/problems/contains-duplicate-ii/
    // Lưu ý dùng Window Sliding
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (Math.abs(i - map.get(nums[i])) <= k) return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() == 0) return false;
        if (word.length() == 1) return true;

        for (int i = 1; i < word.length(); i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                if (Character.isLowerCase(word.charAt(i - 1))) return false;
            } else {
                if (i > 1 && Character.isUpperCase(word.charAt(i - 1))) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        int[] a = {1, 2, 3, 3, 3, 5};
//        System.out.println(Arrays.toString(searchRange(a, 3)));
//        System.out.println(Arrays.toString(searchRange(a, 2)));
//        System.out.println(Arrays.toString(searchRange(a, 6)));
        System.out.println(containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1));
    }
}
