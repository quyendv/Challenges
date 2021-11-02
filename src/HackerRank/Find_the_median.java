// https://www.hackerrank.com/challenges/find-the-median/problem

package HackerRank;

import java.util.List;

public class Find_the_median {
    public static int findMedian(List<Integer> arr) {
        // Write your code here
        int lo = 0, hi = arr.size() - 1;
        int k = arr.size() / 2; // <=> (arr.size() - 1) / 2 + 1 - 1 = (arr.size() - 1) / 2;
        // vì size = 2n + 1, k là phần tử thứ n + 1 và index = n (0->2n) <size chẵn phải lấy trung bình 2 số giữa>
        while (lo < hi) {
            int pi = partition(arr, lo, hi);
            if (pi < k) lo = pi + 1;
            else if (pi > k) hi = pi - 1;
            else return arr.get(k);
        }
        return arr.get(k);
    }

    public static int partition(List<Integer> arr, int lo, int hi) {
        int pivot = arr.get(lo);
        int i = lo, j = hi + 1;
        while (true) {
            while (arr.get(++i) < pivot) if (i == hi) break;
            while (arr.get(--j) > pivot) if (j == lo) break;
            if (i >= j) break;
            swap(arr, i, j);
        }
        swap(arr, j, lo);
        return j;
    }

    public static void swap(List<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
}
