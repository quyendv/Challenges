// https://www.hackerrank.com/challenges/kruskalmstrsub/problem
// tham khảo bài mẫu: https://www.hackerrank.com/rest/contests/master/challenges/kruskalmstrsub/hackers/zbsGuy/download_solution
// Bài này ta nên cài bằng danh sách cạnh sẽ hiệu quả hơn. Vì cài bằng danh sách kề như slide ta cũng vẫn phải trả về danh sách
// các cạnh của đồ thị mới làm tiếp đc

/*
    Cho đồ thị vô hướng có trọng số. Tìm và in ra weightMST bằng KruskalAlgorithm
    Đầu vào: + Dòng đầu tiên: V, E
             + E dòng tiếp: v, w, ưeight
 */

package HackerRank;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Kruskal_MST_Really_Special_Subtree_MEDIUM {
    static class Edge implements Comparable<Edge> {
        int v, w, weight;

        public Edge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public int from() {
            return v;
        }

        public int to() {
            return w;
        }

        public int weight() {
            return weight;
        }

        @Override
        public int compareTo(Edge that) {  // phải so sánh cả 2 đỉnh nữa vì ta dùng set nếu chỉ so sánh cạnh thì 2 cạnh cùng
                                           // weight sẽ là 2 phần tử bằng nhau trong set
            if (weight == that.weight) {
                if (v == that.v) return Integer.compare(w, that.w);
                return Integer.compare(v, that.v);
            }
            return Integer.compare(weight, that.weight);
        }
    }

    // static PriorityQueue<Edge> pq = new PriorityQueue<>(); // ta có thể dùng Set thay thế
    static Set<Edge> edges = new TreeSet<>();
    static int[] id;
    static int[] size;

    static int find(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }

    static void union(int v, int w) {
        int i = find(v);
        int j = find(w);
        if (size[i] < size[j]) {
            id[i] = j;
            size[j] += size[i];
        } else {
            id[j] = id[i];
            size[i] += size[j];
        }
    }

    static boolean connected(int v, int w) {
        return find(v) == find(w);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt(), E = sc.nextInt();

        // init
        id = new int[V + 1];
        size = new int[V + 1];
        for (int i = 0; i <= V; i++) id[i] = i;
        // List<Edge> graph = new ArrayList<>(); // k cần luôn

        // add edge
        for (int i = 0; i < E; i++) {
            int v = sc.nextInt(), w = sc.nextInt(), weight = sc.nextInt();
            edges.add(new Edge(Math.min(v, w), Math.max(v, w), weight));
        }

        // solve: cal weightMST
        int weightMST = 0;
        for (Edge e : edges) {
            if (!connected(e.v, e.w)) {
                union(e.v, e.w);
                weightMST += e.weight;
            }
        }
        System.out.println(weightMST);
    }
}
