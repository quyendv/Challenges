// https://www.hackerrank.com/challenges/dijkstrashortreach/problem
/*
    Cho đồ thị vô hướng có trọng số
    Tìm đường đi ngắn nhất từ 1 đỉnh start và in ra đường đi từ đỉnh nguồn đến các đỉnh khác (k in đỉnh start - start)
    - Định dạng đầu vào:
    + Số nguyên q: số truy vấn
    + Mỗi truy vấn gồm:
        Dòng đầu tiên gồm V và E. (Lưu ý các đỉnh trên HackerRank đánh số từ 1 tới V, bỏ qua 0)
        E dòng tiếp theo là các cạnh dạng v, w, weight
        Dòng cuối là start - đỉnh bắt đầu
    -> cách giải xem ở file Dijkstra_Algorithm
*/

package HackerRank;

import java.io.IOException;
import java.util.*;


// Solution bên dưới chưa pass hết (test 7)
public class Dijkstra_Shortest_Reach_2_HARD {

    static class Cost implements Comparable<Cost> {
        int w;      // vertex
        int weight; // distance

        public Cost(int w, int weight) {
            this.w = w;
            this.weight = weight;
        }

        public int vertex() {
            return w;
        }

        public int weight() {
            return weight;
        }

        @Override
        public int compareTo(Cost that) {
            return Integer.compare(weight, that.weight);
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Cost) {
                return w == ((Cost) o).vertex();
            }
            return false;
        }
    }

    static int[] findShortestPath(ArrayList<ArrayList<Cost>> graph, int startVertex) {
        // init
        int[] distance = new int[graph.size()];  // V + 1, [1, V]
        Set<Integer> set = new HashSet<>(); // có thể dùng mảng marked để đánh dấu các đỉnh đã hoàn tất,
        // nhưng ta dùng set.size() để xem bao nhiêu đỉnh hoàn tất rồi
        PriorityQueue<Cost> pq = new PriorityQueue<>();

        // solve
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[startVertex] = 0;
        pq.offer(new Cost(startVertex, 0));
        while (!pq.isEmpty() && set.size() < graph.size() - 1) {  // setSize < V vì đủ V đỉnh là hoàn tất rồi
            Cost c = pq.poll();
            int v = c.vertex();
            if (set.contains(c.vertex())) continue;

            // set distance of neighbors
            for (Cost cost : graph.get(c.vertex())) {
                if (!set.contains(cost.vertex())) {
                    if (distance[v] + cost.weight() < distance[cost.vertex()]) {
                        distance[cost.vertex()] = distance[v] + cost.weight();
                        pq.offer(new Cost(cost.vertex(), distance[cost.vertex()]));  // nhỏ hơn mới add
                    }
                }
            }

            // c.vertex() done
            set.add(c.vertex());
        }
        return distance;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        while (q-- > 0) {
            int V = sc.nextInt(), E = sc.nextInt();

            // initialize graph. dismiss graph.get(0)
            ArrayList<ArrayList<Cost>> graph = new ArrayList<>();
            for (int i = 0; i <= V; i++) graph.add(new ArrayList<Cost>());

            // Add edge.
            for (int i = 0; i < E; i++) {
                int v = sc.nextInt(), w = sc.nextInt(), weight = sc.nextInt();
                graph.get(v).add(new Cost(w, weight));
                graph.get(w).add(new Cost(v, weight));
            }
            int startVertex = sc.nextInt();

            // solve
            int[] distance = findShortestPath(graph, startVertex);
            for (int i = 1; i <= V; i++) {
                if (i != startVertex) {
                    System.out.print((distance[i] == Integer.MAX_VALUE ? -1 : distance[i]) + " ");
                }
            }
            System.out.println();
        }
    }
}
