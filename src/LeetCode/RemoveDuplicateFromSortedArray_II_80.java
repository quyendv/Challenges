// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
// video https://www.youtube.com/watch?v=WsYkpq3mQvU&list=PLyiioioEJSxGl_AEyiKSAeoeH_0-GvV4b&index=8
// solution discuss https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/discuss/27976/3-6-easy-lines-C%2B%2B-Java-Python-Ruby

package LeetCode;

public class RemoveDuplicateFromSortedArray_II_80 {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) { // or forEach
            if (i < 2 || nums[i - 2] < nums[j])
            {
                nums[i] = nums[j];  // or [i++]
                i++;
            }
        }
        return i;

        // optimize
        /*
        if (nums.length <= 2) return nums.length;
        int i = 2;
        for (int j = 2; j < nums.length; j++) {
            if (nums[i - 2] < nums[j])
            {
                nums[i++] = nums[j];
            }
        }
        return i;
        */
    }
}
