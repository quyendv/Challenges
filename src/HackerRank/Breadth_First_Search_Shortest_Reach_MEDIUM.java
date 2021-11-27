// https://www.hackerrank.com/challenges/bfsshortreach/problem

/**
 * Nhập q (số truy vấn). Mỗi truy vấn gồm:
 * + Dòng đầu chứa số đỉnh N (vertex đánh số từ 1 đến N, bỏ qua 0) và số cạnh M
 * + M dòng tiếp mỗi dòng nhập 1 cặp cạnh
 * + Dòng tiếp theo là đỉnh start (cho index).
 * --> Yêu cầu tìm đường ngắn nhất từ đỉnh start đến các đỉnh khác. Khoảng cách giữa 2 đỉnh connected mặc định là 6 (unweighted graph, chưa
 * cần dùng thuật toán Dijkstra với mỗi cạnh có độ dài riêng), nếu k có đường thì kq là -1
 *
 *
 * Tham khảo lời giải ở discuss, editorial hoặc link: https://drive.google.com/file/d/0B7mScmczCiHWcW8xMl9GMUxUVUE/view?resourcekey=0-iMsO6K2g_Q4UbUtJ3n9Dbg
 * của cô Châu (bài làm bên dưới):
 * + graph được biểu diễn dưới dạng adjacency list representation (ArrayList 2 chiều: vì đỉnh từ 1 đến N nên tạo size N+1 và bỏ qua get(0))
 * + Hàm findShortestReach trả về mảng N + 1 phần tử (N là số đỉnh). Nhưng ta chỉ cần n-1 phần tử tương đương khoảng cách từ start tới n - 1
 * đỉnh còn lại. Ta chỉ trả về mảng kết quả còn việc in ra phần tử nào để main xử lý.
 * + Khởi tạo mảng costs (result) với các giá trị = -1 và ta bfs từ start. Bởi bfs sẽ đi dần đến hết các vertex mà nó có thể đến được.
 * Các đỉnh khác k đến được thì mặc định là -1 luôn.
 */

package HackerRank;

import java.util.*;

public class Breadth_First_Search_Shortest_Reach_MEDIUM {
    static int[] findShortestReach(ArrayList<ArrayList<Integer>> graph, int start) {
        int[] costs = new int[graph.size()];        // tương tự diskTo[] ở slide với diskTo[i] là cạnh thứ i
        int UNIT_COST = 6;
        // for (int i = 0; i < costs.length; ++i) costs[i] = -1;
        Arrays.fill(costs, -1);
        boolean[] visited = new boolean[graph.size()];
        // Arrays.fill(visited, false);

        // Write your code here
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        costs[start] = 0; // không quan trọng vì ta k in cái này
                          // nhưng nên gán = 0 vì cost[neighbor] = costs[v] + UNIT_COST dễ hơn là -1 với costs[neighbor] = v == start ? UNIT_COST : cost[v] + UNIT_COST
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int neighborV : graph.get(v)) {
                if (!visited[neighborV]) {
                    visited[neighborV] = true;
                    queue.offer(neighborV);
                    costs[neighborV] = costs[v] + UNIT_COST;
                }
            }
        }
        return costs;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int Q = scanner.nextInt();
        for (int q = 0; q < Q; ++q) {
            int N, M, start;
            N = scanner.nextInt();
            M = scanner.nextInt();
            ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i <= N; ++i) {
                graph.add(new ArrayList<Integer>());
            }
            for (int i = 0; i < M; ++i) {
                int n1, n2;
                n1 = scanner.nextInt();
                n2 = scanner.nextInt();
                graph.get(n1).add(n2);
                graph.get(n2).add(n1);
            }
            start = scanner.nextInt();
            /*
            for (int i = 0; i < N; ++i) {
                Collections.sort(graph.get(i));
            }
            for (int i = 1; i <= N; ++i) {
                System.out.println("Node: " + i);
                System.out.println(graph.get(i).toString());
            }
            System.out.println("Start: " + start);
            */
            int[] costs = findShortestReach(graph, start);
            for (int i = 1; i < costs.length; ++i) {
                if (i == start) continue;
                System.out.print(costs[i] + " ");
            }
            System.out.println();
        }
    }
}
