package LeetCode.LinkedList;
/*
 * https://leetcode.com/problems/linked-list-cycle-ii/discuss/44781/Concise-O(n)-solution-by-using-C%2B%2B-with-Detailed-Alogrithm-Description
 * https://www.youtube.com/watch?v=YxtsZfc-zp4&list=PLqLksqdSk4b37pGIyfy_266wP0-S68HDh&index=28
 */

public class _142_LinkedListCycle_II {

    public ListNode detectCycle(ListNode head) {
        /*  Cách 1: dễ hiểu nhưng chưa hiệu quả
        Set<ListNode> s = new HashSet<>();
        for (ListNode p = head; p != null; p = p.next)
        {
            if (!s.contains(p)) s.add(p);
            else return p;
        }
        return null; */

        // Cách 2: ta cho fast && slow xuất phát từ cùng 1 điểm (khác _141_: ktra cycle) -> s_fast = 2 s_slow
        // -> L1 + L2 + x + L2 = 2 (L1 + L2) -> x = L1

        if (head == null || head.next == null) return null;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) { // fast.next && fast.next.next != null
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                ListNode ans = head;
                while(ans != slow) {
                    slow = slow.next;
                    ans = ans.next;
                }
                return ans; // ans == slow == ListNode bắt đầu cycle
            }
        }
        return null;
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
