import java.util.Scanner;
import java.util.Stack;

public class Solution3 {
    public static boolean check(String s) {
        if (s == null) return false;
        if (s.length() < 2) return false;
        Stack<Character> stk = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '{') {
                if (!stk.empty() && (stk.peek() == ']' || stk.peek() == ')')) return false;
                stk.push('}');
            } else if (c == '[') {
                if (!stk.empty() && stk.peek() == ')') return false;
                stk.push(']');
            } else if (c == '(') {
                stk.push(')');
            } else if (!stk.empty() && c != stk.pop()) return false;
        }
        return stk.empty();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        if (check(s)) System.out.println("VALID");
        else System.out.println("INVALID");
    }
}
