// https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/DepthFirstPaths.java.html

// -->> Ứng dung: Detect Cycle:
// Undirected Graph: https://www.youtube.com/watch?v=6ZRhq2oFCuo, cũng có thể dùng UnionFind
//                   https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
// Directed Graph:   https://www.youtube.com/watch?v=rKQaZuoUR4M <hơi thừa: unvisited, visiting, visited -> chỉ cần 2 mảng cũng đc>
//                   https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/CycleInDirectedGraph.java
//                   https://www.geeksforgeeks.org/detect-cycle-in-a-graph/ <code GFG - ngắn gọn hơn, chỉ dùng 2 mảng>
package Graph;

import java.util.Stack;

// UndirectedGraph hay DirectedGraph đều như vậy
public class DepthFirstPaths {
    private final boolean[] marked;    // marked[v] = is there an s-v path?
    private final int[] edgeTo;        // edgeTo[v] = last edge on s-v path. Ex: 6 -> 4 ==> edgeTo[4] = 6
    private final int s;         // source vertex

    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) { // has path s - v
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int i = v; i != s; i = edgeTo[i]) {
            path.push(i);
        }
        path.push(s); // push vertex nguon
        return path; // print bằng cách pop() lần lượt. Nếu forEach hay iterator thì vẫn từ đáy tới đỉnh stack
    }
}
