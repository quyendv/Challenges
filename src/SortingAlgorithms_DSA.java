// http://algs4.cs.princeton.edu/code/

import edu.princeton.cs.algs4.StdRandom;

public class SortingAlgorithms_DSA {
    private static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /* ********************************************************************************************************** */

    /**
     * Ý tưởng: Đổi chỗ các bọt (phần tử nhẹ/nhỏ hơn) lên trên, đến khi hết bọt
     * <p>
     * Best-case: O(n)
     * Worst-case: O(n^2)
     * <p>
     * Good: Sắp xếp xuôi, các giá trị bằng nhau
     * Bad: sắp xếp ngược
     * <p>
     * Lưu ý: TH có 1 (hoặc 1 vài) phần tử sai vị trí, chưa thể đánh giá được vì:
     * + Mỗi lần duyệt sẽ đưa phần tử lớn nhất về cuối mảng
     * + Nếu phần tử sai vị trí đó lớn nhất -> 1 lần duyệt là đưa đc về đúng vị trí (và thêm 1 lần check isSorted để kết thúc)
     * + Nếu phần tử sai vị trí đó lớn thứ 2 -> 2 lần duyệt (và thêm 1 lần check)
     * ... -> nếu nhỏ nhất thì tốn n lần duyệt như vậy --> không đánh giá được Good/Bad
     */

