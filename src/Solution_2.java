public class Solution_2 {
    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    static Node deleteSingle(Node head) {
        if (head.value != head.next.value && head.value != head.next.next.value) return head.next;
        Node p;
        for (p = head; p.next.next != null; p = p.next) {
            if (p.next.value != p.value && p.next.value != p.next.next.value) {
                p.next = p.next.next;
                break;
            }
        }
        if (p.value != p.next.value) p.next = p.next.next;  // p.next = null;
        return head;
    }

    public static void main(String[] args) {
        Node node4 = new Node(4);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(3, node3);
        Node node1 = new Node(2, node2);
        Node head = new Node(2, node1);

        // test: 2 2 3 4 4  |  2 3 3 4 4   | 2 2 3 3 4

        head = deleteSingle(head);
        for (Node p = head; p != null; p = p.next) {
            System.out.println(p.value);
        }
    }
}
