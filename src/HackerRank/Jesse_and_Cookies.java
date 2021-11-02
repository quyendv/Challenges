package HackerRank;

// https://www.hackerrank.com/challenges/jesse-and-cookies/problem

import java.util.*;

public class Jesse_and_Cookies {
    public static int cookies(int k, List<Integer> A) {
        // Write your code here
        Queue<Integer> minHeap = new PriorityQueue<>(A);
        if (minHeap.isEmpty() || minHeap.peek() >= k) return 0;

        int step = 0;
        while (minHeap.size() > 1 && minHeap.peek() < k) {
            int min1 = minHeap.poll();
            int min2 = minHeap.poll();
            minHeap.add(min1 + 2 * min2);
            step++;
        }

        // all element >= k || minHeap.size() == 1 && peek() < k
        return minHeap.peek() < k ? -1 : step;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
        System.out.println(cookies(k, list));
    }
}
