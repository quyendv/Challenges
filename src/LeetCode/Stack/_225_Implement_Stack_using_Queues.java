// https://leetcode.com/problems/implement-stack-using-queues/
// https://www.youtube.com/watch?v=SmWxMj31lGA&list=PLqLksqdSk4b37pGIyfy_266wP0-S68HDh&index=14
// https://leetcode.com/problems/implement-stack-using-queues/solution/

package LeetCode.Stack;

import java.util.LinkedList;
import java.util.Queue;

public class _225_Implement_Stack_using_Queues {
    static class MyStack {
        Queue<Integer> q1 = new LinkedList<>();
    //        Queue<Integer> q2 = new LinkedList<>();
        /*
            Dùng 2 queue cũng được nhưng k cần thiết, thay vì phải chuyển phần tử queue1 sang queue2 như stack ta có
            thể chuyển nó về cuối queue1 bằng .add(.poll())
        */

        public MyStack() {

        }

        public void push(int x) {
            q1.offer(x);
            for (int i = 0; i < q1.size() - 1; i++)
                q1.offer(q1.poll());          // chuyển phần tử đầu về cuối n - 1 lần => x ở đầu (1 2 3 x -> x 1 2 3)
        }

        public int pop() {
            //noinspection ConstantConditions
            return q1.poll();
        }

        public int top() {
            return q1.peek();
        }

        public boolean empty() {
            return q1.isEmpty();
        }
    }
}