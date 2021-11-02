// Tính exp(x) = 1 + x + x^2 / 2! + x^3 / 3! + ... + x^n / n! (Ban đầu ans = 1 và temp = x luôn -> ans += temp dần lên)
// Nhập x và tính <không có n>: điều kiện dừng cộng x^i / i! khi nó nhỏ hơn 1 ngưỡng sai số nào đó <có thể tùy chọn hoặc đề ra>

// Tương tự với sinx = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ... 
//              cosx = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ... Với x là số thực chuyển sang Radian bằng Math.toRadians(..)

import java.util.*;

public class ExpX {
    public static double expX(int x) {
        double ans = 1;
        int i = 1;
        double temp = x; // temp = x^i / i!
        while (temp > 1E-10) { // 10^-12, sai số tự chọn càng nhỏ càng chính xác: nhỏ hơn sai số thì dừng
            ans += temp;
            temp = temp * x / ++i;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        System.out.println(expX(x));
        sc.close();
    }
}
