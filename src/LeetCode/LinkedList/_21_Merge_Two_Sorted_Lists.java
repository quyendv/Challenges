// https://leetcode.com/problems/merge-two-sorted-lists/

package LeetCode.LinkedList;

public class _21_Merge_Two_Sorted_Lists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode(0);
        ListNode link = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                link.next = l1;
                l1 = l1.next;
            } else {
                link.next = l2;
                l2 = l2.next;
            }
            link = link.next;
        }
        // break when l1 or l2 == null -> continue connect
        link.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}
