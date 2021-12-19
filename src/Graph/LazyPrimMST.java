// https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/LazyPrimMST.java.html

package Graph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// Độ phức tạp: O(E.logE) vì insert, delMin E lần vào PQ -> E.logE.
// Space: O(E)
// Lưu ý ta có thể dùng class Cost như trong bài Prim_MST_Special_Subtree_MEDIUM vì ta chỉ cần quan tâm w đã add vào MST hay chưa,
// còn v chắc chắn đã đc add vào MST thì cạnh Cost đó mới đc add vào PQ để xét. Còn thuật toán Kruskal cần phải có 2 đỉnh
public class LazyPrimMST {
    private boolean[] marked;        // marked[v] = true iff v on tree
    private PriorityQueue<Edge> pq;  // edges with one endpoint in tree
    private double weight;           // total weight of MST
    private Queue<Edge> mst;         // edges in the MST

    // Cách trong slide chỉ tạo cây bao trùm của 1 vùng liên thông. Cải tiến tìm rừng bao trùm nhỏ nhất như trong code mẫu algorithms
    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new PriorityQueue<>();
        marked = new boolean[G.V()];
        mst = new LinkedList<>();
        visit(G, 0);
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.poll();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            pq.offer(e);
            weight += e.weight();
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    public void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.offer(e);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }
}
