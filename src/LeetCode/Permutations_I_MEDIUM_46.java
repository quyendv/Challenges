/*
    https://leetcode.com/problems/permutations/
    Trả về danh sách tất cả các hoán vị từ 1 mảng theo bất kì thứ tự nào!

    https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
    <Xem thêm 1 số dạng bài trong link trên về backtracking>
 */

package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Permutations_I_MEDIUM_46 {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        boolean[] marked = new boolean[nums.length];
        Integer[] auxiliary = new Integer[nums.length];

        helper(nums, auxiliary, res, marked, 0);

        return res;
    }

    public static void helper(int[] original, Integer[] auxiliary, List<List<Integer>> res, boolean[] marked, int indexElement) {
        if (indexElement == original.length) {
            res.add(List.of(auxiliary));
            // res.add(Arrays.asList(auxiliary)); // sai vì copy nông --> phải chuyển thành res.add(new ArrayList(Arrays.asList(auxiliary)))
            return;
        }

        for (int i = 0; i < original.length; i++) {
            if (!marked[i]) {
                marked[i] = true;
                auxiliary[indexElement] = original[i];
                helper(original, auxiliary, res, marked, indexElement + 1);
                marked[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        List<List<Integer>> res = permute(arr);
        for (var i : res) {
            System.out.println(i);
        }
    }
}
