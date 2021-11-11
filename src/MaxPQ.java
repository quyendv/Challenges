/*
    - Với cài đặt arr index từ 1, nếu Node con là k thì Node cha là k/2,
    Tương tự với Node cha là K thì node con là 2K và 2K + 1. Last parent node = Math.floor(n / 2)

    - Với index từ 0, Node cha K sẽ có 2 node con là 2K + 1 và 2K + 2
    Node cha sẽ là Math.floor((k - 1) / 2). Last parent node = Math.floor((n-1) / 2)

    - Node lá (leaf) là những Node sau Last parent Node.
*/
public class MaxPQ<Key extends Comparable<Key>> { // generic class
    private Key[] pq;
    private int n;

    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * B1: Thêm vào cuối mảng O(1)
     * B2: swim nó lên (tối đa logN) vì tối đa bơi từ cuối lên root (height : logN)
     */
    public void insert(Key val) {
        pq[++n] = val;
        swim(n);
    }

    /**
     * lưu giá trị root để cuối return  ~1
     * swap node cuối vs đầu. giảm size ~1
     * sink(1)   ~tối đa logN
     * gán phần tử cuối bằng null tránh thất thoát bộ nhớ
     * return
     */
    public Key delMax() {
        Key max = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        return max;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;  // children of node at k
            if (j < n && less(j, j + 1)) j++; // j + 1 <= n
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
}
