/*
    https://leetcode.com/problems/decode-string/
    https://leetcode.com/problems/decode-string/discuss/87662/Python-solution-using-stack one stack
    https://leetcode.com/problems/decode-string/discuss/87534/Simple-Java-Solution-using-Stack two stacks -> đổi không gian lấy tgian

    s gồm các cặp "số[string]" kết hợp với nhau -> trước [ luôn là số
 */

package LeetCode.Stack;

import java.util.Stack;

public class _394_Decode_String {
    // Solution 2: two stack
    public String decodeString1(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> resStack = new Stack<>();

        StringBuilder res = new StringBuilder();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                count = count * 10 + ch - '0';

            } else if (Character.isAlphabetic(ch)) {
                res.append(ch);

            } else if (ch == '[') {
                resStack.push(res);         // lưu lại res đến hiện tại, chỉ nên push ở đây
                res = new StringBuilder();  // gán res = "" để lưu string trong []
                numStack.push(count);       // push count vì trước đó là số, push luôn nếu k sẽ mất
                count = 0;                  // gán lại để duyệt tiếp

            } else if (ch == ']') {
                StringBuilder tmp = resStack.pop();
                int repeatNum = numStack.pop();
                tmp.append(String.valueOf(res).repeat(Math.max(0, repeatNum)));
                res = new StringBuilder(tmp);  // k nên push ở đây
            }
        }
        return res.toString();
    }

    // Solution 2: one stack
    public String decodeString(String s) {
        Stack<String> stk = new Stack<>(); // luôn push repeatNum trước mới push res -> cần nhớ thứ tự để pop() lần lượt

        int count = 0;
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                count = count * 10 + c - '0';

            } else if (Character.isLetter(c)) {
                res.append(c);

            } else if (c == '[') {
                stk.push(count + "");  // push count and reset
                count = 0;
                stk.push(res.toString());   // push res and reset
                res = new StringBuilder("");

            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder(stk.pop());
                int repeatNum = Integer.parseInt(stk.pop());
                tmp.append(res.toString().repeat(Math.max(0, repeatNum)));  // String.repeat thay for
                res = tmp;
            }
        }
        return res.toString();
    }
}
