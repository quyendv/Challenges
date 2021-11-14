// https://www.youtube.com/watch?v=GhKJcr1Fri8&list=PLoaAbmGPgTSNMAzkKBHkh2mLuBk54II5L&index=28

package LeetCode.Tree.Implement;

import java.util.*;

class Node<T extends Comparable<T>> {
    T data;
    Node<T> left;
    Node<T> right;

    public Node(T data) {
        this(data, null, null);
    }

    public Node(T data, Node<T> left, Node<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return data.toString();
    }
}

/*
 * Cây tìm kiếm nhị phân (BST) là một cấu trúc dữ liệu cây nhị phân dựa trên nút có các thuộc tính sau:
 * + Cây con bên trái của một nút chỉ chứa các nút có khóa nhỏ hơn khóa của nút đó
 * +  Cây con bên phải của một nút chỉ chứa các nút có khóa lớn hơn khóa của nút đó
 * + Cả cây con bên trái và bên phải cũng phải là cây tìm kiếm nhị phân

 * Cây nhị phân hoàn chỉnh:
 *  + số Node ở độ cao h là 2^h
 *  + tổng số node là 2^0 + 2^1 + 2^2 + ... 2^h = 2^(h+1) - 1
 * Cây không hoàn chỉnh thì số Node là 2^h (tức 2^0 + 2^1 + ... 2^ (h - 1) + 1) -> 2^(h+1) - 1
 */
public class BinarySearchTree<T extends Comparable<T>> implements TreeADT<T> {
    private Node<T> root;
    private int nodeCount = 0;

    public BinarySearchTree() {
        nodeCount = 0;
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return nodeCount;   // hoặc duyệt và đếm, nhưng k cần thiết và tốn tgian
    }

    @Override
    public int height() {
        return height(root);
    }

    @Override
    public boolean contains(T elem) {
        return contains(root, elem);
    }

    @Override
    public boolean add(T elem) {
        if (contains(elem)) return false;
        root = add(root, elem);
        nodeCount++;
        return true;
    }

    @Override
    public boolean remove(T elem) {
        if (!contains(elem)) return false;
        root = remove(root, elem);
        nodeCount--;
        return true;
    }

    @Override
    public Iterator<T> traverse(TreeTraverseType type) {
        switch (type) {
            case PRE_ORDER:
                return preOrderTraverse();
            case IN_ORDER:
                // return inOrderTraverse();
            case POST_ORDER:
                // return postOrderTraverse();
            case LEVEL_ORDER:
                // return levelOrderTraverse();
            default:
                return null;
        }
    }

    @Override
    public Iterable<T> traversalIterable(TreeTraverseType type) {
        switch (type) {
            case PRE_ORDER:
                return preOrderList();
            case IN_ORDER:
                return inOrderList();
            case POST_ORDER:
                return postOrderList();
            case LEVEL_ORDER:
                // return levelOrderList(); // xem bên MyBST
            default:
                return null;
        }
    }

    private Iterable<T> preOrderList() {
        List<T> ans = new ArrayList<>();
        preOrderList(root, ans);
        return ans;
    }

    private void preOrderList(Node<T> node, List<T> ans) {
        if (node == null) return;
        ans.add(node.data);
        preOrderList(node.left, ans);
        preOrderList(node.right, ans);
    }

    private Iterable<T> inOrderList() {
        List<T> ans = new ArrayList<>();
        inOrderList(root, ans);
        return ans;
    }

    private void inOrderList(Node<T> node, List<T> ans) {
        if (node == null) return;
        inOrderList(node.left, ans);
        ans.add(node.data);
        inOrderList(node.right, ans);
    }

    private Iterable<T> postOrderList() {
        List<T> ans = new ArrayList<>();
        postOrderList(root, ans);
        return ans;
    }

    private void postOrderList(Node<T> node, List<T> ans) {
        if (node == null) return;
        postOrderList(node.left, ans);
        postOrderList(node.right, ans);
        ans.add(node.data);
    }

    private Iterator<T> preOrderTraverse() {
        final int expectedCount = nodeCount;
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedCount != nodeCount) throw new ConcurrentModificationException();

                return root != null && !stack.empty();
            }

            @Override
            public T next() {
                if (expectedCount != nodeCount) throw new ConcurrentModificationException();

                Node<T> cur = stack.pop();
                if (cur.right != null) stack.push(cur.right); // xử lý sau -> push trước
                if (cur.left != null) stack.push(cur.left);   // xử lý trước -> push sau
                return cur.data;
            }
        };
    }

    private Iterator<T> inOrderTraverse() {
        // TODO:
        return null;
    }

    private Iterator<T> postOrderTraverse() {
        // TODO:
        return null;
    }

    private Iterator<T> levelOrderTraverse() {
        // TODO:
        return null;
    }

    private boolean contains(Node<T> node, T elem) {
        if (node == null) return false;
        int comp = elem.compareTo(node.data);
        if (comp < 0) return contains(node.left, elem);
        if (comp > 0) return contains(node.right, elem);
        return true;
    }

    /**
     * Tùy theo đề bài. chiều cao có thể là tổng số Node theo đường dài nhất từ root tới lá
     * Có bài cho là số cành từ root tới Node lá xa nhất --> thấy đúng hơn vì root có h = 0
     */
    private int height(Node<T> node) {
        if (node == null) return 0; // chỉ cần sửa return -1 cho TH 2
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private Node<T> add(Node<T> node, T elem) {
        if (node == null) return new Node<>(elem);
        if (elem.compareTo(node.data) < 0) {
            node.left = add(node.left, elem);
        }
        if (elem.compareTo(node.data) > 0) {
            node.right = add(node.right, elem);
        }
        return node;
    }

    private Node<T> remove(Node<T> node, T elem) {
        // find
        if (node == null) return null;
        if (elem.compareTo(node.data) < 0) {
            node.left = remove(node.left, elem);
        } else if (elem.compareTo(node.data) > 0) {
            node.right = remove(node.right, elem);
        } else {    // equals -> remove

            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            T minRight = min(node.right);
            node.data = minRight;
            node.right = remove(node.right, minRight);
        }
        return root;
    }

    private T min(Node<T> node) {
        while (node.left != null) node = node.left;
        return node.data;
    }

    public static void main(String[] args) {
        BinarySearchTree<String> BST = new BinarySearchTree<>();
        BST.add("D");
        BST.add("C");
        BST.add("F");
        BST.add("A");
        BST.add("E");
        /*
                        D
                  C           F
               A    null    E    null
        */

        // Iterator<String> i = BST.traverse(TreeTraverseType.PRE_ORDER);
        // while (i.hasNext()) {
        //     System.out.println(i.next());
        // }

        for (String i : BST.traversalIterable(TreeTraverseType.POST_ORDER)) {   // iterable
            System.out.println(i);
        }
    }
}
