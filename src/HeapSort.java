import java.util.Arrays;
/*
** Heap Sort: Thuật toán duy nhất được học vừa inplace, vừa đảm bảo O(n.logn) trong mọi TH
- Ý tưởng: Biến đổi mảng thành 1 MaxBinaryHeap, sau đó tách từng max ra khỏi Heap
- Complexity:
	+ Space: O(1) -> k cần thêm bộ nhớ -> inplace
	+ Time: Đưa mảng về binary heap (sink lần lượt các Node cha, mỗi lần tối đa LogN --> O(n.logn) nhưng
	tốt nhất có thể đạt O(n) khi sink O(1)). Sau đó xóa từng max khỏi heap (xóa khỏi heap chứ k xóa bằng null): swap last node với
	root + sink(new root): O(n.logn)
	==> O(N.logN)
- Stable: Not stable
- Inplace: true.
 */
public class HeapSort {
    // Lưu ý ta coi heap từ 1 đến size, các bước swap với less tự giảm index đi 1
    public static void sort(Comparable[] a) {
        // B1: tạo max heap từ mảng ban đầu:
        // sink các phần tử từ a/2 đến 1 (tính từ 1)
        // Lưu ý độ phức tạp tối đa O(n) trong worstCase: các parent chỉ 1 + 2 + ... + h -> tuyến tính O(n)
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
