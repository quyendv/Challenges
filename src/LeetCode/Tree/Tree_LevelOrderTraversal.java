package LeetCode.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree_LevelOrderTraversal {
    static class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static void levelOrder(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node first = queue.poll();
                if (first.left != null) queue.offer(first.left);
                if (first.right != null) queue.offer(first.right);
                System.out.print(first.data + " ");
            }
        }
    }
}
