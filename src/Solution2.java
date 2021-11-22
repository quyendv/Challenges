public class Solution2 {
    static class Node {
        public int data;
        public Node parent;
        public Node left;
        public Node right;

        // Các biến tạm dưới đây có thể dùng để lưu vị trí, kiểm tra nếu sinh viên thấy cần thiết
        public boolean tempBool1; // Hai biến tạm kiểu boolean có thể dùng cho bất kỳ mục đích gì
        public boolean tempBool2;
        public int tempInt1; // Hai biến tạm kiểu int có thể dùng cho bất kỳ mục đích gì
        public int tempInt2;
        public Node tempNode1; // Hai biến tạm kiểu Node có thể dùng cho bất kỳ mục đích gì
        public Node tempNode2;

        public Node(int data, Node parent, Node left, Node right) {
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

    }

    static public Node push(Node root, int n, int newValue) {
        return add(root, newValue);
    }

    static public Node add(Node root, int newValue) {
        if (root == null) return new Node(newValue, null, null, null);
        if (newValue < root.data) root.left = add(root.left, newValue);
        if (newValue > root.data) root.right = add(root.right, newValue);
        return root;
    }
    static public int getMax(Node root) {
        if (root == null) return -1;
        Node x = root;
        while (x.right != null) x = x.right;
        return x.data;
    }
}
