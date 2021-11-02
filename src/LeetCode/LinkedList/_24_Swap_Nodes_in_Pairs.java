/*
     https://leetcode.com/problems/swap-nodes-in-pairs/
     Thực hiện đổi chỗ từng cặp liền kề: 1-2-3-4 thành 2-1-4-3
     - Cách 1: <tự nghĩ> thêm dummy trỏ vào head:  // nên khử recursion nếu có thể
        Mỗi lần ta cho link vào ngay trước cặp số tiếp theo <nếu tồn tại cặp số tiếp theo: cả 2 khác null> thì mới đổi tiếp
     + link-1-2-3-4
     + link-2-1-3-4 đổi chiều các thứ
     + link = 1 và tiếp tục cặp tiếp theo
     - Cách 2: recursion: 1-2-3-4-5-6-null
     + đổi chỗ 5-6-null thành 6-5-null, return 6 và hàm trước gọi head.next = hàm mới
*/

package LeetCode.LinkedList;

import java.lang.constant.DynamicCallSiteDesc;
import java.util.List;

public class _24_Swap_Nodes_in_Pairs {

    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode Dummy = new ListNode(0);
        ListNode link = Dummy;

        while (link.next != null && link.next.next != null) { // nếu cặp tiếp theo null hoặc chỉ 1 số null cũng k cần đổi chiều
            ListNode temp = link.next.next;
            link.next.next = temp.next;
            temp.next = link.next;
            link = temp.next;
        }
        return Dummy.next;
    }

    public ListNode swapPairs3(ListNode head) {
        // bỏ Dummy và cur(link) đứng ở đầu mỗi cặp cần đổi -> cur và cur.next != null mới đổi
        if (head == null || head.next == null) return head;
        ListNode cur = head, newHead = head.next;
        while (cur != null && cur.next != null) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = cur;
            cur = cur.next;
            if (cur != null && cur.next != null) temp.next.next = cur.next; // cực kì lưu ý đế nối phần trước với phần tử sau của cặp mới
        }
        return newHead;
    }

    public ListNode swapPairs2(ListNode head) {   // gọn gàng hơn, nhưng khó hiểu hơn :>
        if (head == null || head.next == null) return head;
        ListNode nextNode = head.next;
        head.next = swapPairs2(nextNode.next);
        nextNode.next = head;
        return nextNode;
    }

    /*
        1-2-3-4-5-6-null
        ........5  -null
        ....3  -6-5-null
     */
}
