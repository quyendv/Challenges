package Graph;

import java.util.LinkedList;
import java.util.List;

// Danh sách kề, mỗi phần tử adj[i] là các cạnh có trọng số có chứa đỉnh i
public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private List<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (List<Edge>[]) new List[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public int degree(int v) {
        return adj[v].size();
    }

    // Trả về danh sách các cạnh
    public Iterable<Edge> edges() {
        List<Edge> list = new LinkedList<>();
        for (int v = 0; v < V; v++) {
            int selfLoop = 0;
            for (Edge e : adj[v]) {
                if (e.other(v) > v) list.add(e); // ex: 1-2 thì chỉ xét 2-3, 2-4... đổ đi, k xét 2-1 nữa
                else if (e.other(v) == v) { // cạnh v nối với cạnh v, ta sẽ chỉ lấy 1 lần
                    if (selfLoop % 2 == 0) list.add(e);
                    selfLoop++;
                }
            }
        }
        return list;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }
}
