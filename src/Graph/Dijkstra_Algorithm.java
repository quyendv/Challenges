// https://www.youtube.com/watch?v=XB4MIexjvY0&t=1001s hướng dẫn
// https://www.youtube.com/watch?v=bImvsFNym9I&t=437s code mẫu <tham khảo>
// https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue/  <GFG>
// Thuật toán tìm đường đi ngắn nhất từ 1 đỉnh start
// Code mẫu trên nên cải tiến adj list với mỗi phần tử thuộc adj[v] sẽ là 1 EdgeTo (w, weight) chứa đỉnh nối w và trọng số của cạnh
// --> implement Comparable để có thể so sánh --> sau đó dùng PQ sẽ hiệu quả hơn

package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*
    Time: O(E.logV): Ta có V lần chạy insert cho việc insert V đỉnh vào queue. V lần deleteMin khi lấy mỗi đỉnh ra khỏi queue.
    Và trong trường hợp xấu nhất, ta có thể tính decreaseKey. Các thao tác tốn logV.
    <PQ chỉ chứa các đỉnh (V đỉnh) và khoảng cách từ nguồn tới nó>
*/
public class Dijkstra_Algorithm {
    static class Cost {   // cũng có thể tương đương distanceTo(vertex, distance)
        private int w, weight;  // weight >= 0

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
    }

    // Cách link 1: chưa cải tiến
    static int[] shortestPathFromStart(ArrayList<ArrayList<Cost>> graph, int start) {
        // initialize
        boolean[] visited = new boolean[graph.size()]; // visited[i] == true <-> đỉnh đó đã có đường đi ngắn nhất, k cần xét
        int[] distance = new int[graph.size()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        // solve: O(V^2)  --> worst: V * 2V ?
        while (true) {  // lặp O(V) lần: cho đến khi visited hết
            int minVertex = findMinVertex(distance, visited);  // nên cải tiến dùng PQ với cặp distanceTo(vertex, distance)
            if (minVertex == -1) break;  // fix later
            for (Cost e : graph.get(minVertex)) { // tối đa O(V) khi đỉnh này liên thông với tất cả các đỉnh khác luôn
                if (!visited[e.vertex()]) {
                    if (distance[minVertex] + e.weight() < distance[e.vertex()])
                        distance[e.vertex()] = distance[minVertex] + e.weight();
                }
            }
            visited[minVertex] = true;  // set true trước vòng for cũng được -> k ảnh hưởng
        }
        return distance;
    }

    // O(V)
    static int findMinVertex(int[] distance, boolean[] visited) {
        int minVertex = -1;
        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }

    /* *************************************************************
    * Cách link 2: Dùng PQ, thay mảng boolean bằng Set settled: xem trong link
    * tuy nhiên vẫn chưa xử lý được vấn đề mình cần: add thừa dữ liệu. Đọc code sẽ hiểu, khi mình add 1 phần tử
    * distanceTo(w, distance) vào khi distance đó chưa chuẩn, sau đó khi nó đã chuẩn rồi cạnh đó vẫn k thể xóa ra được dẫn đến
    * các thao tác offer, poll bị tốn thêm tgian
    * --> Lưu ý: sửa lại code trong link: chỉ add khi khoảng cách mới nhỏ hơn. còn lớn hơn add vào làm gì đâu??
    *
    * Cài lại ở bài Dijkstra_Shortest_Reach_2_HARD
    ************************************************************** */

    // main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt(), E = sc.nextInt();

        // init graph. vertex [0, V - 1].
        ArrayList<ArrayList<Cost>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());
        // add edge.
        for (int i = 0; i < E; i++) {
            int v = sc.nextInt(), w = sc.nextInt(), weight = sc.nextInt();
            graph.get(v).add(new Cost(w, weight));  // undirected, weighted-edge
            graph.get(w).add(new Cost(v, weight));  // undirected, weighted-edge
        }
        int startVertex = sc.nextInt();

        int[] distance = shortestPathFromStart(graph, startVertex);
        for (int i = 0; i < V; i++) {
            System.out.println("start (" + startVertex + ") to " + i + " = " + distance[i]);
        }
        /*
            Ex: 4 4
                0 1 1
                0 2 5
                1 2 2
                2 3 2
                0
            --> graph:
            0  --(1)--  1
            |         /
           (5)    (2)
            |   /
            2    --(2)-- 3

            ==> result: 0   1   2   3
              distance  0   1   3   5
        */
    }
}
