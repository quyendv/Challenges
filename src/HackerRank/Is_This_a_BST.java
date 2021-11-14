// https://www.hackerrank.com/challenges/is-binary-search-tree/problem
// https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/
// Xem thêm ở leetcode

package HackerRank;

import java.util.Stack;

public class Is_This_a_BST {
    static class Node {
        int data;
        Node left;
        Node right;
    }

    /* *****************************
     * Recursion.
     ****************************** */
    boolean checkBST(Node root) {
        return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean checkBST(Node cur, int min, int max) {
        if (cur == null) return true;
        if (cur.data <= min || cur.data >= max) return false;
        return checkBST(cur.left, min, cur.data) && checkBST(cur.right, cur.data, max);
    }
    /* Hoặc truyền Node
        boolean checkBST(Node root) {
            return checkBST(root, null, null);
        }

        boolean checkBST(Node current, Node left, Node right) {
            if (current == null) return true;
            if (left != null && left.data >= current.data) return false;
            if (right != null && right.data <= current.data) return false;
            return checkBST(current.left, left, current) && checkBST(current.right, current, right);
        }
    */

    /* *****************************
     * InorderTraversal.
     ****************************** */
    boolean checkBST1(Node root) {
        if (root == null) return true;
        Stack<Node> stk = new Stack<>();
        Node cur = root, prev = null;
        while (cur != null || !stk.empty()) {
            if (cur != null) {
                stk.push(cur);
                cur = cur.left;
            } else {
                cur = stk.pop();
                if (prev != null && prev.data >= cur.data) return false;
                prev = cur;
                cur = cur.right;
            }
        }
        return true;
    }
}
