// https://www.hackerrank.com/challenges/torque-and-development/problem
/**
 * Bài toán: Nhập q truy vấn, mỗi truy vấn gồm:
 * + dòng đầu tiên chứa n = số cities, m = cities[] size (cặp connected), c_lib, c_road. Lưu ý city đánh số từ 1 -> n
 * + m dòng tiếp theo mỗi dòng là 1 cặp số nguyên (cities[i][2]) tương đương với 2 thành phố a và b liên thông (tức a và b có thể xây đường
 * nối 2 thành phố lại, còn không liên thông thì k thể xây đường)
 * Hỏi chi phí tối thiểu xây dựng đường, thư viện để tất cả thành phố đều có thể đến thư viện (nếu thành phố đó có thư viện hoặc có
 * đường đến thành phố khác có thư viện)
 * --> giống cho đồ thị vô hướng, ta cần làm 1 trong 2 nhiệm vụ (đường or thư viện) sao cho chi phí tốn ít nhất (tuyệt đối k được hiểu đây
 * là 1 đồ thị có hướng, vì ta xây đường đi lại giữa 2 thành phố)
 * <p>
 * Ý tưởng: Giả sử ta có 2 cities a và b. chắc chắn cần có ít nhất 1 thư viện (giả sử tại a). như vậy ta cần chọn xây đường đến b hay xây
 * luôn thư viện tại b.
 * ==> Nếu c_lib nhỏ hơn c_road thì xây luôn thư viện, cần gì đường
 * ==> Nếu c_road nhỏ hơn thì ta sẽ xây đường: ta tính số thành phố (vertex) của từng vùng liên thông = numVertex, sau đó ta cộng chi phí
 * từng vùng liên thông đó lại (path) : += (numVertex - 1) * c_road + c_lib. (Vì mỗi vùng liên thông cần ít nhất 1 thư viện (chỉ cần 1 là đủ)
 * và ta tính đỉnh rồi suy ra cạnh có vẻ dễ code hơn là tính trực tiếp cạnh, nhưng vẫn tính được)
 */

package HackerRank;

import java.util.ArrayList;
import java.util.Scanner;

public class Roads_and_Libraries_MEDIUM {

    static long minCost(ArrayList<ArrayList<Integer>> graph, int n, int c_lib, int c_road) {
        if (c_lib < c_road) return (long) c_lib * n;
        boolean[] visited = new boolean[n + 1]; // or graph.size(), không dùng biến cục bộ (thuộc tính class) vì mỗi query cần reset lại
        long minCost = 0; // chi phí tối thiểu cho tất cả các vùng liên thông path

        for (int i = 1; i <= n; i++) {  // vertex i-th. Ta duyệt tương tự như bài tìm maxVertex của từng vùng liên thông
            if (!visited[i]) {
                int countVertexPath = countConnected(graph, i, visited);               // Số đỉnh mỗi path
                minCost += c_lib + (long) c_road * (countVertexPath - 1);              // cost += chi phí mỗi path
            }
        }
        return minCost;
    }

    static int countConnected(ArrayList<ArrayList<Integer>> graph, int v, boolean[] visited) { // tính số đỉnh của path tính từ v (chứa v)
        int count = 1;
        visited[v] = true;
        for (int neighbor : graph.get(v)) {
            if (!visited[neighbor]) {
                count += countConnected(graph, neighbor, visited);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        while (q-- > 0) {
            int n = sc.nextInt(), m = sc.nextInt(), c_lib = sc.nextInt(), c_road = sc.nextInt();
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>()); // dismiss 0, from 1 to n
            }
            for (int i = 0; i < m; i++) { // number of connected components
                int a = sc.nextInt();
                int b = sc.nextInt();
                graph.get(a).add(b);
                graph.get(b).add(a);
            }
            System.out.println(minCost(graph, n, c_lib, c_road));
        }
    }
}
