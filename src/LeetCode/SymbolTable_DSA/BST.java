// https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/BST.java.html

package LeetCode.SymbolTable_DSA;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/*
 * Cây tìm kiếm nhị phân (BST) là một cấu trúc dữ liệu cây nhị phân dựa trên nút có các thuộc tính sau:
 * + Cây con bên trái của một nút chỉ chứa các nút có khóa nhỏ hơn khóa của nút đó
 * + Cây con bên phải của một nút chỉ chứa các nút có khóa lớn hơn khóa của nút đó
 * + Cả cây con bên trái và bên phải cũng phải là cây tìm kiếm nhị phân

 * Cây nhị phân hoàn chỉnh:
 *  + số Node ở độ cao h là 2^h
 *  + tổng số node là 2^0 + 2^1 + 2^2 + ... 2^h = 2^(h+1) - 1
 * Cây không hoàn chỉnh thì số Node là 2^h (tức 2^0 + 2^1 + ... 2^ (h - 1) + 1) -> 2^(h+1) - 1
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int size;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.left = null;
            this.right = null;
        }
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.value;
        }
        return null;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.value = val;
        x.size = 1 + size(x.left) + size(x.right); // có thể đặt vào hàm updateSize để dùng nhiều chỗ
        return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right; // Node trái cùng bên trái
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left; // Node phải cùng bên phải
        x.right = deleteMax(x.right);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;

            // C1:
            // Node minRight = min(x.right);
            // x.key = minRight.key;
            // x.value = minRight.value; // key & value of x = minRight
            // x.right = delete(x.right, key);

            // C2:
            Node minRight = min(x.right);
            minRight.right = delete(x.right, key);
            minRight.left = x.left;  // gán left sau để bước delete bên trên k ảnh hưởng
            x = minRight;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        // if (x == null) return null; // không cần vì khi gọi hàm thì BST !empty
        while (x.left != null) x = x.left;
        return x;
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        // if (x == null) return null; // không cần vì khi gọi hàm thì BST !empty
        while (x.right != null) x = x.right;
        return x;
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key; // x phải != null
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key); // floor(key) <= key -> cần cmp >= 0
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        // else: khi nhỏ hơn thì cần tìm lớn nhất
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key); // ceiling(key) >= key -> cần cmp <= 0
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        // else: khi lớn hơn thì cần tìm nhỏ nhất
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    /**
     * Có bao nhiêu key NHỎ hơn key đã cho?
     */
    public int rank(Key key) {
        return rank(key, root);
    }

    // làm gần giống floor
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return size(x.left);
        if (cmp < 0) return rank(key, x.left);
        // else:
        return 1 + size(x.left) + rank(key, x.right); // left nhỏ hơn, right phải check tiếp
    }

    // Lấy phần tử nhỏ thứ rank trong tree (đánh số từ 0). Có n Node và rank sẽ từ 0 -> n - 1.
    // Số nhỏ thứ rank tức có rank số nhỏ hơn nó (từ 0 đến rank - 1)
    public Key select(int rank) {
        if (rank < 0 || rank >= size()) throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        return select(root, rank);
    }

    /**
     * Luôn check leftSize vs rank. Mục tiêu: tìm Node có leftSize == rank
     * + leftSize == rank -> có rank Node nhỏ hơn x từ 0 -> rank - 1 => x nhỏ thứ rank.
     * + leftSize > rank  -> gọi hàm đến bên trái để leftSize giảm dần đến rank
     * + leftSize < rank  -> nhỏ hơn thì lại gọi hàm bên phải (x.right, leftSize - rank - 1 <-> vì có leftSize + 1 số nhỏ hơn
     * Node thứ rank rồi)) để kiếm thêm Node nhỏ hơn Node thứ rank và > Node hiện tại. VD cần tìm số nhỏ thứ 9 (tức phải có 9 số
     * nhỏ hơn nó từ 0 đến 8. Node hiện tại có leftSize = 6 < rank => cần tìm thêm 3 Node nhỏ hơn nữa (bên phải))
     */
    private Key select(Node x, int rank) {
        if (x == null) return null;
        int leftSize = size(x.left);
        if (leftSize == rank) return x.key; // tức có rank node nhỏ hơn nó rồi
        if (leftSize > rank)
            return select(x.left, rank); // tức đang là Node nhỏ thứ leftSize > rank -> cần tìm số nhỏ hơn
        // else:
        return select(x.right, rank - leftSize - 1);
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;   // tính theo số cạnh từ root tới leaf xa nhất
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new LinkedList<>();
        if (root == null) return keys;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node x = queue.poll();
                if (x.left != null) queue.offer(x.left);
                if (x.right != null) queue.offer(x.right);
                keys.offer(x.key);
            }
        }
        return keys;
    }

    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        bst.put(1, "one");
        bst.put(5, "five");
        bst.put(3, "three");
        bst.put(2, "two");
        bst.put(4, "four");
        for (var i : bst.levelOrder())
            System.out.print(i + " ");
        System.out.println('\n');
        System.out.println(bst.contains(10));
        System.out.println(bst.floor(5));
    }
}
