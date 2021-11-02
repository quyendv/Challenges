package LeetCode.LinkedList;

public class _91_Remove_Nth_NodeFromEndofList {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // n tính từ 1 là Node cuối
        ListNode fakeHead = new ListNode(0); fakeHead.next = head;
        // mọi bài nên tạo fakeHead->head để tránh lỗi
        // VD: 1->null ta tạo 0-1-null, sau khi p = 1, cur = 0, ta cho 0-null và return fakeHead.next là null

        ListNode p = fakeHead, cur = fakeHead;

        for (int i = 0; i < n; i++) p = p.next;  // để p cách cur n Node
        while (p.next != null) { // đưa cur đến trước node cần xóa
            p = p.next;
            cur = cur.next;
        }
        cur.next = cur.next.next;
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
