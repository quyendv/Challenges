/*
    3 set: white, gray, black
    Or 2 boolean arrays
*/

package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Detect_Cycle_Directed_Graph {
    static class Graph {

        private final int V;
        private final List<List<Integer>> adj;

        public Graph(int V) {
            this.V = V;
            adj = new ArrayList<>(V);

            for (int i = 0; i < V; i++)
                adj.add(new LinkedList<>());
        }

        // This function is a variation of DFSUtil() in
        // https://www.geeksforgeeks.org/archives/18212
        private boolean isCyclicUtil(int i, boolean[] visited,
                                     boolean[] recStack) {

            // Mark the current node as visited and
            // part of recursion stack
            if (recStack[i])    // in graySet
                return true;

            if (visited[i])     // in blackSet: Nằm trên đoạn đường k có cycle đã đc ktra trước đó
                return false;

            visited[i] = true;  // nên chuyển xuống cuối

            recStack[i] = true;
            List<Integer> children = adj.get(i);

            for (Integer c : children)
                if (isCyclicUtil(c, visited, recStack))
                    return true;

            recStack[i] = false;    // <-> move element from gray to black

            return false;
        }

        private void addEdge(int source, int dest) {
            adj.get(source).add(dest);
        }

        // Returns true if the graph contains a
        // cycle, else false.
        // This function is a variation of DFS() in
        // https://www.geeksforgeeks.org/archives/18212
        private boolean isCyclic() {

            // Mark all the vertices as not visited and
            // not part of recursion stack
            boolean[] visited = new boolean[V];
            boolean[] recStack = new boolean[V];


            // Call the recursive helper function to
            // detect cycle in different DFS trees
            for (int i = 0; i < V; i++)
                if (isCyclicUtil(i, visited, recStack))
                    return true;

            return false;
        }
    }
}
