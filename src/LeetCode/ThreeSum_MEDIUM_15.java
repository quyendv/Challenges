// https://leetcode.com/problems/3sum/
// Bài toán in ra TẤT CẢ các bộ 3 có tổng bằng 0. Lưu ý không trùng lặp --> dạng khó nhất phải k nhỉ? :>
/*
    Sẽ đi từ 2Sum: https://www.geeksforgeeks.org/given-an-array-a-and-a-number-x-check-for-pair-in-a-with-sum-as-x/
    + Cách O(N^2): for 2 lần
    + Cách O(NlogN): sort (NlogN), tiếp đó ta có thể duyệt bằng nhiều cách: dùng 2 con trỏ sẽ là O(n), dùng binarySearch sẽ là O(NlogN)
    + Cách O(N): dùng set, duyệt từng phần tử a[i] ktra xem có -a[i] chưa

    Với 3Sum: https://www.geeksforgeeks.org/find-a-triplet-that-sum-to-a-given-value/
    + Cách O(N^3): for 3 lần
    + Cách sx:
        sort (NlogN)
        for 1 lần duyệt từng a[i], tiếp đó đến việc của 2Sum tìm 2 số có tổng bằng -a[i]
        -> Lưu ý: dùng HashSet sẽ khá nhanh, tuy nhiên bộ nhớ tốn rất nhiều vì mỗi a[i] phải tạo 1 Set mới nhưng time : O(N^2)
                  dùng 2 binarySearch sẽ đưa bài toán về O(N^2.logN)
                  dùng 2 con trỏ sẽ tốt nhất với O(N^2) khi đảm bảo đc bộ nhớ tối ưu

    ==> Như vậy 2Sum nên dùng Set còn 3Sum nên dùng 2 con trỏ (two pointers technique)
 */

package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum_MEDIUM_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // sort của java đã tối ưu rất nhiều

        // find
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;      // CỰC KỲ LƯU Ý ĐIỀU NÀY, GIẢM TGIAN ĐI RẤT NHIỀU, VÀ CHỈ VỚI BỘ 3 TỔNG BẰNG 0 MỚI ĐÚNG
            if (i > 0 && nums[i] == nums[i - 1]) continue;   // không xét trùng lặp

            // solve 2Sum: sum = -a[i] from [i+1, a.length - 1]
            int lo = i + 1, hi = nums.length - 1, sum = -nums[i];
            while (lo < hi) {
                if(nums[lo] + nums[hi] == sum) {
                    ans.add(List.of(nums[i], nums[lo], nums[hi])); // or ans.add(Arrays.asList(nums[i], nums[lo], nums[hi]))0;
                    while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                    while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                    lo++; hi--;     // đừng quên dòng này
                } else if (nums[lo] + nums[hi] < sum) {
                    lo++;
                }
                else {
                    hi--;
                }
            }
        }
        return ans;
    }
}
