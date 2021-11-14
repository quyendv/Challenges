// video: https://www.youtube.com/watch?v=TlEqqkszRtQ&t=2406s

package LeetCode.Tree.Implement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


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

class TreeNode {
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

public class MyBST {
    private TreeNode root;

    /*
        _710_Insert_into_a_BST
        https://leetcode.com/problems/insert-into-a-binary-search-tree/
    */
    public TreeNode insertNode(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode temp = root;
        while (true) {
            if (val < temp.val) {
                if (temp.left == null) {
                    temp.left = new TreeNode(val);
                    break;
                } else temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = new TreeNode(val);
                    break;
                } else temp = temp.right;
            }
        }
        return root;
    }

    //---------------------------------------------------------------------------------------------//
    public TreeNode insertNode1(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insertNode1(root.left, val);
        if (val > root.val) root.right = insertNode1(root.right, val);
        return root;
    }


    //---------------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------//




    /*
        _450_Delete_Node_in_a_BST
        https://leetcode.com/problems/delete-node-in-a-bst/
        Cách 1: đệ quy đến khi root là Node cần xóa
        + nếu Node cần xóa k có left và right thì return null
        + nếu Node cần xóa chỉ có left khác null (right == null), return left
        + nếu Node cần xóa chỉ có right khác null (left == null) , return right
        Cách 2: https://leetcode.com/problems/delete-node-in-a-bst/discuss/93298/Iterative-solution-in-Java-O(h)-time-and-O(1)-space
        + tham khảo: vì dùng while khác recursion ở chỗ phải dùng 1 Node đứng trước Node cần xóa
        + mặc dù gọi hàm ít hơn đệ quy nên tiết kiệm bộ nhớ hơn, nhưng khó implement và khó hiểu
    */

