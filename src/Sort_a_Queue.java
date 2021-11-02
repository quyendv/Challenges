// https://www.geeksforgeeks.org/sorting-queue-without-extra-space/
// https://www.youtube.com/watch?v=OcQyfkpA0YY

import java.util.LinkedList;
import java.util.Queue;

public class Sort_a_Queue {

    public static void sortQueue(Queue<Integer> qu) {
        for (int i = 0; i < qu.size(); i++) {
            int minIndex = findMinIndex(qu, qu.size() - i - 1);
            insertMinToRear(qu, minIndex);
        }
    }

    public static int findMinIndex(Queue<Integer> qu, int findLimitIndex) {
        int minIndex = -1, minValue = Integer.MAX_VALUE;
        int n = qu.size();
        for (int i = 0; i < n; i++) {
            int current = qu.poll();
            if (i <= findLimitIndex && current <= minValue) {
                minIndex = i;
                minValue = current;
            }
            qu.offer(current);
        }
        return minIndex;
    }

    public static void insertMinToRear(Queue<Integer> qu, int minIndex) {
        int n = qu.size();
        int minValue = 0;
        for (int i = 0; i < n; i++) {
            int current = qu.poll();
            if (i == minIndex) {
                minValue = current;
            } else {
                qu.offer(current);
            }
        }
        qu.offer(minValue);
    }

    public static void main(String[] args) {
        Queue<Integer> qu = new LinkedList<>();
        for (int i = 0; i < 10; i++) qu.offer(10 - i - 1);
        System.out.println(qu);
        sortQueue(qu);
        System.out.println(qu);
    }
}
