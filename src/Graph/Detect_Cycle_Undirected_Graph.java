// Xem link ở DepthFirstPaths

package Graph;

import java.util.Iterator;
import java.util.LinkedList;

public class Detect_Cycle_Undirected_Graph {
    static class Graph {

        // No. of vertices
        private int V;

        // Adjacency List Representation
        private LinkedList<Integer> adj[];

        // Constructor
        Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i)
                adj[i] = new LinkedList();
        }

        // Function to add an edge
        // into the graph
        void addEdge(int v, int w) {
            adj[v].add(w);
            adj[w].add(v);
        }

        // A recursive function that
        // uses visited[] and parent to detect
        // cycle in subgraph reachable
        // from vertex v.
        Boolean isCyclicUtil(int v,
                             Boolean visited[], int parent) {
            // Mark the current node as visited
            visited[v] = true;
            Integer i;

            // Recur for all the vertices
            // adjacent to this vertex
            Iterator<Integer> it =
                    adj[v].iterator();
            while (it.hasNext()) {
                i = it.next();

                // If an adjacent is not
                // visited, then recur for that
                // adjacent
                if (!visited[i]) {
                    if (isCyclicUtil(i, visited, v))
                        return true;
                }

                // If an adjacent is visited
                // and not parent of current
                // vertex, then there is a cycle.
                else if (i != parent)
                    return true;
            }
            return false;
        }

        // Returns true if the graph
        // contains a cycle, else false.
        Boolean isCyclic() {

            // Mark all the vertices as
            // not visited and not part of
            // recursion stack
            Boolean visited[] = new Boolean[V];
            for (int i = 0; i < V; i++)
                visited[i] = false;

            // Call the recursive helper
            // function to detect cycle in
            // different DFS trees
            for (int u = 0; u < V; u++) {

                // Don't recur for u if already visited
                if (!visited[u])
                    if (isCyclicUtil(u, visited, -1))
                        return true;
            }

            return false;
        }
    }
}
