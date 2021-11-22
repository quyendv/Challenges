import java.util.Scanner;

public class Solution4 {

    public static void insertionSort(int[] arr, int lo, int hi) {
        if (arr.length < 2) return;
        if (lo >= hi) return;
        for (int i = lo; i <= hi; i++) {
            int j = i;
            while (j > lo && arr[j] < arr[j-1]) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        insertionSort(arr, 1, n - 2);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
