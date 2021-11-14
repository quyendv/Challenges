// https://leetcode.com/problems/validate-binary-search-tree/
// Cách 1: check bằng đệ quy
// Cách 2: sử dụng duyệt inorder so sánh:
// + áp dụng được cho nhiều bài: https://leetcode.com/problems/validate-binary-search-tree/discuss/32112/Learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-(Java-Solution)
// + hoặc cách đã quen hơn: https://leetcode.com/problems/validate-binary-search-tree/discuss/32101/My-java-inorder-iteration-solution
package LeetCode.Tree;

import java.util.Stack;

public class ValidBST_98 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /* *****************************
     * Recursion.
     * Trên leetcode phải thay int bằng long mới pass? Mặc dù HackerRank đúng
     ****************************** */
    public boolean isValidBST(TreeNode root) {
        return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean checkBST(TreeNode cur, int min, int max) {
        if (cur == null) return true;
        if (cur.val <= min || cur.val >= max) return false;
        return checkBST(cur.left, min, cur.val) && checkBST(cur.right, cur.val, max);
    }

    /* Hoặc truyền Node - cũng khá hiệu quả vì hình như k tạo thêm bản copy

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
     * Có vẻ chậm hơn.
     ****************************** */
    public boolean isValidBST1(TreeNode root) {
        Stack<TreeNode> stk = new Stack<>();
        TreeNode prev = null, cur = root;
        while (!stk.empty() || cur != null) {
            if (cur != null) {
                stk.push(cur);
                cur = cur.left;
            } else {
                cur = stk.pop();
                if (prev != null && prev.val >= cur.val) return false;
                prev = cur;
                cur = cur.right;
            }
        }
        return true;
    }
}
