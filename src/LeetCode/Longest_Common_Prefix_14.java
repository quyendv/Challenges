// https://leetcode.com/problems/longest-common-prefix/

/*
    Tìm prefix của 1 mảng String strs. Với prefix là chuỗi bắt đầu (tức startWith của tất cả các String trong strs)
    VD: flo, fle, flat -> "fl". dog, car, cat -> ""
    -> Cách giải:
    + Cách 1: so sánh từng cặp: ta lấy prefix của từng cặp 2 String trong strs (for đến khi gặp kí tự khác nhau của 2 xâu thì substring ra)
    + Cách 2: lấy res là strs[0]. so sánh từng kí tự trong res với từng kí tự trong strs -> hiệu quả hơn cách 1.
    + Cách 3: bỏ dần kí tự cuối đi cho đến khi thành prefix. Xem video rõ hơn
    --<> video cách 2,3 : https://youtu.be/TNEpFdG7Rss
 */

package LeetCode;

public class Longest_Common_Prefix_14 {
    // cách 2:
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        String res = strs[0];
        for (int i = 0; i < res.length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || res.charAt(i) != strs[j].charAt(i))
                    return res.substring(0, i);
            }
        }
        return res; // TH strs có 1 string
    }

    // cách 3:
    public String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while(!strs[i].startsWith(res)) {
                res = res.substring(0, res.length() - 1); // pop_back()
            }
        }
        return res;
    }

    // Cách 1: k hiệu quả. xem để hình dung thôi
    public String longestCommonPrefix0(String[] strs) {
        if (strs.length == 0) return "";
        StringBuilder res = new StringBuilder(strs[0]);

        for (int i = 1; i < strs.length; i++) {
            find2word(res, strs[i]);
            if (res.toString().equals("")) break;
        }
        return res.toString();
    }

    public void find2word(StringBuilder res, String s) {
        int i, minLength = Math.min(res.length(), s.length());
        for (i = 0; i < minLength; i++) {
            if (res.charAt(i) != s.charAt(i)) break;
        }
        res.delete(i, res.length());
    }
}
