//https://leetcode.com/problems/valid-parentheses/

package LeetCode.Stack;

import java.util.Stack;

public class _20_Valid_Parentheses {
    public static boolean isValid(String s) {
        Stack<Character> stk = new Stack<>();
        for (char i : s.toCharArray()) {
            if (i ==  '(') stk.push(')');
            else if (i == '{') stk.push('}');
            else if (i == '[') stk.push(']');
            else if (stk.empty() || i != stk.pop()) return false; // pop đã xóa rồi
        }
        return stk.empty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("{)"));
        System.out.println(isValid("([{(]}])"));
    }
}
