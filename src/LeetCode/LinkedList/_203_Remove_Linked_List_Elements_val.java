package LeetCode.LinkedList;

public class _203_Remove_Linked_List_Elements_val {

    static ListNode addNode (ListNode head, int val) {
        ListNode n = new ListNode(val);
        head.next = n;
        return n;
    }

    public static ListNode removeElements(ListNode head, int val) {
        // ListNode fakeHead = new ListNode(0); fakeHead.next = head;
        // ListNode prev = fakeHead, cur = head;
        // while (cur != null) {
        //     if (cur.val == val) prev.next = cur.next;
        //     else prev = prev.next;
        //     cur = cur.next;
        // }
        // return fakeHead.next;


        if (head == null) return null;
        head.next = removeElements(head.next, val);
        if (head.val == val) return head.next;
        // nếu đổi dòng này lên trên thì khi đến phần tử có .val = val sẽ chỉ trả về Node tiếp theo và k xét head.next nữa
        return head;
    }

    public static void printLL (ListNode head) {
        for (ListNode p = head; p != null; p = p.next) System.out.printf("%d ", p.val);
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
//        ListNode b = new ListNode(2);
//        ListNode c = new ListNode(3);
//        ListNode d = new ListNode(4);
//        ListNode e = new ListNode(5);
//        a.next = b; b.next = c; c.next = d; d.next = e;
        int[] arr = {1,2,6,3,4,5,6};
        ListNode head = a;
        for (int i : arr) {
            head = addNode(head, i);
        }
        head = removeElements(a.next, 6);
        printLL(head);
    }
}
