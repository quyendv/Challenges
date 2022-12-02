/*
    https://leetcode.com/problems/permutations-ii/
    Permutation1 thì k có phần tử trùng lặp, nên có thể dùng 1 mảng boolean để ktra đã lấy phần tử đó chưa. Còn ở bài này có thể
    chứa duplicates nên k thể dùng cách đó. Tóm lại với nums = {1, 1, 1} thì ta sẽ không tạo cả 6 hoán vị của các số 1

    - Cách 1 (tự nghĩ): đề bài có giới hạn nums[i] từ [-10, 10] -> ta có thể dùng mảng 21 phần tử lưu tần số (với tần số của -1 -> -10 là
    freq[11] -> freq[20]). Hoặc có thể dùng HashMap sẽ tốt hơn. Tuy nhiên cách này sẽ khá lâu vì chưa tối ưu, do phụ thuộc vào bài này
    khoảng cách giữa min, max của nums nhỏ, chứ nó lớn thì bước helper chạy lâu (k còn -10 đến 10)
    --> cách dùng mảng tự làm: (Bài nộp đầu tiên) https://leetcode.com/submissions/detail/622388147/
    --> Cải tiến là dùng Map: https://leetcode.com/problems/permutations-ii/solution/ (Nhưng tđn time chậm hơn nhiều so với mảng :<)

    - Cách 2: https://leetcode.com/problems/permutations-ii/discuss/18594/Really-easy-Java-solution-much-easier-than-the-solutions-with-very-high-vote
    Ý tưởng là giả sử có mảng nums[1a, 1b, 1c, 2] (a, b, c để phân biệt 3 số 1 với nhau). Việc ta làm là sẽ tạo các hoán vị trong đó 1a
    luôn đc chọn trước 1b và 1b trước 1c.
    1 1 1 2 (1a 1b 1c 2)
    1 1 2 1
    1 2 1 1 -> tại index 1 khi ta xét 1c thì 1b chưa lấy nên chuyển tiếp đến 2
    2 1 1 1
*/

package LeetCode;

import java.util.*;

public class Permutations_II_MEDIUM_47 {

    /* ********************************
     * Solution 1.1: Map
     * Time: O(nAk hoặc viết là P)?? Chỉnh hợp (A - sắp xếp k phần tử theo thứ tự nào đó, tổ hợp(C) là số tập con k phần tử và A = C.k!): n! / (n-k)!
     * Space: O(N)
     ******************************** */

    public static List<List<Integer>> permuteUnique1(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int val : nums) {
            // freq.put(val, freq.getOrDefault(val, 0) + 1);
            freq.compute(val, (k, v) -> v == null ? 1 : v + 1);
        }

        List<List<Integer>> res = new ArrayList<>();
        helper(res, new Integer[nums.length], freq, 0);
        return res;
    }

    public static void helper(List<List<Integer>> res, Integer[] auxiliary, Map<Integer, Integer> freq, int indexElement) {
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > 0) {
                freq.put(entry.getKey(), entry.getValue() - 1);  // freq--
                auxiliary[indexElement] = entry.getKey();

                if (indexElement == auxiliary.length - 1) {
                    res.add(List.of(auxiliary));
                } else {
                    helper(res, auxiliary, freq, indexElement + 1);
                }

                freq.put(entry.getKey(), entry.getValue() + 1);  // freq++
            }
        }
    }


    /* ********************************************
     * Solution 2 - Best Solution: sort, bỏ qua các phần tử trùng lặp
     * Xem thêm: https://youtu.be/nYFd7VHKyWQ
     ******************************************** */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        boolean[] marked = new boolean[nums.length];
        // List<Integer> auxiliary = new ArrayList<>();
        Integer[] auxiliary = new Integer[nums.length];

        dfs1(nums, result, auxiliary, marked, 0);
        // dfs(nums, result, auxiliary, marked);

        return result;
    }

    // ta có thể cải tiến auxiliary bằng List (add và remove), chỉ cần dùng .size() thay cho indexCurrElement
    public static void dfs1(int[] original, List<List<Integer>> result, Integer[] auxiliary, boolean[] marked, int indexCurrElement) {
        for (int i = 0; i < original.length; i++) {
            if (marked[i]) continue;
            if (i > 0 && original[i] == original[i - 1] && !marked[i - 1]) continue;

            // chọn
            marked[i] = true;
            auxiliary[indexCurrElement] = original[i];

            if (indexCurrElement == auxiliary.length - 1) {
                result.add(List.of(auxiliary));
            } else {
                dfs1(original, result, auxiliary, marked, indexCurrElement + 1);
            }

            marked[i] = false;
        }
    }

    // cải tiến dùng list (không chắc có nhanh hơn, vì vẫn copy list mới mà, hơn nữa ArrayList là mảng động đôi khi phải nới rộng
    // nên có thể sẽ lâu hơn)
    public static void dfs(int[] original, List<List<Integer>> result, List<Integer> auxiliary, boolean[] marked) {
        for (int i = 0; i < original.length; i++) {
            if (marked[i]) continue;
            if (i > 0 && original[i] == original[i - 1] && !marked[i - 1]) continue;

            // select
            marked[i] = true;
            auxiliary.add(original[i]);

            if (auxiliary.size() == original.length) {
                result.add(new ArrayList<>(auxiliary));
            } else {
                dfs(original, result, auxiliary, marked);
            }

            marked[i] = false;
            auxiliary.remove(auxiliary.size() - 1);
        }
    }


    public static void print(List<List<Integer>> res) {
        for (var i : res) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 3, 3, 1, 2};
        print(permuteUnique(a));
        // print(permuteUnique1(a));
    }
}
