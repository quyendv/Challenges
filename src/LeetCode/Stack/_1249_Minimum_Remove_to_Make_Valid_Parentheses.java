package LeetCode.Stack;

import java.util.Stack;

public class _1249_Minimum_Remove_to_Make_Valid_Parentheses {
    /**
     * Duyệt từ trái qua phải
     * - Dấu ')' mà không có '(' tương ứng phía trước tức phải remove
     * - Dấu '(' phải chờ đến cuối xem, nếu không có đủ ')' tương ứng thì cũng bị remove
     * -> Đặt một mảng char bằng string ban đầu, chỗ cần remove thì gán bằng '', cuối cùng join mảng đó lại để đc string result (join dùng for vs stringBuilder, không join như js được)
     */
    public static String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] strArray = s.toCharArray();

        // remove invalid close brackets -> remove ~ set char[i] = ''
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                stack.push(i);
            else if (c == ')') {
                if (stack.empty())
                    strArray[i] = Character.MIN_VALUE; // '' ~ empty char ~ \u0000
                else
                    stack.pop();
            }
        }

        // remove invalid open bracket(các dấu mở nhưng không có đóng)
        for (int i : stack) {
            strArray[i] = Character.MIN_VALUE;
        }

        // return new String(strArray); // still showing Character.MIN_VALUE = \u0000
        StringBuilder res = new StringBuilder();
        for (char c : strArray) {
            if (c != Character.MIN_VALUE)
                res.append(c);
        }
        return res.toString();
    }
}
