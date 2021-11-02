import java.util.*;

public class SortingAlgorithm {
    public static void printArray(int i, int[] a) {
        System.out.print(i + ": ");
        for (int value : a)
            System.out.print(value + " ");
        System.out.println();
    }

    public static void BubbleSort(int[] a) { // optimized: O(n^2)
        int n = a.length;
        for (int i = n - 1; i > 0; i--) {
            boolean isSorted = true;
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    isSorted = false;
                    int t = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = t;
                }
            }
            if (isSorted)
                break;
            printArray(n - i - 1, a);
        }
    }

    public static void InsertionSort(int[] a) { // O(n^2)
        for (int i = 1; i < a.length; i++) {
            int j = i - 1, ai = a[i];
            while (j >= 0 && a[j] > ai) {   // tóm lại là swap về
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = ai;
            printArray(i - 1, a);
        }
    }

    public static void SelectionSort(int[] a) { // O(n^2)
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[minIndex])
                    minIndex = j;
            }
            if (minIndex != i) {
                // Java k có con trỏ + tham chiếu nên k thể viết hàm swap như C++

                // int t = a[i];
                // a[i] = a[minIndex];
                // a[minIndex] = t;
                a[i] += a[minIndex] - (a[minIndex] = a[i]); // swap(a, b): a = b - (b = a)
            }
            printArray(i, a);
        }
    }

    public static int[] mergeSort(int[] a, int L, int R) { // O(n * log2(n)) nhanh hơn với n lớn
        // TH đặc biệt & ĐK dừng
        if (L > R) // có thể bỏ vì k xảy ra
            return new int[0]; // có thể bỏ vì k xảy ra
        if (L == R) {
            // int[] singleElement = { a[L] };
            // return singleElement;
            return new int[] { a[L] }; // cách trả về mảng
        }

        // Chia
        int k = L + (R - L) / 2;  // (L + R) / 2; bản chất giống nhau nhưng L + R có thể tràn số nên k nên dùng
        int[] a1 = mergeSort(a, L, k);
        int[] a2 = mergeSort(a, k + 1, R);

        // Tron: nên tách thành hàm riêng
        int n = a1.length + a2.length;
        int[] result = new int[n];
        for (int i = 0, i1 = 0, i2 = 0; i < n; i++) {
            if (i1 < a1.length && i2 < a2.length) { // Nếu a1, a2 != rỗng <hoặc chưa đến cuối mảng>
                if (a1[i1] <= a2[i2])
                    result[i] = a1[i1++];
                else
                    result[i] = a2[i2++];
            } else { // a1 or a2 == rỗng <hoặc đén cuối mảng>
                if (i1 < a1.length)
                    result[i] = a1[i1++];
                else
                    result[i] = a2[i2++];
            }
        }
        return result;
    }

    public static void swap(int[] a, int i, int j) { // chỉ dùng hoán đổi 2 phần tử trong hàm
        a[i] += a[j] - (a[j] = a[i]);
    }

    public static int partition(int[] arr, int L, int H) {
        int pivot = arr[H];
        int i = L - 1;
        for (int j = L; j < H; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, H);
        return i + 1;
    }

    // Lomuto
    public static void QuickSort(int[] arr, int L, int H) { // pick last element as pivot
        if (L < H) {
            int pi = partition(arr, L, H);
            QuickSort(arr, L, pi - 1);
            QuickSort(arr, pi + 1, H);
        }
    }

    public static void main(String[] args) {

        int[] a = { 5, 7, 2, 6, 4, 3, 1 };

        // int[] b = { 1, 2, 3, 4, 5, 7, 6 };
        // BubbleSort(a);
        // BubbleSort(b);

        // InsertionSort(a);

        // SelectionSort(a);

        // int[] c = { 1, 5, 3, 2, 8, 7, 6, 4 };
        // System.out.println(Arrays.toString(mergeSort(c, 0, a.length - 1)));

        QuickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
