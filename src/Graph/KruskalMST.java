// https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/KruskalMST.java.html
// Bài này nên cài đặt graph bằng danh sách cạnh. Vì nếu dùng danh sách kề thì vẫn cần lấy ra danh sách các cạnh
// tham khảo code mẫu 1 bài nhập input từ bàn phím: https://www.hackerrank.com/rest/contests/master/challenges/kruskalmstrsub/hackers/zbsGuy/download_solution

package Graph;

import edu.princeton.cs.algs4.UF;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
- Ta xét các cạnh của đồ thị có trọng số từ nhỏ nhất đến lớn nhất. Nếu thêm cạnh đó vào cây bao trùm nhỏ nhất mà k tạo
ra cycle thì ta add, còn k thì xét cạnh tiếp theo.
- Để ktra có cycle hay k ta có thể dùng dfs nhưng độ phức tạp cho TH này là O(V) <không phải V + E vì MST của ta chỉ có V-1 cạnh>.
Nếu dùng UnionFind có thể đạt log*V -> nhanh hơn (dạng UF có trọng số cây, các thao tác find, union đạt tối đa LogN) <thực chất
chỉ là mảng nhưng có thể hiểu theo dạng cây, xem lại slide bài giảng>
 */

// Time Complexity: E.log(E) trong wors-case. Chủ yếu ở PriorityQueue khi khởi tạo E cạnh vào PQ là E.logE, poll() E cạnh
// cũng là E.logE. Còn UF chỉ tốn khoảng log*V cho connected cũng như union.
// Trong TH danh sách cạnh đầu vào đã được sx (best-case) thì ta chỉ cần E.log*V ?? (sao k phải (V - 1).log*V nhỉ)
// Lưu ý trong thực tế log*V <= 5


// Tham khảo bài: Kruskal_MST_Really_Special_Subtree_MEDIUM
public class KruskalMST {
    private double weight;                             // weight of MST. Có thể bỏ qua
    private Queue<Edge> mst = new LinkedList<>();      // edges in MST

    public KruskalMST(EdgeWeightedGraph G) {
        // init edge list
        PriorityQueue<Edge> pq = new PriorityQueue<>(); // sx các cạnh của đồ thị có trọng số để duyệt. Cũng có thể sort thay PQ
        for (Edge e : G.edges()) pq.add(e);

        UF uf = new UF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {  // khi còn cạnh và mst chỉ cần V - 1 cạnh
            Edge e = pq.poll();
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {  // Cạnh e nối v-w, nếu ta thêm vào mà mst k liên thông thì ok
            // or: if (uf.find(v) != uf.find(w)) {
                uf.union(v, w);
                mst.offer(e);
                weight += e.weight();
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }
}
