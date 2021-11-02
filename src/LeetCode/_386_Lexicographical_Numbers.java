// https://leetcode.com/problems/lexicographical-numbers/
// Nhập n: trả về list từ 1 đến n theo thứ tự từ điển: n = 25 -> 1 10 11 12 ... 2 20 21 22.. 3 4 5...
package LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class _386_Lexicographical_Numbers {
    public static List<Integer> lexicalOrder1(int n) {
        List<Integer> l = new ArrayList<>();
        for (int i = 1; i <= n; i++) l.add(i);
        // Collections.sort(l, new Comparator<>() {
        //     public int compare(Integer a, Integer b) {
        //         return a.toString().compareTo(b.toString());
        //     }
        // });

        // Collections.sort(l, (Integer a, Integer b) -> {
        //     return a.toString().compareTo(b.toString());
        // });

        // Collections.sort(l, (a, b) -> {
        //     return a.toString().compareTo(b.toString());
        // });

        // l.sort((a, b) -> {
        //     return a.toString().compareTo(b.toString());
        // });

        // l.sort((a, b) -> a.toString().compareTo(b.toString()));

        l.sort(Comparator.comparing(Object::toString));

        return l;
    }

    public static List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        helper(res, 0, n);
        return res;
    }

    public static void helper(List<Integer> res, int curNumber, int n) {
        for (int i = 0; i <= 9; i++) {
            int tmp = curNumber * 10 + i;
            if (tmp > 0 && tmp <= n) {
                res.add(tmp);
                helper(res, tmp, n);
            } else if (tmp > n) break;
        }
    }

    public static void main(String[] args) {
        System.out.println(lexicalOrder(25));
    }
}
