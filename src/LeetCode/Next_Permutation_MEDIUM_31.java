/*
    https://leetcode.com/problems/next-permutation/
    Link giải kèm thuật toán trên wikipedia: https://leetcode.com/problems/next-permutation/discuss/13867/C%2B%2B-from-Wikipedia
    <đọc thuật toán sẽ hiểu>

    Đề: tạo hoán vị tiếp theo theo thứ tự từ vựng. VD:[1, 2, 3, 4] -> next permutation: 1, 2, 4, 3 -> next 1, 3, 2, 4 -> ...
    Nếu không thể thì tạo thứ tự thấp nhất: VD 4, 3, 2, 1 -> 1, 2, 3, 4  (reverse lại đấy :>)
    --> Lưu ý yêu cầu là thay đổi mảng input, k tạo mảng phụ, k yêu cầu in ra kết quả (để main lo)

   Tóm tắt thuật toán:
   + Tìm k lớn nhất thoả mãn a[k] < a[k + 1]. Nếu không tồn tại thì nó là hoán vị lớn nhất rồi (còn theo đề bài kia thì reverse lại thấp nhất)
   + Tìm l lớn nhất thoả mãn a[k] < a[l]
   + swap a[k], a[l]
   + reverse mảng từ [k + 1, a.length) <từ k + 1 đến cuối mảng>
 */

/*
    Làm tiếp: https://leetcode.com/problems/permutations/
              https://leetcode.com/problems/permutations-ii/
 */

package LeetCode;

import java.util.Arrays;

public class Next_Permutation_MEDIUM_31 {
    // độ phức tạp là O(3*n) = O(n)
    public static void nextPermutation(int[] nums) {
        // length = 1: ... k quan tâm vì mình chỉ cần thay đổi nums, k cần in -> k ảnh hưởng

        int k = -1, l = 0;

        // find k
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                k = i;
                break;
            }
        }

        // k tồn tại k-index: reverse lại
        if (k == -1) {
            reverse(nums, 0, nums.length);
            return;
        }

        // find l
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > nums[k]) {
                l = i;
                break;
            }
        }

        swap(nums, k, l);
        reverse(nums, k + 1, nums.length); // reverse [start, end)
    }

    public static void swap(int[] nums, int i, int j) {
        nums[i] += nums[j] - (nums[j] = nums[i]);
    }

    public static void reverse(int[] nums, int start, int end) {
        for (int i = start, j = end - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        nextPermutation(a);
        System.out.println(Arrays.toString(a));

        nextPermutation(a);
        System.out.println(Arrays.toString(a));

        nextPermutation(a);
        System.out.println(Arrays.toString(a));

        nextPermutation(a);
        System.out.println(Arrays.toString(a));

        nextPermutation(a);
        System.out.println(Arrays.toString(a));

        nextPermutation(a);
        System.out.println(Arrays.toString(a));

        nextPermutation(a);
        System.out.println(Arrays.toString(a));

        int[] b = {4, 3, 2, 1};
        nextPermutation(b);
        System.out.println(Arrays.toString(b));
    }
}
