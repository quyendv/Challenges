// Tìm dãy con tăng dài nhất

// DP: https://www.youtube.com/watch?v=CE2b_-XfVDk
// Link solution: description video: O(n^2)
/*
    if (arr[i] > arr[j]) dp[i] = dp[j] + 1    // i từ 0 đến n - 1, j từ 0 đến i - 1
    else mặc định bằng 1
 */

// Other Solution: https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/ (đọc để hiểu rõ hơn)
// Video: https://www.youtube.com/watch?v=S9oUiVYEq7E (cách này lưu giá index thôi, GFG lưu luôn giá trị, code dưới mô tả or GFG)
// newSolution: O(n.log(n))


// Thảo luận trên StackOverFlow: https://stackoverflow.com/questions/2631726/how-to-determine-the-longest-increasing-subsequence-using-dynamic-programming

package DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Longest_Increasing_Subsequence {       // (LIS)
    /************************************
     * Solution1 - DP: O(n^2)
     ***********************************/
    public static int longestIncreasingSubsequence(int[] arr) {
        int ans = -1;
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;

        // Nếu muốn in trực tiếp dãy con tăng đó ra ta có thể dùng 1 mảng phụ:
        // if arr[i] > arr[j]: beforeValue[i] = j (lưu index phần tử trước nối với phần tử hiện tại), sau đó in ngược về (dãy ngược), có thể dùng stack để cho xuôi
        // xem trong link
    }


    /******************************************************************************
     * Solution2 - using binarySearch: O(n.log(n))
     *****************************************************************************/
    // Cách này lưu giá trị, nếu lưu index sẽ dễ in ra LIS kết quả hơn
    public static int LIS(int[] arr) {
        int[] tailTable = new int[arr.length];
        int len;    // len of LIS

        tailTable[0] = arr[0];
        len = 1;

        Map<Integer, Integer> beforeValue = new HashMap<>();

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < tailTable[0]) {              // a[i] < all endValue of all active lists, we will start new active lists of length 1
                tailTable[0] = arr[i];
            } else if (arr[i] > tailTable[len - 1]) { // a[i] > all endValue of all active listsIS, we extend LIS with a[i]
                beforeValue.put(arr[i], tailTable[len - 1]);
                tailTable[len++] = arr[i];
            } else {                                  // a[i] is between, we will find a list with largest end element that is smaller than A[i] and discard all other lists of same length as that of this modified list.
                int ceilIndex = ceilIndex(tailTable, 0, len - 1, arr[i]);       // tìm từ [0, len - 1]
                beforeValue.put(arr[i], tailTable[ceilIndex - 1]);
                tailTable[ceilIndex] = arr[i];

            }
        }

        // Print LIS: nếu k muốn ngược có thể dùng đệ quy print ra, hoặc stack, dùng thêm mảng result như LCS, ...
        System.out.print("Reverse LIS: ");
        for (int i = tailTable[len - 1]; ; i = beforeValue.get(i)) {
            System.out.print(i + " ");
            if (!beforeValue.containsKey(i)) break;  // this is first value, hasn't beforeValue
        }
        System.out.println();
        return len;
    }

    private static int ceilIndex(int[] tailTable, int start, int end, int key) {
        // Nếu dùng Arrays.binarySearch hoàn toàn có thể gộp case 1 và 3 vào 1 luôn.
        // int index = Arrays.binarySearch(tailTable, start, end, key); // lưu ý khi truyền end vào hàm, nó bđ từ [start, end), nên truyền end + 1 (tức len)
        // if (index < 0) index = -index - 1;
        // return index;


        // tìm vị trí của key trong đoạn từ [start, end]. Truyền vào len - 1 là chuẩn nhất
        int mid;
        int len = end;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (mid < len && tailTable[mid] < key && key <= tailTable[mid + 1]) return mid + 1;
            else if (tailTable[mid] < key) start = mid + 1;
            else end = mid - 1;
        }
        return -1; // k xảy ra
    }

    // ***********************************************************************
    // Cải tiến cách 2: chỉ lưu index thôi, k lưu giá trị -> giúp cho việc in ra xâu kết quả tốt hơn
    // Hoặc vẫn lưu value nhưng dùng map để lưu beforeValue (không dùng mảng lưu trực tiếp giá trị vì có thể index sẽ quá lớn)
    // ***********************************************************************


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // int[] arr = {3, 4, -1, 6, 2, 3}; // res: 3 4 6 or -1 2 3 -> 3
        int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15}; // res: 0 2 6 9 11 15 -> 6
        System.out.println(longestIncreasingSubsequence(arr));
        System.out.println(LIS(arr));
    }
}
