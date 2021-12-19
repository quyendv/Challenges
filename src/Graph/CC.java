// https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/CC.java.html

package Graph;

//  Runs in O(E + V) time.
public class CC {
    private boolean[] marked;   // marked[v] = has vertex v been marked?
    private int[] id;           // id[v] = id of connected component containing v
    private int[] size;         // size[id] = number of vertices in given component
    private int count;          // number of connected components

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);      // Dfs từng vùng liên thông. id các phần tử là count (tức vùng liên thông thứ count)
                count++;        // tăng count xét vùng liên thông tiếp theo
            }
        }
    }

    public void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++; // số lượng phần tử vùng liên thông thứ count tăng lên
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int size(int v) {        // tính số lượng phần tử của vùng liên thông chứa v
        return size[id[v]];
    }
}
