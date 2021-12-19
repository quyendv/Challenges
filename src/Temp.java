import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


public class Temp {
    public static void main(String[] args) {
        int N = 50;
        Stack<Integer> stack = new Stack<Integer>();        // stack của algs4 trả về cách duyệt từ đỉnh về,
                                                            // ngược hoàn toàn với thư viện chuẩn java
        while (N > 0) {
            stack.push(N % 2);
            N = N / 2;
        }
        for (int d : stack)
            System.out.println(d);
        System.out.println();
//        for (int d : stack) StdOut.print(d);
//        StdOut.println();

    }
}
