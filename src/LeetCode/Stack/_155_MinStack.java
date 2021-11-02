// https://leetcode.com/problems/min-stack/
/*
    Lớp MinStack vẫn giống stack bth, nhưng vấn đề là có hàm getMin trả về giá trị min tồn tại trong stack
    + Ta không thể dùng 1 biến min để lưu giá trị đó, vì khi pop() có thể peek == min -> lúc đó k biết min sẽ bằng ??
 vì k biết có mấy giá trị min, cũng k thể duyệt stack mỗi lần xóa
    + Giải pháp: Vẫn dùng 1 stack nhưng mỗi lần push ta luôn push thêm phần tử nhỏ nhất vào stack đó
 VD: nếu min đang là 2, ta thêm 3 thì chỉ push 3 (min k đổi) nhưng nếu là thêm 1 thì ta sẽ push thêm 2 vào rồi sau đó push 1
 làm như vậy khi pop() ta vẫn top mới là min mới -> k lạc mất min :>
                <Cách này thay thế cho việc dùng 2 stack: BrownBox>
    + Cách tiếp theo tự tạo 1 LeetCode.Stack với phần tử Node gồm 3 thành phần: val, next, min <biến min lưu giá trị min của LeetCode.Stack>: discuss
*/
package LeetCode.Stack;

import java.util.Stack;

public class _155_MinStack {
    // cách 1: One LeetCode.Stack
    static class MinStack1 {
        Stack<Integer> s;
        int min;

        public MinStack1() {
            s = new Stack<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int val) {
            if (val <= min) { // lưu ý có dấu bằng vì nếu bằng thì ta có 2 min bằng nhau, pop vẫn còn 1 cái ở peek mới
                s.push(min); // push min cũ vào để k lạc min
                min = val; // gán lại
            }
            s.push(val);
        }

        public void pop() {
            if (s.pop() == min) { // vừa check vừa pop luôn rồi
                min = s.pop(); // pop lần 2 vì phần tử này chỉ là min cũ đc thêm tạm vào, pop lần đầu mới là phần tử chính
            }
        }

        public int top() {
            return s.peek();
        }

        public int getMin() {
            return min;
        }
    }

    // Cách 2: implement new LeetCode.Stack
    static class Node {
        int val;
        int min; // min của LeetCode.Stack tính đến khi thêm nó
        Node next;

        Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

    static class MinStack {
        Node head; // head <- node <- node.... : Thêm vào trước head và xóa thì xóa head (LIFO)

        public MinStack() {
            head = null;
        }

        public void push(int val) {
            if (head == null) head = new Node(val, val, null);
            else head = new Node(val, Math.min(head.min, val), head);
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
    }
}
