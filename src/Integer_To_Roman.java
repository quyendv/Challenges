// Chuyển từ số nguyên sang số La Mã
// https://algorithms.tutorialhorizon.com/convert-integer-to-roman/

public class Integer_To_Roman {
    public static String convert(int num) {
        // chưa xử lý số âm

        // nên khởi tạo ra ngoài để tránh mỗi lần convert lại init lại
        String[] Roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < Roman.length; i++) {
            while (num >= values[i]) {
                ans.append(Roman[i]);
                num -= values[i];
            }
        }
        return ans.toString();
    }

    public static String convert1(int num) {
        String[] Roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < Roman.length; i++) {
            if (num >= values[i]) {
                ans.append(Roman[i].repeat(num/values[i]));
                num %= values[i];
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert(4379));
    }
}