    public static void BubbleSort(int[] a) { // optimized: best-case: O(n), worst-case O(n^2)
        // int n = a.length;
        // for (int i = n - 1; i > 0; i--) {
        //     boolean isSorted = true;
        //     for (int j = 0; j < i; j++) {
        //         if (a[j] > a[j + 1]) {
        //             isSorted = false;
        //             int t = a[j];
        //             a[j] = a[j + 1];
        //             a[j + 1] = t;
        //         }
        //     }
        //     if (isSorted)
        //         break;
        // }

        // int n = a.length;
        // for (int i = 0; i < n - 1; i++) {
        //     boolean isSorted = true;
        //     for (int j = i + 1; j < n; j++) {
        //         if (a[j] < a[j - 1]) {
        //             a[j-1] += a[j] - (a[j] = a[j-1]);
        //             isSorted = false;
        //         }
        //     }
        //     if (isSorted) break;
        // }

        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    // swap a[j+1] and a[j]
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) break;
        }
    }

    /* ********************************************************************************************************** */


    /**
     * Ý tưởng: Chọn phần tử nhỏ nhất đưa về đầu mảng, lặp lại tương tự với n-1 phần tử còn lại
     * O(n^2) mọi TH: Do thuật toán luôn cần tìm phần tử nhỏ nhất
     * <p>
     * k nên dùng
     */
    public static void selectionSort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[minIdx])) minIdx = j;
            }
            // a[i] += a[minIdx] - (a[minIdx] = a[i]);
            exch(a, i, minIdx);
        }
    }



    /* ********************************************************************************************************** */


    /**
     * Ý tưởng: Chèn các phần tử vào mảng đã sắp xếp sao cho thứ tự sắp xếp không đổi
     * <p>
     * best case: khi mảng đã sắp xếp -> O(n) : tốn n - 1 phép so sánh
     * worst case: khi mảng sắp xếp ngược -> O(n^2) : ~1/2 n^2 phép so sánh và 1/2 n^2 phép đổi chỗ
     * average (randomly-ordered) : O(n^2) : ~1/4 n^2 phép so sánh và 1/4 n^2 phép đổi chỗ
     * <p>
     * Good: Sắp xếp xuôi, giá trị bằng nhau, có 1 vài phần tử sai vị trí (nearlySorted)
     * Bad: Sắp xếp ngược
     * <p>
     * insertionSort có tính ổn định
     */
    public static void insertionSort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) exch(a, j, j - 1);
                else break;
            }

            // gán như dưới sẽ chạy nhanh hơn

            // Comparable ai = a[i];
            // int j = i;
            // while (j > 0 && less(ai, a[j - 1])) {
            //     a[j] = a[j - 1]; j--;
            // }
            // a[j] = ai;
        }
    }



    /* ********************************************************************************************************** */


    /**
     * Ý tưởng: Liên tục thực hiện chia đôi mảng, sắp xếp nửa trái, nửa phải và trộn chúng lại thành 1 mảng sx hoàn chỉnh
     * Time complexity:
     * D(N) = 2 * D(N/2) + N : chia đôi LogN lần và trộn N lần
     * = 2N.logN
     * ==> O(N.logN)
     * Space Complexity: O(N)
     * <p>
     * MergeSort có tính ổn định
     */
    public static class MergeSort {
        private final int CUTOFF = 10;

        public void mergeSortBU(Comparable[] a) {  //bottom up
            int n = a.length;
            Comparable[] aux = new Comparable[n];
            for (int sz = 1; sz < n; sz += sz)
                for (int lo = 0; lo < n - sz; lo += 2 * sz)
                    merge(a, aux, lo, lo + sz - 1, Math.min(lo + 2 * sz - 1, n - 1));
        }

        public void mergeSort(Comparable[] a) {
            Comparable[] aux = new Comparable[a.length];
            sort(a, aux, 0, a.length - 1);
        }

        public void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
            if (hi <= lo + CUTOFF - 1) {
                insertionSort(a, lo, hi);
                return;
            }

            // if (hi <= lo) return;
            int mid = lo + (hi - lo) / 2;

            sort(a, aux, lo, mid);
            sort(a, aux, mid + 1, hi);
            if (!less(a[mid + 1], a[mid])) return;  // a[mid] <= a[mid + 1]
            merge(a, aux, lo, mid, hi);
        }

        // copy to aux[], merge from aux[] to a[]
        public void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
            for (int k = lo; k <= hi; k++) aux[k] = a[k];
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) {
                if (i > mid) a[k] = aux[j++];
                else if (j > hi) a[k] = aux[i++];
                else if (less(aux[i], aux[j])) a[k] = aux[i++];
                else a[k] = aux[j++];
            }
        }

        public void insertionSort(Comparable[] a, int lo, int hi) {
            for (int i = lo + 1; i <= hi; i++) {
                for (int j = i; j > lo; j--) {
                    if (less(a[j], a[j - 1])) exch(a, j, j - 1);
                    else break;
                }
            }
        }


        public void mergeSortX(Comparable[] a) {
            Comparable[] aux = a.clone();
            sortX(aux, a, 0, a.length - 1);
        }

        public void sortX(Comparable[] a, Comparable[] aux, int lo, int hi) {
            // if (hi <= lo) return;
            if (hi <= lo + CUTOFF - 1) {
                insertionSort(aux, lo, hi);
                return;
            }

            int mid = lo + (hi - lo) / 2;
            sortX(aux, a, lo, mid);
            sortX(aux, a, mid + 1, hi);

            if (!less(a[mid + 1], a[mid])) {
                System.arraycopy(a, lo, aux, lo, hi - lo + 1); // for (int i = lo; i <= hi; i++) aux[i] = a[i];
                return;
            }

            mergeX(a, aux, lo, mid, hi);
        }

        // no copy, merge from a[] to aux[]
        public void mergeX(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) {
                if (i > mid) aux[k] = a[j++];
                else if (j > hi) aux[k] = a[i++];
                else if (less(a[i], a[j])) aux[k] = a[i++];
                else aux[k] = a[j++];
            }
        }

    }



    /* ********************************************************************************************************** */


    /**
     * Ý tưởng: Thực hiện partitioning (phần vùng) mảng, sau đó thực hiện tương tự sắp xếp bên trái và phải của phần tử
     * được phân vùng vừa rồi cho đến khi mảng đc sắp xếp. Bước phân vùng: chọn pivot, sắp xếp mảng sao cho các phần tử < pivot
     * về bên trái và > pivot về bên phải < lưu ý: quickSort3way cải tiến mới là trái <= pivot <= phải >
     *
     *** Hoare * (khó hiểu nhưng nhanh hơn cách cài đặt Lomuto)
     *
     * Best case: liên tục chia mảng làm đôi -> ~NlogN (pivot luôn là median (trung vị) của đoạn mảng [lo, hi]
     * Worst case: Mảng đã sx || các phần tử trùng nhau -> ~1/2 n^2
     * average case: ~1.39 NlogN
     */
    public static class QuickSort {
        public int partition(Comparable[] a, int lo, int hi) {
            Comparable pivot = a[lo];
            int i = lo, j = hi + 1;
            while (true) {
                while (less(a[++i], pivot))
                    if (i == hi) break;

                while (less(pivot, a[--j]))
                    if (j == lo) break;  // dismiss

                if (i >= j)
                    break;    // a[i] > pivot , a[j] < pivot; i == j == hi || lo or i == j && a[i] = a[j] = pivot
                exch(a, i, j);
            }
            exch(a, lo, j);  // a[j] <= pivot
            return j;
        }

        public void sort(Comparable[] a, int lo, int hi) {
            // if (hi <= lo + CUTOFF - 1) { insertionSort(a, lo, hi); return; }
            if (hi <= lo) return;
            int pi = partition(a, lo, hi);
            sort(a, lo, pi - 1);
            sort(a, pi + 1, hi);
        }

        public void quickSort(Comparable[] a) {
            StdRandom.shuffle(a);
            sort(a, 0, a.length - 1);
        }

        /**
         * Tìm phần tử nhỏ thứ k (0 = min, a.length - 1 = max, ...)
         */
        public Comparable select(Comparable[] a, int k) {
            StdRandom.shuffle(a);
            int lo = 0, hi = a.length - 1;
            while (hi > lo) {
                int pi = partition(a, lo, hi);
                if (pi < k) lo = pi + 1;
                else if (pi > k) hi = pi - 1;
                else return a[k];
            }
            return a[k];
        }


        // xử lý TH nhiều khóa trùng
        public void quickSort3way(Comparable[] a) {
            StdRandom.shuffle(a);
            sort3way(a, 0, a.length - 1);
        }

        public void sort3way(Comparable[] a, int lo, int hi) {
            if (hi <= lo) return;
            int lt = lo, gt = hi;
            Comparable pivot = a[lo];
            int idx = lo + 1;  // idx = lo -> cmp == 0
            while (idx <= gt) {
                int cmp = a[idx].compareTo(pivot);
                if (cmp < 0) exch(a, lt++, idx++);
                else if (cmp > 0) exch(a, idx, gt--); // not idx++
                else idx++;
            }
            // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
            sort3way(a, lo, lt - 1);
            sort3way(a, gt + 1, hi);
        }
    }

    /* ********************************************************************************************************** */


    public static class Date implements Comparable<Date> {
        private final int day, month, year;

        public Date(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;
        }

        @Override
        public int compareTo(Date o) {
            if (this.year < o.year) return -1;
            if (this.year > o.year) return 1;
            if (this.month < o.month) return -1;
            if (this.month > o.month) return 1;
            if (this.day < o.day) return -1;
            if (this.day > o.day) return 1;
            return 0;
        }
    }

    public static void main(String[] args) {
        Comparable[] a = {1, 9, 3, 2, 5, 6, 7, 24, 5, 6, 2, 35, 40};
//        selectionSort(a);
//        insertionSort(a);

//        MergeSort ms = new MergeSort();
//        ms.mergeSortBU(a);

        QuickSort qs = new QuickSort();
        qs.quickSort3way(a);
        for (var i : a) System.out.print(i + " ");
    }
}
