// https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor/problem
// https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
// tìm node tổ tiên chung thấp nhất của n1, n2

package HackerRank;

public class BST_LowestCommonAncestor {
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

    // chắc chắn có v1 v2 trong cây
    // O(n): duyệt full cây
    public static Node lca(Node root, int v1, int v2) {
        // if (root == null) return null;
        // if (root.data == v1 || root.data == v2) return root;
        if (root == null || root.data == v1 || root.data == v2) return root;

        Node leftLca = lca(root.left, v1, v2);
        Node rightLca = lca(root.right, v1, v2);

        if (leftLca != null && rightLca != null) // root ở giữa chính là LCA
            return root;

        return leftLca != null ? leftLca : rightLca;
    }
}
