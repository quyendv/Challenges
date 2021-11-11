// https://leetcode.com/problems/pascals-triangle/
// tam giác pascal, dòng thứ i có i + 1 số (i từ 0)
/*
    j theo i: j <= i
  0  0
  1  0 1
  2  0 1 2
  3  0 1 2 3
  ...
*/

package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Pascal_Triangle_118 {
    // tham khảo: nếu j là viền thì add 1 còn trong lõi thì ...
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> level = new ArrayList<>();  // level is eachRow
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) level.add(1);
                else level.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
            }
            ans.add(level);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Pascal_Triangle_118().generate(5));
    }
}
