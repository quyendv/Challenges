public class GCD_LCM {
    // tìm UCLN --> áp dụng cho cả số âm
    public int gcd(int a, int b) {
        if (b == 0) {   // cần a % b nên b cần != 0
            return a;
        }
        return Math.abs(gcd(b, a % b));
    }

    public int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
}
