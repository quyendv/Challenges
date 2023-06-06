package LeetCode.Stack;

public class _1963_Minimum_Number_of_Swaps_to_Make_the_String_Balanced {
    /**
     * - Xem thêm 921. (để hiểu cơ chế tính invalidClose và invalidOpen) và 1249.
     * - Bài này có 1 cách khác để tính invalidOpen
     * Xem solution most vote. Cách làm hình dung việc bỏ hết các cặp đúng đi
     * - Với mỗi dấu ] mà chưa có dấu [ đủ cho nó thì là điểm cần swap
     * - Cách để minimum swap là swap cặp dấu '[' và ']' ở tận cùng 2 đầu thay vì từ giữa
     * VD: ]] [[ Thay vì ] [] [ (đổi cặp ở giữa) rồi đổi tiếp cặp ở giữa còn lại [] (cặp được thì hình dung bỏ nó đi). Thì ta đổi cặp cuối cùng chỉ 1 lần, sẽ được [[]]
     * - Với cách swap các cặp ngoặc cuối như vậy ta có minSwap = mismatches / 2 (xem ví dụ trong solution)
     * - Ngoài ra còn có cách tiếp cận 2 con trỏ: https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/solutions/1390811/clear-explanation-c-greedy-detailed-explanation/
     */
    public int minSwaps(String s) {
        if (s.length() < 2 || s.length() % 2 == 1) return -1;

        int unmatches = 0; // invalidOpen bằng stackSize
        for (char c : s.toCharArray()) {
            if (c == '[') unmatches++;
            else {
                if (unmatches > 0) unmatches--;
            }
        }
        return (unmatches + 1) / 2;
    }
}
