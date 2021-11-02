// https://www.hackerrank.com/challenges/queue-using-two-stacks/problem
// giống _232_ Leetcode

package HackerRank;

import java.util.Scanner;
import java.util.Stack;

public class Queue_using_Two_Stacks {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public void enqueue(int val) {
        s1.push(val);
    }

    public int dequeue() {
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    public int peek() {
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue_using_Two_Stacks myQueue = new Queue_using_Two_Stacks();
        int q = sc.nextInt();

        while (q-- > 0) {
            int i = sc.nextInt();
            switch (i) {
                case 1 -> myQueue.enqueue(sc.nextInt());
                case 2 -> myQueue.dequeue();
                case 3 -> System.out.println(myQueue.peek());
            }
        }
    }
}
