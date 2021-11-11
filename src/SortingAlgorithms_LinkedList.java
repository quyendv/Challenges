// Tài liệu tham khảo trên Internet, có thể xem qua GeeksForGeeks

public class SortingAlgorithms_LinkedList {
    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head;
    Node tail;
    int size;

    public SortingAlgorithms_LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addNode(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail = tail.next = newNode;
        }
        size++;
    }

    // time: O(n^2), space: O(1)
    public void bubbleSort() {
        if (head == null) return;
        Node current, next;
        for (int i = 0; i < size - 1; i++) {
            boolean isSorted = true;

            current = head;
            next = current.next;
            for (int j = 0; j < size - i - 1; j++) {
                if (current.data > next.data) {
                    int temp = current.data;
                    current.data = next.data;
                    next.data = temp;
                    isSorted = false;
                }
                current = next;
                next = next.next;
            }
            if (isSorted) break;
        }
    }

    // time: O(n^2), space: O(1)
    public void selectionSort() {
        if (head == null) return;
        for (Node current = head; current.next != null; current = current.next) {
            Node minNode = current;
            for (Node next = current.next; next != null; next = next.next) {
                if (next.data < minNode.data) {
                    minNode = next;
                }
            }
            // swap value:
            int temp = current.data;
            current.data = minNode.data;
            minNode.data = temp;
        }
    }

    public void insertionSort() {
        Node current = head;
        Node sortedHead = null;
        while (current != null) {
            Node next = current.next;
            sortedHead = sortedInsert(sortedHead, current);
            current = next;
        }
        head = sortedHead;
    }

    private Node sortedInsert(Node sortedHead, Node newNode) {
        if (sortedHead == null || newNode.data <= sortedHead.data) {
            newNode.next = sortedHead;
            return newNode;
        }
        // else
        Node p = sortedHead;
        while (p.next != null && newNode.data > p.next.data) {
            p = p.next;
        }
        newNode.next = p.next;
        p.next = newNode;
        return sortedHead;
    }

    // Time Complexity: O(n*log n)
    // Space Complexity: O(n*log n)
    public void mergeSort() {
        head = sort(head);
    }

    private Node sort(Node head) {
        if (head == null || head.next == null)
            return head; // cần ít nhất 2 phần tử để sort

        Node mid = getMiddle(head);
        Node nextMid = mid.next;

        mid.next = null;

        Node left = sort(head);
        Node right = sort(nextMid);

        // return merge(left, right);
        return merge2(left, right);
    }

    // đệ quy n lần -> space: N * logN
    private Node merge(Node head1, Node head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        if (head1.data <= head2.data) {
            head1.next = merge(head1.next, head2);
            return head1;
        }
        // else
        else {
            head2.next = merge(head1, head2.next);
            return head2;
        }
    }

    private Node merge2(Node head1, Node head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;
        Node fakeHead = new Node(0);
        Node temp = fakeHead;
        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                temp = temp.next = head1;
                head1 = head1.next;
            } else {
                temp = temp.next = head2;
                head2 = head2.next;
            }
        }
        temp.next = head1 == null ? head2 : head1;
        return fakeHead.next;
    }

    private Node getMiddle(Node head) {
        if (head == null) return null;

        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Lomuto
    public void quickSort() {
        quickSort(head, tail);
    }

    private void quickSort(Node start, Node end) {
        if (start == end || start == null || end == null) {
            return;
        }
        Node prevPivot = partition(start, end);
        quickSort(start, prevPivot);

        if (prevPivot != null && prevPivot == start)
            quickSort(prevPivot.next, end);    // Original list: 1 4 3 2 5 ->
        else if (prevPivot != null && prevPivot.next != null)
            quickSort(prevPivot.next.next, end);
    }

    private Node partition(Node start, Node end) {
        if (start == end || start == null || end == null) {
            return start;
        }
        Node prevPivot = start;
        Node current = start; // lưu vị trí cần swap các giá trị < pivot
        int pivot = end.data;
        while (start != end) {
            if (start.data < pivot) {
                // update prevPivot
                prevPivot = current;

                // swap data current, start
                int temp = current.data;
                current.data = start.data;
                start.data = temp;

                // next current
                current = current.next;
            }
            start = start.next;
        }
        // swap data current, pivot(end)
        end.data = current.data;
        current.data = pivot;

        return prevPivot;
    }

    public void display() {
        for (Node p = head; p != null; p = p.next) {
            System.out.print(p.data + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        SortingAlgorithms_LinkedList sList = new SortingAlgorithms_LinkedList();

        // Adds data to the list
        sList.addNode(8);
        sList.addNode(3);
        sList.addNode(7);
        sList.addNode(4);
        sList.addNode(6);
        sList.addNode(5);

        // Displaying original list
        System.out.println("Original list: ");
        sList.display();

        // Sorting list
        // sList.bubbleSort();
        // sList.selectionSort();
        // sList.insertionSort();
        // sList.mergeSort();
        sList.quickSort();

        // Displaying sorted list
        System.out.println("Sorted list: ");
        sList.display();
    }
}