/*
    Ảnh convertLL trong LinkedList
    * Tuỳ theo yêu cầu đề bài số phần tử chẵn hay lẻ để biết có bn hay k
    a1-a2-...an-b1-..-bn-1/bn-null -> a1-b1-a2-b2...
*/

package LeetCode.LinkedList;

public class _328_OddEvenLinkedList_Reverse {
    public ListNode merge (ListNode a, ListNode b, int n) {
        if (b == null) {
            if (n % 2 == 0) {  // VD: 1 2 3 4 - null  => 1 3 2 4 - null
                return null;
            }
            // else: 1 2 3 4 5 - null => 1 4 2 5 3 null
            a.next = b;
            return a;
        }
        b.next = merge(a.next, b.next, n);
        a.next = b; // bây giờ mới nối a với b để dòng trên k mất a.next
        return a;
    }

    public ListNode merge1(ListNode a, ListNode b, int index, int n) { // index từ 0
        if (b == null) {
            if (n % 2 == 0) {  // VD: 0 1 2 3 - null
                return null;
            }
            // else: 1 2 3 4 5 - null => 1 4 2 5 3 null
            a.next = b;
            return a;
        }

        // Phần dưới ý tưởng giống merge 2 sorted linkedlist
        if (index % 2 == 1) {
            a.next = merge1(a.next, b, index + 1, n);
            return a;
        } else {
            b.next = merge1(a, b.next, index + 1, n);
            return b;
        }
    }

    public ListNode solve (ListNode head) {
        if (head == null || head.next == null) return head; // vì cần ít nhất 2 Node
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null) {   // n lẻ: 1 2 3 4 5 null
            slow = slow.next; // slow thành b1 = 4
            head = merge(head, slow, 1); // a1, b1 và 1 đại diện cho số lẻ Node thôi
        } else {
            head = merge(head, slow, 0);
        }
        return head;
    }
}
