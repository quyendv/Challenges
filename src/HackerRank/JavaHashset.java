// https://www.hackerrank.com/challenges/java-hashset/problem
/*
    Bài này không khó, chỉ dễ bị lừa.
    Cho T query, mỗi lần nhập 2 String. Hãy in ra số query khác nhau sau mỗi lần
    -> ý tưởng dùng Set. Tuy nhiên nếu mỗi lần add(pair_left[i] + pair_right[i]) thì sẽ sai khi nhập cặp "ab" "c"
    và "a" "bc" -> chỉ cần thêm ký tự vào giữa
*/
package HackerRank;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class JavaHashset {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        String [] pair_left = new String[t];
        String [] pair_right = new String[t];

        for (int i = 0; i < t; i++) {
            pair_left[i] = s.next();
            pair_right[i] = s.next();
        }

        Set<String> set = new HashSet<>();
        for (int i = 0; i < t; i++) {
            set.add(pair_left[i] + " " + pair_right[i]);
            System.out.println(set.size());
        }
    }
}
