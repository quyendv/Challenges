/*
 * https://leetcode.com/problems/palindrome-linked-list/
 * https://leetcode.com/problems/palindrome-linked-list/discuss/64501/Java-easy-to-understand
 */

package LeetCode.LinkedList;

public class _234_Palindrome_Linked_List {

    public static ListNode addNode(ListNode head, int val) {
        ListNode n = new ListNode(val);
        head.next = n;
        return n;
    }

    public static ListNode reverse(ListNode head) {
        // Tùy bài toán mà ta được phép thay đổi ll ban đầu hay k <nếu k phải các Node mới>
        // Đệ quy lâu hơn / hạn chế dùng
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static boolean isPalindrome(ListNode head) {
        // ta chia ll làm 2, reverse đoạn sau và duyệt 2 ll để so sánh (nửa sau bắt đầu sau Node giữa)
        // VD: + n lẻ: 1 2 3 4 5 ta reverse ll2 từ 4 để thành 1-2-null và 5-4-null
        //     + n chẵn: 1 2 3 4 ta reverse từ 3 để thành 1-2 và 4-3-null

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

    /* Giải thích: số Node fast đi đc gấp 2 so với slow (Node head k tính là đi được) và số Node fast đi qua luôn chẵn (gấp 2 slow) nên:
       + Nếu ll có n chẵn: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null thì n-1 (bỏ Node head) là lẻ => fast chắc chắn kết thúc ở null
         -> tự nhẩm để CM, fast = null và slow.val = 4 (kết Node giữa) -> reverse từ slow
       + Nếu ll có n lẻ:   1 -> 2 -> 3 -> 4 -> 5 -> null thì n-1 là chẵn => fast chắc chắn kết thúc ở Node cuối
         -> fast.val = 5 (node cuối) và slow.val = 3 (Node giữa List) => reverse từ slow.next;
                <tự lấy VD ra thử>
       ==> n lẻ: slow ở Node giữa, fast là Node cuối, n chẵn: fast = null và slow đầu nửa sau <cả 2 tiến thêm 1 Node>
       slow = fast == null ? slow : slow.next; reverse(slow)

    */
        if (fast != null) slow = slow.next; // k được dùng fast.next == null vì fast có thể null
        slow = reverse(slow); fast = head; // dùng lại 2 biến fast slow duyệt chỉ để tiết kiệm bộ nhớ (thay vì tạo 2 biến mới)
        while (slow != null) {
            if (slow.val != fast.val) return false;
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    public static void printLL(ListNode head) {
        for (ListNode p = head; p != null; p = p.next) System.out.printf("%d ", p.val);
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode fakeHead = new ListNode(1);
        int[] arr = {1, 1, 2, 1};
        ListNode head = fakeHead;
        for (int i : arr) head = addNode(head, i); head = fakeHead.next;
        System.out.println(isPalindrome(head));
    }
}