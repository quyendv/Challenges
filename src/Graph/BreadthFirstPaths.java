// https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/BreadthFirstPaths.java.html

package Graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// UndirectedGraph hay DirectedGraph đều như vậy
public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s-v path
    private int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        bfs(G, s);
    }

    public void bfs(Graph G, int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        marked[s] = true;
        distTo[s] = 0;  // đỉnh nguồn k có edgeTo
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    distTo[w] = distTo[v] + 1;
                    edgeTo[w] = v;
                    queue.offer(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int distTo(int v) {
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        int i;
        for (i = v; distTo[i] != 0; i = edgeTo[i]) {  // có thể dùng thêm đỉnh nguồn như dfs
            path.push(i);
        }
        path.push(i);
        return path;
    }
}
