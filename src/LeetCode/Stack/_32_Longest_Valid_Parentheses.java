/*
 * Hard
 * https://leetcode.com/problems/longest-valid-parentheses/
 * Discuss<stack>: https://leetcode.com/problems/longest-valid-parentheses/discuss/14167/Simple-JAVA-solution-O(n)-time-one-stack
 * Solution<4 cách>: https://leetcode.com/problems/longest-valid-parentheses/solution/
 */
/*
Trả về số lượng dấu ngoặc của chuỗi ngoặc hợp lệ liên tiếp dài nhất: (), ()(), (())(), ...
        <sai: ()((), ())(()), ...>
()()()()()() là hợp lệ -> return 12
((((())))) là hợp lệ -> return 10
()(()) -> return 6; ())(()) -> return 4
 */
package LeetCode.Stack;

import java.util.Stack;

public class _32_Longest_Valid_Parentheses {
    public static int longestValidParentheses(String s) { // nên cải tiến như trong solution <bên dưới>
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        int left = -1; // pivot: coi như có 1 ')' trước String s <=> pivot luôn là index của ')' cuối cùng đã được xét
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push(i); // push tất cả index của '(' vào
            else {                                 // đối với index của ')'
                if (stack.isEmpty()) left = i;     // <=> chưa có '(' nào trước đó -> chuyển trục(left) về vị trí ')' đang xét
                else {                             // <=> có '(' trước đó
                    stack.pop(); // -> xóa 1 '(' đi <vì ta tính đc thêm 1 cặp>
                    if (stack.isEmpty()) max = Math.max(max, i - left);  // chỉ có 1 '(' trong stack (và ta đã xóa ở ngay trên)
                    else max = Math.max(max, i - stack.peek());  // nhiều hơn 1 '(' trong stack <đặc biệt index các '(' phải liền
                                                                 // nhau thì mới cùng tồn tại trong stack đc>
                }
            }
        }
        return max;
    }

    public int longestValidParentheses0(String s) {  // cải tiến cách trên chút: cho left vào stack luôn
        int ans = 0;
        Stack<Integer> stk = new Stack<>(); stk.push(-1); // vẫn coi có 1 ')' trước s
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stk.push(i);
            else {
                stk.pop(); // pop trước vì chắc chắn nó k thể empty đc vì luôn có topNode cuối stack (left của cách trên - pivot)
                if (stk.empty()) {  // nếu xóa xong nó empty -> chỉ có pivot -> trước đó k hề có index của '(' nào đc push vào
                    stk.push(i);    // -> cứ empty là sẽ đc thêm left vào luôn (left luôn là index của ')' cuối đã được xét)
                } else {            // -> có index của '(' trước đó (đã được xóa hồi nãy)
                    ans = Math.max(ans, i - stk.peek());
                }
            }
        }
        return ans;
    }

    public int longestValidParentheses2(String s) {
        int ans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(')
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }

    public int longestValidParentheses3(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")(()))()"));
    }
}
