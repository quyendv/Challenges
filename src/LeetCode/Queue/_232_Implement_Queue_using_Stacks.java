// https://leetcode.com/problems/implement-queue-using-stacks/
// https://www.youtube.com/watch?v=Bwicsjb5AT4&list=PLqLksqdSk4b37pGIyfy_266wP0-S68HDh&index=15

package LeetCode.Queue;

import java.util.Stack;

public class _232_Implement_Queue_using_Stacks {
    static class MyQueue {
        Stack<Integer> s1 = new Stack<>(); // mainStack
        Stack<Integer> s2 = new Stack<>(); // reverseStack

        public MyQueue() {

        }

        public void push(int x) {
            s1.push(x);
        }

        public int pop() {
            peek();         // đổ s1 sang s2 nếu cần
            return s2.pop();
        }

        public int peek() {
            if (s2.empty()) {  // nếu s2 rỗng, đổ s1 sang s2
                while (!s1.empty()) {
                    s2.push(s1.pop());
                }
            }
            return s2.peek();
        }

        public boolean empty() {
            return s1.empty() && s2.empty(); // vì dữ liệu đc phân phát ở cả 2 stack
        }
    }
}
