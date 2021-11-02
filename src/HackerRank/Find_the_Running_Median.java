/*
    // https://www.hackerrank.com/challenges/find-the-running-median/problem?h_r=internal-search
    // https://www.youtube.com/watch?v=KxY57jhXQNU&list=PLqLksqdSk4b37pGIyfy_266wP0-S68HDh
    Tìm số trung vị với mỗi lần thêm phần tử vào mảng
    VD: 12 4 5 3 8 7 -> 12.0  8.0  5.0  4.5  5.0  6.0
    -> ta sẽ chia làm 2 mảng: 1 nửa (maxHeap) là các số <= số trung vị và 1 nửa còn lại (minHeap) lớn hơn số trung vị và ĐIỀU KIÊN
       là Max của maxHeap phải <= Min của minHeap.
    Mỗi lần duyệt ưu tiên add vào s1 trước, số trung vị sẽ là Max của maxHeap (nếu lẻ) và (Max maxHeap + Min minHeap) / 2.0 (nếu chẵn)
*/

package HackerRank;

import java.util.*;

public class Find_the_Running_Median {

    // O(NlogN): add(logN), remove(logN), peek(1)
    public static List<Double> runningMedian(List<Integer> a) {
        List<Double> ans = new ArrayList<>();
                // maxHeap <median> - minHeap
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); // các phần tử nhỏ hơn bằng median
                                                                                 // cần lấy Max nên phải reverseOrder
                                                                                 // Có thể dùng Collections.reverseOrder()
        Queue<Integer> minHeap = new PriorityQueue<>(); // các phần tử lớn hơn median, cần lấy min

        for (int val : a) {
            if (maxHeap.size() <= minHeap.size()) { // ưu tiên maxHeap hơn
                maxHeap.offer(val);
            } else {
                minHeap.offer(val);
            }

            // validate:
            while (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                int tmp = maxHeap.poll();
                maxHeap.offer(minHeap.poll());
                minHeap.offer(tmp);
            }

            // 2 TH: size maxHeap > minHeap or maxHeap == minHeap
            if (maxHeap.size() > minHeap.size()) ans.add(maxHeap.peek() * 1.0); // lẻ phần tử -> max maxHeap = median
            else if (!maxHeap.isEmpty() /*&& !minHeap.isEmpty()*/)   // maxHeap  !empty -> minHeap !empty
                ans.add((maxHeap.peek() + minHeap.peek()) / 2.0);              // chẵn phần tử lấy cả 2
        }
        return ans;
    }

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) a.add(sc.nextInt());
        System.out.println(runningMedian(a));
    }
}