    // Solution 1: Recursion
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;    // test root đầu tiên, cũng là TH nếu key k tồn tại trong BST
        // B1: find key
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);

        } else {  // root.val == key  -> xoa key
            // B2: delete key

//            // TH1: left, right == null
//            if (root.left == null && root.right == null) return null;
//
//            // TH2: left or right == null
//            if (root.left == null) {
//                return root.right;
//            }
//            if (root.right == null) {
//                return root.left;
//            }

            // TH 1 + 2: optimized
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // TH3: left, right != null
            // Có thể tìm trái cùng bên phải hoặc phải cùng bên trái đều được

            TreeNode rightSmallest = findRightSmallest(root.right);
            root.val = rightSmallest.val;
            root.right = deleteNode(root.right, rightSmallest.val);

            // 1 cách khác cho TH3:
            /*
             * TreeNode rightSmallest = findRightSmallest(root.right); // có thể dùng while trực tiếp luôn thay vì tạo thêm hàm
             * rightSmallest.left = root.left;
             * return root.right;
             * --> tuy nhiên cách này chắc chắn sẽ làm dài cây ra -> tốt nhất k nên dùng
             */
        }
        return root;
    }

    public TreeNode findRightSmallest(TreeNode root) {
//        if (root == null) return null;   // có thể bỏ vì khi truyền vào đã != null
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    //---------------------------------------------------------------------------------------------//

    // Solution 2: iteration
    public TreeNode deleteNode1(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) return deleteCurNode(root);
        TreeNode prev = null, cur = root;
        while (cur != null && cur.val != key) {
            prev = cur;
            cur = key < cur.val ? cur.left : cur.right;
        }
        // Đến đây có 2 TH: nếu key tồn tại trong BST thì cur.val = key và prev ở trước cur (left or right), else cur == null;

        // TH cur == null hay k đều đc xử lý trong các TH dưới nên k cần quan tâm. Nhưng vẫn xử lý ngay đc bằng cách return root
        if (cur == null) return root;

        // vì cur chắc chắn chạy 1 vòng while nên prev lúc này chắc chắn khác null, nên ta xét đc prev.left or right
        if (prev.left == cur) {   // cur == null hay k k quan trọng, đều được xử lý
            prev.left = deleteCurNode(cur);
        } else if (prev.right == cur) {
            prev.right = deleteCurNode(cur);
        }
        return root;  // trả về root của BST
    }

    public TreeNode deleteCurNode(TreeNode cur) {    // nhiệm vụ xóa Node truyền vào và trả về Node thay thế
        if (cur == null) return null;
        if (cur.left == null) return cur.right;
        if (cur.right == null) return cur.left;

        // find RightSmallest
        TreeNode prev = null, next = cur.right;  // đưa next đến rightSmallest và prev ở trước next nhằm thực hiện bước xóa
        while (next.left != null) {
            prev = next;
            next = next.left;
        }
        next.left = cur.left;   // dòng này quan trọng
        if (cur.right != next) { // nếu next hiện tại == next ban đầu (k chạy while -> next ban đầu là rightSmallest->return thôi)
            prev.left = next.right;  // dịch right của next lên thay chỗ next
            next.right = cur.right;  // next thay chỗ hoàn toàn cur, vì next.left = cur.left trước đó và giờ next.right = cur.right
        }
        return next;
    }


    //---------------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------//


    /*
     * 700.Search in a BST
     * https://leetcode.com/problems/search-in-a-binary-search-tree/
     * - Recursion
     * - Iteration
     * */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (val < root.val) return searchBST(root.left, val);
        if (val > root.val) return searchBST(root.right, val);
        // else val == root.val
        return root;
    }

    //---------------------------------------------------------------------------------------------//
    public TreeNode searchBST1(TreeNode root, int val) {
        TreeNode x = root;
        while (x != null && x.val != val) {
            x = val < x.val ? x.left : x.right;
        }
        return x; // null or val
    }


    //---------------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------//



    /*
     * Duyệt BST: Pre-order (N-L-R), In-order (L-N-R), Post-order (L-R-N)
     *
     * 144. Binary Tree Preorder Traversal
     * https://leetcode.com/problems/binary-tree-preorder-traversal/discuss/45468/3-Different-Solutions
     */


    // Solution 1: recursion
    // khi dùng đệ quy, tránh khởi tạo list trong hàm vì mỗi lần gọi sẽ tốn nhiều bộ nhớ
    // -> giải pháp: tạo bên ngoài || dùng thêm hàm helper || overloading

    List<Integer> ans = new ArrayList<>();  // tuy nhiên cách dùng biến cục bộ như vậy là k nên

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return ans;
        ans.add(root.val);
        /*ans = */
        preorderTraversal(root.left);  // có thể gán, nhưng nó k có nghĩa vì ta đã thay đổi trực tiếp vào ans
        /*ans = */
        preorderTraversal(root.right); // nên dùng hàm helper kiểu void
        return ans;
    }

    //---------------------------------------------------------------------------------------------//
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preHelper(root, res);
        return res;
    }

    public void preHelper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preHelper(root.left, res);
        preHelper(root.right, res);
    }

    //---------------------------------------------------------------------------------------------//
    // Dùng overloading, giống helper nhưng đc phép đặt tên giống
    public List<Integer> preorderTraversal_1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorderTraversal_1(root, res);
        return res;
    }

    public void preorderTraversal_1(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preorderTraversal_1(root.left, res);
        preorderTraversal_1(root.right, res);
    }
    //---------------------------------------------------------------------------------------------//

    // Solution 2: iteration
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;  // return list rỗng
        Stack<TreeNode> stk = new Stack<>();  // chỉ chèn các TreeNode khác null vào thôi
        stk.push(root);

        TreeNode cur;
        while (!stk.empty()) {
            cur = stk.pop();
            res.add(cur.val);
            if (cur.right != null) stk.push(cur.right); // chèn right trước để xử lý sau
            if (cur.left != null) stk.push(cur.left);   // chèn left sau để xử lý trước
        }
        return res;
    }

    //---------------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------//
    //---------------------------------------------------------------------------------------------//

    /*
        InOrder & PostOrder tương tự, chỉ đổi vị trí đi xíu: dùng đệ quy dễ hiểu nhất -> hạn chế dùng stack
        94. Binary Tree Inorder Traversal
        145. Binary Tree Postorder Traversal
        102. Binary Tree Level Order Traversal
        https://leetcode.com/problems/binary-tree-level-order-traversal/discuss/33450/Java-solution-with-a-queue-used
        https://leetcode.com/problems/binary-tree-level-order-traversal/discuss/33445/Java-Solution-using-DFS
        107. Binary Tree Level Order Traversal II

        - Tổng hợp:
        https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45551/Preorder-Inorder-and-Postorder-Iteratively-Summarization

        - BT luyện tập:
        + 105. Construct Binary Tree from Preorder and Inorder Traversal
        https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
        + 106. Construct Binary Tree from Inorder and Postorder Traversal
        https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    */
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        LinkedList<Integer> l = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        l.addFirst(1);
    }
}