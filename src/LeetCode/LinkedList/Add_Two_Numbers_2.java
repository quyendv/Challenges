// Ý tưởng từ bài BigInt C++, khá cũ nhưng còn dùng được :>

package LeetCode.LinkedList;

public class Add_Two_Numbers_2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;

        int carry = 0;

        while (l1 != null || l2 != null) {
            int add = carry;
            if (l1 != null) {
                add += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                add += l2.val;
                l2 = l2.next;
            }
            p = p.next = new ListNode(add % 10);
            carry = add / 10;
        }
        if (carry == 1) p = p.next = new ListNode(1);

        return dummy.next;
    }
}
