// https://www.hackerrank.com/challenges/connected-cell-in-a-grid/problem
/*
** Đề bài: cho ma trận N * M (row x col) chứa các phần tử dạng 0 or 1. Hai số 1 được gọi là liên thông với nhau nếu nó nằm
trong phạm vi 8 ô xung quanh hợp lệ của số còn lại. Các chuỗi số 1 liên thông với nhau tạo thành 1 vùng.
--> Yêu cầu tìm số các số 1 liên thông với nhau trong vùng lớn nhất.
--> Ý tưởng giải như bên dưới của cô Châu:
 + nếu 1 ô là số 1 thì ta đánh dấu visited ô đó = true. sau đó duyệt các ô xung quang liên thông mà chưa được visited (các ô
có giá trị 1 nhưng visited = false) tức dfs hết cả vùng liên thông đó -> trả về kết quả của vùng chứa số 1 đó (chắc chắn sẽ
duyệt hết cả vùng đó)
 + trong hàm main ta duyệt toàn mảng grid đầu vào, nếu phần tử nào = 1 và !visited thì tính số phần tử vùng liên thông chứa số đó.
Tiếp tục tìm phần tử số 1 nào đó ở VÙNG KHÁC(vì vùng trước sẽ được duyệt hết rồi) và so sánh các vùng rồi in ra max
** Khung solution tham khảo của cô Châu:
Tóm tắt: Cho mảng hai chiều với các phần tử (ô) có giá trị là 0 hoặc 1. Hai phần từ được gọi là kết nối (connected) với nhau nếu
chúng cùng có giá trị là 1 và ở sát nhau theo chiều ngang, chiều dọc và chéo cạnh. Như thế mỗi ô có thể có nhiều nhất 8 ô ở kề nối với nó.
Các ô kết nối với nhau tạo thành vùng. Hãy tìm và in ra số phần tử của vùng lớn nhất.
Gợi ý: Xem đây là một đồ thị với đỉnh là các ô, và hai cạnh nối với nhau nếu 2 ô là kết nối (connected). Với mỗi đỉnh (ô), dùng thuật
toán duyệt theo chiều sau (DFS) hoặc duyệt theo chiều rộng (BFS) để lan ra hết các đỉnh (ô) liên thông với nó. Khi duyệt, nếu đỉnh nào
đã thăm rồi thì không duyệt nữa. Nói cách khác, đây là bài toán tìm thành phần liên thông của đồ thị.
Mã nguồn: Sinh viên có thể sử dụng phần chương trình sau: https://drive.google.com/file/d/0B7mScmczCiHWRGUwWFBITmFNdWc/view?resourcekey=0-9skcsa6_uslMsQ3meZHyvg
        <bài bên dưới làm thêm từ link mẫu>
trong đó viết sẵn việc nhập dữ liệu, khai báo mảng các ô grid[][] cũng như mảng lưu trạng thái các ô đã được thăm visited[][] chưa.
Sinh viên cần viết nốt phần hàm static int count_connected(int row, int col) đếm số ô của thành phần liên thông với ô có toạ độ (row, col). Trong hàm này cũng cần cập nhật/đánh dấu các ô đã thăm để không duyệt nữa vào mảng visited[][] được khai báo ở ngoài hàm.
 */

package HackerRank;

import java.util.Scanner;

public class Connected_Cells_in_a_Grid_MEDIUM {

    static int[][] grid;  // matrix N * M
    static boolean[][] visited;
    static int N, M;      // N: numRows, M: numCols

    // helper:
    static int[] _x = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int[] _y = {-1, -1, -1, 0, 1, 1, 1, 0};

    static boolean checkPos(int x, int y) {
        return (x >= 0 && x < N) && (y >= 0 && y < M);
    }

    static int count_connected(int row, int col) {      // dfs
        int cnt = 0;
        // Write your code here

        visited[row][col] = true;
        cnt++;
        for (int i = 0; i < 8; i++) {
            int x = row + _x[i];
            int y = col + _y[i];
            if (checkPos(x, y) && grid[x][y] == 1 && !visited[x][y]) {
                cnt += count_connected(x, y);
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        grid = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                grid[i][j] = scanner.nextInt();
                visited[i][j] = false;
            }
        }
        int max = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (grid[i][j] == 0 || visited[i][j]) continue;
                int cnt = count_connected(i, j);
                if (max < cnt) max = cnt;
            }
        }

        System.out.println(max);
    }
}