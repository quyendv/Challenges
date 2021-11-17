// https://www.geeksforgeeks.org/window-sliding-technique/

// Ý tưởng tổng quát: tìm tổng dãy con có k phần tử sao cho tổng lớn nhất
// VD: arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20}, k = 4 ==> Output: 39 (4, 2, 10, 23)

// Có thể vận dụng cho bài toán tìm subString(tức đoạn xâu liên tiếp) k chứa kí tự trùng --> tuy nhiên k tối ưu, k nên dùng
//      https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/

// Nhớ thêm bài tìm dãy con chung dài nhất có tổng lớn nhất: Maximum_SubArray_53 (leetcode)

public class Window_Sliding_Technique {
    public static int windowSliding(int[] arr, int k) {
        if (arr.length < k) {
            System.out.println("Invalid!");
            return -1;
        }

        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }

        int max_sum = sum;
        for (int i = k; i < arr.length; i++) {
            sum = sum + arr[i] - arr[i - k];
            max_sum = Math.max(sum, max_sum);
        }
        return max_sum;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {5, 2, -1, 0, 3};
        System.out.println(windowSliding(arr, 3));
    }
}
