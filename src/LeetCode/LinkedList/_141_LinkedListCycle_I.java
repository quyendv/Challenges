package LeetCode.LinkedList;

public class _141_LinkedListCycle_I {

    public boolean hasCycle(ListNode head) {  // Lưu ý fast && slow k cùng xuất phát tại head
//        if (head == null || head.next == null) return false; // 0 or 1 Node k thể có cycle
//        ListNode slow = head;
//        ListNode fast = head.next;
//        while (fast != null && fast.next != null && fast != slow) {
//            slow = slow.next;
//            fast = fast.next;
//        }
//        return fast != null && fast == slow;

        if (head == null || head.next == null) return false;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) { // fast && fast.next != null
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
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
