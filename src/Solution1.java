public class Solution1 {
    static class Node {
        public int data;
        public Node next;

        public Node(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    public static void reversePrint(Node head, int k) {
        if (head == null) return;

        if (k == 1) {
            System.out.print(head.data + " ");
        }
        else if (k > 1){
            reversePrint(head.next, k - 1);
            System.out.print(head.data + " ");
        }
    }
}
