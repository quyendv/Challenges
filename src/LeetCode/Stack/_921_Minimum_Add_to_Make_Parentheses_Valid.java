package LeetCode.Stack;

public class _921_Minimum_Add_to_Make_Parentheses_Valid {
    /**
     * - Dấu ')' mà phía TRƯỚC đó không có dấu '(' nào tức là phải add thêm
     * - Dấu '(' mà phía SAU   đó không có dấu ')' nào tức là phải add thêm
     * -> Ý tưởng ban đầu (tự làm): duyệt 2 vòng (từ đầu và từ cuối) để đến invalid open và invalid close đc mô tả ở trên
     * -> cải tiến: chạy 1 lần duy nhất từ trái sáng phải, dùng invalidClose như trước, nhưng invalidOpen tính giống như balance/mismatches kèm thêm điều kiện nếu là ')' và giá trị của nó < 0 (tức -1 do giảm dần từng đơn vị) - tức tại vị trí hiện tại có 1 invalidClose mới và phía trước valid các cặp hết rồi, thì reset về 0 và invalidClose++;
     * Xem bài 1963. cách khác để tính invalidOpen -> duyệt qua dấu ( thì tăng count, gặp ) thì giảm count nếu count > 0 -> cuối cùng là số dấu ( mà không có ) để khớp. Tương tự cho invalidClose
     */

    /*  Original idea */
//     var minAddToMakeValid = function (s) {
//       let invalidClose = 0,
//         invalidOpen = 0;
//
//       // Check invalid close (tức tại dấu ')' không có bất kì dấu mở nào phía TRƯỚC -> phải add)
//       let stackSize = 0; // add open and check close -> tính đc invalidOpen luôn
//       for (const c of s) {
//         if (c === '(') stackSize++;
//         else {
//           if (stackSize > 0) stackSize--;
//           else invalidClose++;
//         }
//       }
//       console.log(invalidClose, stackSize); // tại đây stackSize là invalidOpen luôn rồi
//
//       // Check invalid open (tức tại dấu '(' không có bất kì dấu mở nào phía SAU -> phải add)
//       stackSize = 0; // add close and check open
//       for (let i = s.length - 1; i >= 0; i--) {
//         const c = s[i];
//         if (c === ')') stackSize++;
//         else {
//           if (stackSize > 0) stackSize--;
//           else invalidOpen++;
//         }
//       }
//
//       return invalidOpen + invalidClose;
//     };

    /* Improve idea */
    public static int minAddToMakeValid(String s) {
        int invalidOpen = 0, invalidClose = 0;
        for (char c : s.toCharArray()) {
            invalidOpen += (c == '(' ? 1 : -1);

            // when c is ')' and before all is valid parentheses
            if (invalidOpen == -1) {
                invalidOpen = 0;
                invalidClose++;
            }
        }
        return invalidOpen + invalidClose;
    }

    public static void main(String[] args) {
        System.out.println(minAddToMakeValid("))(("));
    }
}
