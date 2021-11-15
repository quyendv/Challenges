/*
        102. Binary Tree Level Order Traversal
        https://leetcode.com/problems/binary-tree-level-order-traversal/discuss/33450/Java-solution-with-a-queue-used
        https://leetcode.com/problems/binary-tree-level-order-traversal/discuss/33445/Java-Solution-using-DFS

        GFG: https://www.geeksforgeeks.org/level-order-tree-traversal/
        Các loại Traversal khác: https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
 */

package HackerRank;

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
