package LeetCode.LinkedList;

public class DoubleLinkedList {
    // leetcode: 707
    static class Node {
        int val;
        Node next;
        Node prev;
        Node(int val) { this.val = val; this.next = null; this.prev = null; }
    }
    static class MyLinkedList {
        /** Initialize your data structure here. */
        Node head;
        Node tail;
        int size;
        public MyLinkedList() {
            head = null;
            tail = null;
            size = 0;
        }
        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public Node getNode(int index) {
            if (index < 0 || index > size - 1) return null;
            if (index == 0) return head;
            if (index == size - 1) return tail;

            Node res;
            if (index < size/2) {
                res = head;
                for (int i = 0; i < index; i++) res = res.next;
            } else {
                res = tail;
                for (int i = size - 1; i > index; i--) res = res.prev;
            }
            return res;
        }
        public int get(int index) {
            Node cur = getNode(index);
            return cur == null ? -1 : cur.val;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
            Node newN = new Node(val);
            if (head == null) {
                head = newN;
                tail = newN;
            } else {
                newN.next = head;
                head = head.prev = newN;
            }
            size++;
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            Node newN = new Node(val);
            if (tail == null) {
                head = newN;
                tail = newN;
            } else {
                newN.prev = tail;
                tail = tail.next = newN;
            }
            size++;
        }

        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            if (index < 0 || index > size) return;
            if (index == 0) addAtHead(val);
            else if (index == size) addAtTail(val);
            else { // index: [1, size - 1]
                Node newN = new Node(val);
                Node cur = getNode(index);
                newN.prev = cur.prev;
                newN.next = cur;
                cur.prev.next = newN;
                cur.prev = newN;
                size++;
            }
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) return;
            if (index == 0) {
                if (size == 1) {
                    head = null;
                    tail = null;
                } else {
                    head = head.next;
                    head.prev = null;
                }
                size--;
                return;
            }
            if (index == size - 1) {
                if (size == 1) {  // vì index > 0 (do loại 2 if bên trên rồi) -> size luôn > 1 -> k cần đoạn này
                    head = null;
                    tail = null;
                } else {
                    tail = tail.prev;
                    tail.next = null;
                }
                size--;
                return;
            }
            // else: index [1, size - 2]
            Node cur = getNode(index);
            cur.next.prev = cur.prev;
            cur.prev.next = cur.next;
            size--;
        }
        public void print() {
            for (Node i = head; i != null; i = i.next) System.out.print(i.val + " ");
            System.out.println();
        }
    }
    public static void main(String[] args) {
/*
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(2); myLinkedList.print();
        myLinkedList.deleteAtIndex(1); myLinkedList.print();
        myLinkedList.addAtHead(2); myLinkedList.print();
        myLinkedList.addAtHead(7); myLinkedList.print();
        myLinkedList.addAtHead(3); myLinkedList.print();
        myLinkedList.addAtHead(2); myLinkedList.print();
        myLinkedList.addAtHead(5); myLinkedList.print();
        myLinkedList.addAtTail(5); myLinkedList.print();
        myLinkedList.get(5); myLinkedList.print();
        myLinkedList.deleteAtIndex(6); myLinkedList.print();
        myLinkedList.deleteAtIndex(4); myLinkedList.print();
*/

        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1); myLinkedList.print();
        myLinkedList.addAtTail(3); myLinkedList.print();
        myLinkedList.addAtIndex(1, 2); myLinkedList.print();
        myLinkedList.get(1); myLinkedList.print();
        myLinkedList.deleteAtIndex(1); myLinkedList.print();
        myLinkedList.get(1); myLinkedList.print();
    }
}
