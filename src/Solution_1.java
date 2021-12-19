import java.util.ArrayList;
import java.util.Scanner;

public class Solution_1 {

    static int[] id;
    static boolean[] marked;
    static int count;

    public static int solve(ArrayList<ArrayList<Integer>> graph) {
        id = new int[graph.size()];
        marked = new boolean[graph.size()];
        count = 0;

        for (int v = 1; v < graph.size(); v++) {
            dfs(graph, v);
            count++;
        }
        return count;
    }

    public static void dfs(ArrayList<ArrayList<Integer>> graph, int v) {

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>()); // [0, n]
        for (int i = 0; i < m; i++) {
            int v = sc.nextInt(), w = sc.nextInt();
            graph.get(v).add(w);
            graph.get(w).add(v);
        }


    }
}
