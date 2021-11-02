// https://leetcode.com/problems/reverse-linked-list-ii/
// Đảo ngược đoạn ll từ vị trí left đến right (đếm từ 1: head là vị trí 1)
// VD: 1 2 3 4 5, left = 2, right = 4 --> 1 4 3 2 5

package LeetCode.LinkedList;

public class _92_ReverseLinkedList_II {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode fakeHead = new ListNode(0); fakeHead.next = head;
        // tất cả mọi bài nên tạo fakeHead để đảm bảo k thay đổi ll (có trường hợp đảo cả head đi vị trí khác->return fakeHead.next)

        ListNode p = fakeHead;  // duyệt đưa p đến vị trí trước node left
        for (int i = 0; i < left - 1; i++) {
            p = p.next;
        }
        // VD ban đầu: 1 2 3 4 5, left = 2, right = 4 --> p ở Node có giá trị 1


        ListNode prev = p, cur = p.next, next;
        // cur ở Node có val = 2, ta chạy cur từ [left, right], mỗi lần ta đều đảo chiều các Node lại <giống _206_ revers ll bình thường>
        for (int i = 0; i < right - left + 1; i++) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        // Đến đây ta có: 1 -><- 2 <- 3 <- 4 <- 5 -> null
        p.next.next = cur; // đưa 1 trỏ vào 4 (p vẫn ở 1)
        p.next = prev; // trỏ 2 vào 5
        return fakeHead.next;
    }
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b; b.next = c; c.next = d; d.next = e;
    }
}
