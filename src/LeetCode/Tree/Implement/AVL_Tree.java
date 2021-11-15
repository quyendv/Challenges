/*
 Link hướng dẫn (có thể tham khảo các cây khác): https://www.happycoders.eu/algorithms/avl-tree-java/
 Xem thêm tại GeeksForGeeks: https://www.geeksforgeeks.org/avl-tree-set-1-insertion/
                             https://www.geeksforgeeks.org/avl-tree-set-2-deletion/

     AVL đặt theo tên các nhà khoa học phát triển nó -> Là 1 self-balancing binary search tree

     Hệ số cân bằng (Balance factor <-> BF): BF(node) = H(node.right) - H(node.left) dao động [-1, 1]: (Tùy theo bài
nhưng thường là phải trừ trái):
     + If the balance factor is < 0, the node is said to be left-heavy.
     + If the balance factor is > 0, the node is said to be right-heavy.
     + A balance factor of 0 represents a balanced node

     Các dạng cần cân bằng: <cực giống với RedBlackTree, khác mỗi k flipColors>
     + x-L-LL => rotateRight(x)
     + x-L-LR => rotateLeft(L) rồi rotateRight(x)
     + x-R-RR => rotateLeft(x)
     + x-R-RL => rotateRight(R) rồi rotateLeft(x)
*/

package LeetCode.Tree.Implement;

public class AVL_Tree {
    static class Node {
        private int data;
        private Node left, right;
        private int height; // null -> -1; Node lá/mới insert -> height = 0 & BF = 0 ==> chiều cao tính theo số cành mới chuẩn

        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
            this.height = 0; // lưu ý chỗ này!!
        }
    }

    private Node root;

    private int height(Node x) {    // với RBT: null -> size = 0, Node mới/lá size = 1
        if (x == null) return -1;
        return x.height;
    }

    private void updateHeight(Node x) { // tương tự update size ở RedBlackTree
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private int balanceFactor(Node x) {
        return height(x.right) - height(x.left); // Tùy bài toán, nhưng thường là phải trừ trái
    }

    /* ****************************
     * Rotation
     **************************** */
    private Node rotateLeft(Node x) {
        Node rightChild = x.right;
        // setLink.
        x.right = rightChild.left;
        rightChild.left = x;
        // setHeight: ở đây sẽ cập nhật cả 2 Node chứ k cập nhật mỗi Node con mới (làm như RBT cũng được k sai)
        updateHeight(x);          // phải update x (child mới trước)
        updateHeight(rightChild); // rồi mới update parent mới
        return rightChild;
    }
    private Node rotateRight(Node x) {
        Node leftNode = x.left;
        // setLink.
        x.left = leftNode.right;
        leftNode.right = x;
        // setHeight.
        updateHeight(x);
        updateHeight(leftNode);
        return leftNode;
    }
    private Node rebalance(Node x) {
        int balance = balanceFactor(x);
        if (balance > 1) {  // phải nhiều: R-RR or R-RL
            if (balanceFactor(x.right) >= 0) x = rotateLeft(x); // R-R (> hay >= cũng k ảnh hướng, = ở trên hay dưới đều được)
            else {                                              // R-RL
                x.right = rotateRight(x.right);
                x = rotateLeft(x);
            }
        } else if (balance < -1) { // L-LL or L-LR
            if (balanceFactor(x.left) <= 0) x = rotateRight(x); // L-L
            else {                                              // L-LR
                x.left = rotateLeft(x.left);
                x = rotateRight(x);
            }
        }
        return x;
    }

    public void insert(int data) {
        root = insert(root, data);
    }

    /* *************
    * Insert.
    ************** */
    private Node insert(Node x, int data) {
        if (x == null) return new Node(data);
        if (data < x.data) x.left = insert(x.left, data);
        else if (data > x.data) x.right = insert(x.right, data);
        // k cho phép chèn ==

        updateHeight(x); // update trước, vì height phải đúng mới xét balanceFactor được!!
        x = rebalance(x);
        return x;
    }

    // Cài đặt thêm sau ...
}
