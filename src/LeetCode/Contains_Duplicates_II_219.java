/*
    https://leetcode.com/problems/contains-duplicate-ii/
    < Xem thêm 217. Contains Duplicates: https://leetcode.com/problems/contains-duplicate. Ktra xem có duplicates không.
        Cách 1: 2 for -> O(N.N) time + O(1) space
        Cách 2: sort rồi compare theo cặp -> O(N.logN) time + O(1) space
        Cách 3: Set -> O(N) time + O(1) space -> không nên dùng 1 mảng boolean vì dải số có thể lớn
    >

    Đề bài: Cho mảng arr và số k. Tìm xem có cặp i, j thoả mãn arr[i] == arr[j] và |i - j| <= k.
    Điều lưu ý là tìm cặp i, j là chỉ số của nums. nên nếu ta sort rồi thực hiện trên mảng sorted sẽ sai

    -> Cách 1: dùng Map lưu arr[i] và index i. Duyệt qua mảng và ktra xem có tồn tại key chưa?, có thì xét abs ...
           ==> time O(n) nhưng vẫn khá chậm, space xấu nhất là O(n) - các key phân biệt hết thì map n phần tử

    -> Cách 2: sử dụng window sliding. Ta chỉ cần dùng 1 set chứa k phần tử từ [i - k] đến [i - 1] (tức có k số trước index i)
    để ktra xem arr[i] có trong đoạn [i - k, i - 1] chưa. Nếu có tức tồn tại duplicates thoả mãn khoảng cách k. Nếu i = k thì
    sẽ xét k số từ 0 đến k - 1. nếu i tăng lên thành k + 1 (i > k) thì bắt đầu remove phần tử để giữ set tối đa k phần tử trước i
           ==> time O(N), space O(k)
    VD minh hoạ: nums = {1, 2, 3, 4, 2} k = 3
        i = 0 nums[i] = 1 thì set rỗng
        i = 1 nums[i] = 2 thì set chứa 1, không chứa 2 nên thêm 2
        i = 2 nums[i] = 3 thì set chứa 1, 2 không chứa 3 nên thêm 3
        i = 3 nums[i] = 4 thì set chứa 1, 2, 3 không chứa 4 nên tiếp tục thêm 4 -> thành 1 2 3 4
        i = 4 nums[i] = 2 thì set trước hết loại nums[0] đi vì chỉ cần xét từ nums[1->3] thôi. set còn 2 3 4. lúc này chứa 2 nên true
 */

package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 1 2 3 1:   1 2 3 1
public class Contains_Duplicates_II_219 {
    // Cách 1: map
    public static boolean containsNearbyDuplicate1(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) return true;   // false thì cập nhật index, hay quên
            }
            map.put(nums[i], i);    // có key hay k, abs thoả mãn hay k thì đều phải cập nhật pair mới
        }
        return false;
    }

    // Cách 2: Set + window sliding
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) set.remove(nums[i - k - 1]);
            if (!set.add(nums[i])) return true;
        }
        return false;
    }
}
