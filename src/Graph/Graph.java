package Graph;

import java.util.LinkedList;
import java.util.List;

// khác biệt giữa undirected và directed chỉ là dòng 20 có hay k thôi. Đây là cài đặt chung cho Graph
public class Graph {
    private final int V;
    private List<Integer>[] adj; // or Bag<Integer>[] adj: https://algs4.cs.princeton.edu/lectures/keynote/41UndirectedGraphs.pdf

    public Graph(int V) {
        this.V = V;
        adj = (List<Integer>[]) new List[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);          // Directed thì bỏ dòng này đi
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int V() {
        return V;
    }
}
