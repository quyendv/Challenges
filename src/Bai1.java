import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bai1 {

    public static void dfs(ArrayList<ArrayList<Integer>> graph) {
        boolean[] marked = new boolean[graph.size()];
        for (int v = 1; v < graph.size(); v++) {
            if (!marked[v]) {
                dfs(graph, v, marked);
            }
        }
    }

    public static void dfs(ArrayList<ArrayList<Integer>> graph, int v, boolean[] marked) {
        marked[v] = true;
        System.out.print(v + " ");
        for (int w : graph.get(v)) {
            if (!marked[w]) {
                dfs(graph, w, marked);
            }
        }
    }

    public static void bfs(ArrayList<ArrayList<Integer>> graph) {
        boolean[] marked = new boolean[graph.size()];
        for (int v = 1; v < graph.size(); v++) {
            if (!marked[v]) {
                bfs(graph, v, marked);
            }
        }
    }

    public static void bfs(ArrayList<ArrayList<Integer>> graph, int s, boolean[] marked) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            System.out.print(v + " ");
            for (int w : graph.get(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    queue.offer(w);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt(), E = sc.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // size = V+1, [0, V]
        for (int i = 0; i <= V; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < E; i++) {
            int v = sc.nextInt(), w = sc.nextInt();
            graph.get(v).add(w);
            graph.get(w).add(v);
        }
        for (int v = 1; v < graph.size(); v++) {
            graph.get(v).sort(Integer::compareTo);
        }
        dfs(graph);
        System.out.println();
        bfs(graph);
        sc.close();
    }
}
