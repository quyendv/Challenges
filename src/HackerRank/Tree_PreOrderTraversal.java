package HackerRank;

import java.util.Stack;

public class Tree_PreOrderTraversal {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static void preOrder(Node root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void preOrder1(Node root) {
        if (root == null) return;
        Stack<Node> stk = new Stack<>();
        stk.push(root);
        while (!stk.empty()) {
            Node temp = stk.pop();
            System.out.print(temp.data + " ");
            if (temp.right != null) stk.push(temp.right);
            if (temp.left != null) stk.push(temp.left);
        }
    }

    public static void preOrder2(Node root) {
        Stack<Node> stk = new Stack<>();
        Node p = root;
        while (p != null || !stk.empty()) {
            if (p != null) {
                stk.push(p);
                System.out.print(p.data + " ");
                p = p.left;
            } else {
                p = stk.pop();
                p = p.right;
            }
        }
    }
}
