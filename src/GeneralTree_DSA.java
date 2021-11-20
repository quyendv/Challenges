import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Tree: là cấu trúc dữ liệu gồm các nút (Node) được liên kết với nhau theo quan hệ cha - con.
   Mỗi Node có thể có nhiều Node con hoặc k có Node con nào
 * Chiều cao của cây là độ sâu của Node xa nhất
 * Depth(Node x) = số lượng ancestors của x
 */
public class GeneralTree_DSA {
    Node root;

    public GeneralTree_DSA() {
        root = null;
    }

    static class Node {
        int data;
        boolean visited;
        Node parent;
        List<Node> children;

        public Node(int data, Node parent, List<Node> children) {
            this.data = data;
            this.parent = parent;
            this.children = children;
            this.visited = false;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }

        // helperMethod.
        public Node find(int data) {
            // if (this == null) return null; // khác null mới gọi được hàm
            if (this.data == data) return this;
            if (this.children == null) return null;

            for (Node node : this.children) {
                Node temp = node.find(data);
                if (temp != null) return temp;
            }

            return null; // Not found.
        }
    }

    static void insertNode(Node root, int parent, int newInt) {
        // if empty tree --> insert root
        if (root == null) {
            root = new Node(newInt, null, null);
            return;
        }

        // else:
        Node parentNode = find(root, parent);
        if (parentNode == null) return; // invalid parent

        // Insertion.
        if (parentNode.children == null) {
            parentNode.children = new ArrayList<>();
        }

        parentNode.children.add(new Node(newInt, parentNode, null));
    }

    static void delete(Node root, int key) {
        if (root == null) return;

        Node target = find(root, key);
        if (target == null) return; // !contains(key)

        // sai: chưa tính TH bên dưới còn Node
        // if (target.parent.children.size() == 1) target.parent.children = null;
        // else target.parent.children.remove(target);

        // delete đúng:
        if (target.children == null) { // Node lá: k có children
            if (target.parent.children.size() == 1) target.parent.children = null; // nếu parent chỉ có target là con -> null
            else target.parent.children.remove(target);
        } else {
            // target còn children (k thể xóa luôn được) -> dịch child đầu tiên lên (chỉ gán data và child rồi xét target.children
            // chứ nếu tìm index của target trong list children của target.parent rồi thay child(0) lên mất tgian hơn

            // gán data:
            target.data = target.children.get(0).data;
            // gán children:
            if (target.children.size() == 1) target.children = target.children.get(0).children;
            else {
                List<Node> temp = target.children.get(0).children;
                target.children.remove(0);
                target.children.addAll(0, temp);
            }
        }
    }

    static boolean isBinaryTree(Node root) {
        // Cây rỗng or có 1 Node root.
        if (root == null || root.children == null) return true;

        if (root.children.size() > 2) return false;

        for (Node node : root.children) {
            if (!isBinaryTree(node)) return false;
        }
        return true;
    }

    static boolean isBinarySearchTree(Node root) {
        if (!isBinaryTree(root)) return false;
        return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    static boolean isMaxBinaryHeap(Node root) {
        return false;
    }

    /**
     * Là độ sâu của Node xa nhất
     * Depth(Node x) = số lượng ancestors của x
     */
    static int height(Node root) {
        if (root == null) return -1;            // Node null height = -1
        if (root.children == null) return 0;    // Node leaf height = 0;

        int maxHeightOfSubtree = -1;     // chỉ cần gán < 0 vì children != null nên qua vòng lặp maxHeightOfSubtree tối thiểu == 0
        for (Node node : root.children) {
            maxHeightOfSubtree = Math.max(maxHeightOfSubtree, height(node));
        }
        return 1 + maxHeightOfSubtree;
    }

    static void print(Node root) {
        // In cây theo từng tầng một
        if (root == null) {
            System.out.println("Empty tree!");
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.print(temp.toString() + " ");
            if (temp.children != null) {
                for (Node node : temp.children) queue.offer(node);
            }
        }
        System.out.println();
    }

    static public void preorder(Node root) {
        if (root != null) System.out.print(root.toString() + " ");
        if (root.children != null) {
            for (Node node : root.children) {
                preorder(node);
            }
        }
    }

    static public void postorder(Node root) {
        if (root != null) {
            if (root.children != null) {
                for (int i = root.children.size() - 1; i >= 0; i--) {
                    postorder(root.children.get(i));
                }
            }
            System.out.print(root.toString() + " ");
        }
    }

    public static void main(String[] args) {
        /*
                        5                   -> isBST height = 2;
                    3       7
                  1   4   6    8
        */

        // Tree generalTree = new Tree();
        // generalTree.root = new Node(5, null, null);

        GeneralTree_DSA generalTree = new GeneralTree_DSA(5); // root = Node{data=5}
        insertNode(generalTree.root, 5, 3);
        insertNode(generalTree.root, 5, 7);
        insertNode(generalTree.root, 3, 1);
        insertNode(generalTree.root, 3, 4);
        insertNode(generalTree.root, 7, 6);
        insertNode(generalTree.root, 7, 8);

        // delete(generalTree.root, 4);

        System.out.println(isBinarySearchTree(generalTree.root));

        System.out.println(height(generalTree.root));

        System.out.println("LevelOrder Traversal:");
        print(generalTree.root);

        System.out.println('\n' + "Preorder Traversal:");
        preorder(generalTree.root);

        System.out.println("\n\n" + "Postorder:");
        postorder(generalTree.root);
    }

    /* ************************
     * HelperMethod.
     ************************ */
    public GeneralTree_DSA(int data) {
        root = new Node(data, null, null);
    }

    private static Node find(Node root, int parent) {
        if (root == null) return null;
        return root.find(parent);
    }

    private static boolean checkBST(Node x, int min, int max) {
        if (x == null) return true;
        if (x.data <= min || x.data >= max) return false;

        if (x.children == null) return true;

        if (x.children.size() <= 1) return true; // k rõ nhánh left hay right?

        return checkBST(x.children.get(0), min, x.data) && checkBST(x.children.get(1), x.data, max);
    }
}