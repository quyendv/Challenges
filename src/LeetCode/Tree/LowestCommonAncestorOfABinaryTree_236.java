// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
// tìm node tổ tiên thấp nhất (gần nhất) chứa v1, v2 (v1, v2 chắc chắn có)
// tham khảo thêm nếu v1, v2 chưa chắc có trong cây: https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/

/*
    Luyện thêm:
    https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/
    https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/
    https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
*/


package LeetCode.Tree;

public class LowestCommonAncestorOfABinaryTree_236 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // O(n): duyệt full cây
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode leftLCA = lowestCommonAncestor(root.left, p, q);
        TreeNode rightLca = lowestCommonAncestor(root.right, p, q);

        if (leftLCA != null && rightLca != null) return root;
        return leftLCA != null ? leftLCA : rightLca;
    }
}
