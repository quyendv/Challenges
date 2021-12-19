import java.util.Scanner;

public class Bai2 {
//
//    static int count;
//    static boolean[] marked;
//
//    public static void solve(ArrayList<ArrayList<Integer>> graph) {
//        count = 0;
//        marked = new boolean[graph.size()];
//
//        for (int v = 1; v <= graph.size(); v++) {
//            if (!marked[v]) {
//                dfs(graph, v);
//                count++;
//            }
//        }
//
//    }
//
//    public static void dfs(ArrayList<ArrayList<Integer>> graph, int v) {
//        marked[v] = true;
//        for (int w : graph.get(v)) {
//            if (!marked[w]) {
//                dfs(graph, w);
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int V = sc.nextInt(), E = sc.nextInt();
//        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // size = V+1, [0, V]
//        for (int i = 0; i <= V; i++) graph.add(new ArrayList<>());
//
//        for (int i = 0; i < E; i++) {
//            int v = sc.nextInt(), w = sc.nextInt();
//            graph.get(v).add(w);
//            graph.get(w).add(v);
//        }
//        sc.close();
//        solve(graph);
//        System.out.println(count);
//    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int[] arr = new int[n + 1];

        int ans = 0, x, y;
        for (int i = 0; i < m; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            if (arr[x] == 0 && arr[y] == 0) {
                arr[x] = ++ans;
                arr[y] = ans;
            } else if (arr[y] == 0) {
                arr[y] = arr[x];
            } else if (arr[x] == 0) {
                arr[x] = arr[y];
            } else {
                if (arr[x] != arr[y]) {
                    int temp = arr[y];
                    for (int j = 0; j <= n; j++) {
                        if (arr[j] == temp) arr[j] = arr[x];
                        if (arr[j] == ans) arr[j] = temp;
                    }
                    ans--;
                }
            }
        }
        for (int v = 1; v <= n; v++) {
            if (arr[v] == 0) ans++;
        }
        System.out.println(ans);
        sc.close();
    }
}
