// https://leetcode.com/problems/maximum-depth-of-binary-tree/

package LeetCode.Tree;

public class _104_Maximum_Depth_of_Binary_Tree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
            left = null;
            right = null;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Là độ sâu của Node xa nhất
     * Depth(Node x) = số lượng ancestors của x
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
