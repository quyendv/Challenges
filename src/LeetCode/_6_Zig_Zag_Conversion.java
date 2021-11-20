/*
    https://leetcode.com/problems/zigzag-conversion/submissions/
    Cho 1 String s được tạo từ biến đổi ZigZag 1 String ans theo numRows hàng. tìm ans.
*/

package LeetCode;

public class _6_Zig_Zag_Conversion {

    // Cách 1: ta dải cái String s theo zigzag theo cách nó đc tạo, và trả về từng dòng chữ (k tính " ") -> ans
    // ~ 4ms
    public String convert(String s, int numRows) {
        int n = s.length();

        if (numRows <= 1 || n <= numRows) {   // 2TH quan trọng giảm tgian chạy từ 12ms -> 9ms
            return s;
        }

        char[] tmp = s.toCharArray();
        /*
            Có 1 lưu ý ở đây: ta tạo char[] chỉ để truy xuất nhanh hơn ở 2 vòng for bên dưới nhưng nó giảm
            tgian chạy bài toán từ 9ms -> 4ms (mảng truy xuất nhanh hơn Sring.charAt đáng kể)
            ==> luôn luôn tránh thao tác với String Java, thay bằng mảng || StringBuilder
        */


        StringBuilder[] ans = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++)
            ans[i] = new StringBuilder();

        int i = 0;
        while (i < n) {
            for (int row = 0; row < numRows && i < n; row++) {
                ans[row].append(tmp[i++]);                          // dùng char[] nhanh hơn s.charAt rất nhiều
            }
            for (int row = numRows - 2; row >= 1 && i < n; row--) {
                ans[row].append(tmp[i++]);                          // dùng char[] nhanh hơn s.charAt rất nhiều
            }
        }

        for (int j = 1; j < numRows; j++)
            ans[0].append(ans[j]);

        return ans[0].toString();
    }


    /*
        Cách 2: ta ngầm hiểu S theo zigzag (k triển khai như cách 1) và ta add từng kí tự đúng trên mỗi dòng zigzag đó
        vào ans (dựa vào khoảng cách giữa các kí tự ở mỗi hàng). Tham khảo hình vẽ ở:
        https://leetcode.com/problems/zigzag-conversion/discuss/3435/If-you-are-confused-with-zigzag-patterncome-and-see!
        và solution mẫu nhanh nhất ở:
        https://leetcode.com/submissions/detail/571058259/
        ~ 2ms

        n = numRows
            Δ=2n-2    1                           2n-1                         4n-3
            Δ=        2                     2n-2  2n                    4n-4   4n-2
            Δ=        3               2n-3        2n+1              4n-5       .
            Δ=        .           .               .               .            .
            Δ=        .       n+2                 .           3n               .
            Δ=        n-1 n+1                     3n-3    3n-1                 5n-5
            Δ=2n-2    n                           3n-2                         5n-4
    */
    public static String convert1(String s, int numRows) {
        int n = s.length();
        if (n < numRows || numRows <= 1) return s;

        char[] tmp = s.toCharArray();    // vẫn dùng tmp để truy cập nhanh hơn String.charAt

        StringBuilder ans = new StringBuilder();
        int interval = 2 * numRows - 2;  // khoảng cách ban đầu giữa mỗi kí tự cần trên dòng <=> maxStep
                                         // khoảng cách giữa 2 cột thẳng đứng '|' (k phải đường chéo '/') |/|/|/|....

        for (int row = 0; row < numRows; row++) {
            int step = interval - 2 * row;  // row dần đến numRows thì step giảm dần 2numRows - 2 tới 0;
                                            // decreaseStep = 2 * row
            for (int i = row; i < n; i += interval) {  // index duyệt qua các kí tự cần trên 1 hàng theo từng cặp "|/"
                                                       // chú ý i chạy từ cột 0 nhưng index = row của S, tránh nhầm từ 0
                ans.append(tmp[i]);                    // thêm cột thẳng đứng
                if (step > 0 && step < interval && i + step < n)    // thêm đường chéo (k tính hàng đầu và cuối)
                    ans.append(tmp[i + step]);
            }
        }

        return ans.toString();
    }


    public String recode(String s, int numRows) {
        if (numRows <= 1 || s.length() <= numRows) return s;

        char[] chars = s.toCharArray();
        int n = chars.length, index = 0;
        StringBuilder[] res = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) res[i] = new StringBuilder();

        while (index < n) {
            for (int row = 0; row < numRows && index < n; row++) {
                res[row].append(chars[index++]);
            }
            for (int row = numRows - 2; row > 0 && index < n; row--) {
                res[row].append(chars[index++]);
            }
        }

        for (int i = 1; i < numRows; i++) res[0].append(res[i]);
        return res[0].toString();

//        if (numRows <= 1 || s.length() <= numRows) return s;
//
//        int n = s.length();
//        char[] chars = s.toCharArray();
//        int interval = 2 * numRows - 2;
//        StringBuilder res = new StringBuilder();
//
//        for (int row = 0; row < numRows; row++) {
//            int step = interval - 2 * row;
//            for (int i = row; i < n; i += interval) {
//                res.append(chars[i]);
//                if (step > 0 && step < interval && i + step < n) res.append(chars[i + step]);
//            }
//        }
//        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert1("PAYPALISHIRING", 3) + '\n' + "PAHNAPLSIIGYIR");
    }
}
