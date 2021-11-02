// https://leetcode.com/problems/odd-even-linked-list/
/*
    Các phần tử ở vị trí lẻ về đầu, chẵn về cuối
    a1-b1-a2-b2-...-an-null || a1-b1-a2-b2...-an-bn-null
--> a1-a2-...-an-b1-..-null || a1-a2-..-an-b1-...bn-null
*/

package LeetCode.LinkedList;

public class _328_OddEvenLinkedList {

    public static ListNode oddEvenList(ListNode head) {
        // Cách tự làm:
        // if (head == null) return null;
        // ListNode oddList = new ListNode(0), oddP = oddList;
        // ListNode evenList = new ListNode(0), evenP = evenList;
        // int index = 1;
        // for (ListNode p = head; p != null; p = p.next, index++) {
        //     if (index % 2 == 0) evenP = evenP.next = p;
        //     else oddP = oddP.next = p;
        // }
        // oddP.next = evenList.next;
        // evenP.next = null;
        // return oddList.next;

        // Cách tham khảo: Solution
        if (head == null) return null;
        // head mặc định là Node 1 (tính từ 1)
        ListNode oddP = head, evenP = head.next, evenHead = evenP;

        // mỗi vòng lặp ta gán oddP lên Node lẻ tiếp theo và evenP lên Node chẵn tiếp theo
        // nên phải tồn tại evenP.next != null mới phải lặp tiếp (kéo theo phải có evenP != null mới có evenP.next)
        while (evenP != null && evenP.next != null) {
            oddP = oddP.next = evenP.next;
            evenP = evenP.next = oddP.next;
        }
        oddP.next = evenHead;
        return head;
    }

    public static void printLL (ListNode head) {
        for (ListNode p = head; p != null; p = p.next) System.out.printf("%d ", p.val);
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b; b.next = c; c.next = d; d.next = e;
        printLL(oddEvenList(a));
    }
}
