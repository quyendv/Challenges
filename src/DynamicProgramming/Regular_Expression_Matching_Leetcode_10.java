// Link problem Leetcode: https://leetcode.com/problems/regular-expression-matching/
// Link video: https://www.youtube.com/watch?v=l3hda49XcDE, code mẫu dưới mô tả
/*
    Tóm tắt: cho input string s và pattern p. Ktra xem s và p có giống nhau không với s gồm kí tự chữ cái, p gồm kí tự chữ cái và '.', '*'
    + '.': nó có thể thay thế (hay tương đương) với bất kì chữ cái nào. VD: a.b = aab, abb, acb, ...
    + '*': nó cho phép lặp lại kí tự trước nó 0 hoặc nhiều lần (tức có thể xoá kí tự đứng trước nó hoặc nhân bản lên nhiều lần).
        VD: ab* = a, ab, abb, abbb, ... Lưu ý a*, a*b* có thể tương đương với xâu rỗng ''
        '**' không có tác dụng gì hơn '*', '*' đứng trước 1 kí tự mới có nghĩa
    ==> Sử dụng DP: arr2D với i, j là của s và p
    + dp[i][j] = dp[i - 1][j - 1] nếu p[j] = '.' hoặc p[j] = s[i]. (tức s[i] và p[j] tương đương với nhau)
    + dp[i][j] = dp[i][j - 2] hoặc (dp[i - 1][j] khi p[j - 1] tương đương với s[i]) nếu p[j] = '*'

    -> vẫn lưu ý index - 1 so với bảng
*/

package DynamicProgramming;

public class Regular_Expression_Matching_Leetcode_10 {

    // time, space: O(text.length * pattern.length)
    public static boolean matchRegular(char[] text, char[] pattern) {
        boolean[][] dp = new boolean[text.length + 1][pattern.length + 1];

        dp[0][0] = true;

        // case: *a, *a*b, ... tương đương với xâu text rỗng ''
        for (int i = 1; i < dp[0].length; i++) {
            if (pattern[i - 1] == '*') {
                dp[0][i] = dp[0][i - 2]; // '*' k thể ở đầu pattern vì nó k có nghĩa, nên dòng này sẽ không lỗi indexOutOfBound
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (text[i - 1] == pattern[j - 1] || pattern[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pattern[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 2]; // lặp lại 0 lần kí tự trước '*'
                    if (text[i - 1] == pattern[j - 2] || pattern[j - 2] == '.') {
                        dp[i][j] = dp[i][j] | dp[i - 1][j]; // lặp lại nhiều lần thì đưa về bài toán con của xâu text.pop_back()
                                                            // Dùng or | hay || đều được
                    }
                } else dp[i][j] = false; // có thể bỏ qua dòng này vì mặc định = false
            }
        }
        return dp[text.length][pattern.length];
    }

    public static void main(String[] args) {
        System.out.println(matchRegular("aaa".toCharArray(), "ab*a*c*a".toCharArray()));
    }
}
