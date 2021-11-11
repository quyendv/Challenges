// https://leetcode.com/problems/pascals-triangle-ii/
// in ra dòng thứ i trong tam giác Pascal, index từ 0
// tam giác pasca: dòng thứ i có i + 1 số (i từ 0)



package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pascal_Triangle_II_119 {
    // vì chỉ cần in ra dòng đó, nên nếu vẫn build list rồi trả về dòng thứ i thì tốn bộ nhớ -> chưa hiệu quả
    // -> chỉ cần 1 list hoặc arr với size là index + 1 và sửa trực tiếp trên list/arr đó

    // Array with fill: faster
    public List<Integer> getRow(int rowIndex) {
        Integer[] arr = new Integer[rowIndex + 1];
        Arrays.fill(arr, 0);
        arr[0] = 1;
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = i; j >= 1; j--) {
                arr[j] = arr[j] + arr[j - 1];
            }
        }
        return Arrays.asList(arr);
    }

    // List: mỗi row sẽ add thêm 1 ở cuối, sau đó set lại phần bên trong<khác viền> tức j : 1 -> i - 1
    public List<Integer> getRow1(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            ans.add(1);
            for (int j = i - 1; j >= 1; j--) {
                ans.set(j, ans.get(j) + ans.get(j - 1));
            }
        }
        return ans;
    }
}
