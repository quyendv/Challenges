// https://www.hackerrank.com/challenges/primsmstsub/problem
/*
    Cho undirected graph. In trọng số của cây con bao trùm nhỏ nhất MST theo Prim
    Đầu vào: + Dòng đầu chứa V, E (V đánh số từ 1 -> V)
             + E dòng tiếp mỗi dòng là 1 cạnh dạng v w weight
             + Dòng cuối là số nguyên start (đỉnh nguồn)
    --> Hướng: Ta hoàn toàn có thể cài đặt giống như trong slide, dùng adjacency-list với mỗi phần tử là 1 dãy các lớp con là
    Edge (v, w, weight). Tuy nhiên trong bài này chỉ cần weight của cây, nên ta hoàn toàn có thể thay edge bằng class Cost(w, weight)
    với w là đỉnh nối với v và Cost đó thuộc dãy adj(v). Và ta cũng k cần tạo MST và in ra các cạnh trong cây. Cứ tính weight là đc
    --> Lưu ý: Khi ta dùng Cost ta k thể biết được đỉnh v nào nối với w như Edge. Tuy nhiên trong Prim ta không cần quan tâm vì ta
    chỉ cần xét w có nằm trong MST hay chưa thôi vì v chắc chắn đã trong MST thì cạnh Cost đó mới được add vào PQ để xét. Còn với
    thuật toán Kruskal thì phải cần Edge với cả 2 đỉnh như slide
    --> bài này weight chỉ là int, k cần double.
 */
package HackerRank;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Prim_MST_Special_Subtree_MEDIUM {
    static class Cost implements Comparable<Cost> {
        private int w;
        private int weight; // <-> edge v to w with weight = weight

        public Cost(int w, int weight) {
            this.w = w;
            this.weight = weight;
        }

        public int other() {
            return w;
        }

        public int weight() {
            return weight;
        }

        @Override
        public int compareTo(Cost other) {
            return Integer.compare(weight, other.weight);
        }
    }

    static boolean[] marked;
    static int weightMST;
    static int edgesOfMST; // number of edges
    static PriorityQueue<Cost> pq;

    static void Prim(ArrayList<ArrayList<Cost>> graph, int start) {
        visit(graph, start);
        while (!pq.isEmpty() && edgesOfMST < graph.size() - 2) {  // edgesOfMST = V - 1.
            Cost c = pq.poll();
            int w = c.other();
            if (marked[w]) continue;
            weightMST += c.weight();
            edgesOfMST++;   // Chỉ cần đếm số cạnh đủ chưa, k cần tạo MST
            visit(graph, w);
        }
    }

    static void visit(ArrayList<ArrayList<Cost>> graph, int v) {
        marked[v] = true;
        for (Cost edge : graph.get(v)) {
            if (!marked[edge.other()]) {
                pq.offer(edge);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt(), E = sc.nextInt();

        // init graph
        ArrayList<ArrayList<Cost>> graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<Cost>()); // init adj(v) empty. adj(0) dismiss, vertex [1, V]
        }
        marked = new boolean[V + 1];
        pq = new PriorityQueue<>();

        // add edge
        for (int i = 0; i < E; i++) {
            int v = sc.nextInt(), w = sc.nextInt(), weight = sc.nextInt();
            graph.get(v).add(new Cost(w, weight));
            graph.get(w).add(new Cost(v, weight));
        }
        // startVertex
        int start = sc.nextInt();

        Prim(graph, start);
        System.out.println(weightMST);
    }
}
