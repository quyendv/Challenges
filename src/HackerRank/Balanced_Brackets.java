// https://www.hackerrank.com/challenges/balanced-brackets/problem
// giống _20_ Leetcode

package HackerRank;

import java.util.Stack;

public class Balanced_Brackets {
    public static String isBalanced(String s) {
        Stack<Character> stk = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stk.push(')');
            } else if (c == '{') {
                stk.push('}');
            } else if (c == '[') {
                stk.push(']');
            } else if (stk.empty() || c != stk.pop()) {
                return "NO";
            }
        }
        return stk.empty() ? "YES" : "NO";
    }
}
