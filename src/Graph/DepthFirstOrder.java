package Graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


// topological, pre, post. Topological chỉ có trong đồ thị k có cycle
public class DepthFirstOrder {
    private boolean[] marked;
    private Stack<Integer> reversePostorder;
    private Queue<Integer> preorder;
    private Queue<Integer> postorder;

    public DepthFirstOrder(Graph G) { // nhớ phải là đồ thị có hướng đấy, nếu test thì sửa file Graph thành có hướng.
        marked = new boolean[G.V()];
        preorder = new LinkedList<>();
        postorder = new LinkedList<>();
        reversePostorder = new Stack<>();
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    public void dfs(Graph G, int v) {
        marked[v] = true;
        preorder.offer(v);
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        postorder.offer(v);
        reversePostorder.push(v);
    }

    public Iterable<Integer> reversePostorder() {
        return reversePostorder;    // nên duyệt theo while (!empty()) sout stack.pop() để theo thứ tự ngược.
        // forEach vẫn như queue.
    }

    public Iterable<Integer> preorder() {
        return preorder;
    }

    public Iterable<Integer> postorder() {
        return postorder;
    }
}
