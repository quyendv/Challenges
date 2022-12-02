package LeetCode;
/*
    https://leetcode.com/problems/sort-array-by-parity-ii/
    sắp xếp mảng để phần tử chẵn ở index chẵn, lẻ ở index lẻ
    -> two pointer
 */

public class _922_Sort_Array_By_Parity_II {
    public int[] sortArrayByParityII(int[] nums) {
        int n = nums.length;
        int i = 0, j = 1; // ta cần 2 con trỏ, 1 cái index chẵn, 1 cái index lẻ để duyệt
                          // k nên dùng j = n - 1 vì k phải xét j chẵn lẻ...
        while (i < n && j < n) {
            while (i < n && nums[i] % 2 == 0) i += 2;         // i chẵn và a[i] chẵn -> đúng -> next 2
            while (j < n && nums[j] % 2 == 1) j += 2;         // j lẻ và a[j] lẻ     -> đúng -> next 2
            if (i < n && j < n)                               // nếu a[i] và a[j] đều sai thì swap
                nums[i] += nums[j] - (nums[j] = nums[i]);
        }
        return nums;
    }

    public static void main(String[] args) {

    }
}
