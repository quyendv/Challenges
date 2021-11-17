import java.util.Arrays;

public class HeapSort {
    // Lưu ý ta coi heap từ 1 đến size, các bước swap với less tự giảm index đi 1
    public static void sort(Comparable[] a) {
        // B1: tạo max heap từ mảng ban đầu:
        // sink các phần tử từ a/2 đến 1 (tính từ 1)
        // Lưu ý độ phức tạp chỉ O(n)?? --> sai vì sink n/2 Node mà mỗi lần sink tốn từ 1 -> logN ==> chỉ O(n) trong best-case
        int n = a.length;
        for (int k = n / 2; k >= 1; k--) {
            sink(a, k, n);
        }

        // B2: tách từng max ra khỏi heap.
        // Tức swap 1 với n: chuyển max về cuối mảng. Giảm size (tách ra khỏi heap).
        // sau đó sink(1)
        // Độ phức tạp: O(NlogN)
        int k = n;
        while (k > 1) {
            exch(a, 1, k--);
            sink(a, 1, k);
        }

        // -> O( N + NlogN) = O(NlogN)
        // stable: không??
        // inplace: true
    }

    private static void sink(Comparable[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(a, j, j + 1)) j++;
            if (!less(a, k, j)) break;
            exch(a, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i-1].compareTo(a[j-1]) < 0;
    }

    private static void exch (Comparable[] a, int i, int j) {
        Comparable temp = a[i-1];
        a[i - 1] = a[j - 1];
        a[j - 1] = temp;
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{1, 5, 4, 2, 3};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
