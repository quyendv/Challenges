// https://www.hackerrank.com/challenges/self-balancing-tree/problem

// balanceFactor = height(left subtree) - height(right subtree).
// Note: All the values in the tree will be distinct. Height of a Null node is -1 and the height of the leaf node is 0.

package HackerRank;

public class SelfBalancingTree {
    static class Node {
        int val;    //Value
        int ht;        //Height
        Node left;    //Left child
        Node right;    //Right child
    }

    private static int height(Node x) {
        if (x == null) return -1;
        return x.ht;
    }

    private static int balanceFactor(Node x) {
        return height(x.left) - height(x.right);
    }

    private static void updateHeight(Node x) {
        x.ht = 1 + Math.max(height(x.left), height(x.right));
    }

    private static Node rotateLeft(Node x) {
        Node rightChild = x.right;

        // setLink
        x.right = rightChild.left;
        rightChild.left = x;

        // setHeight
        updateHeight(x);
        updateHeight(rightChild);

        return rightChild;
    }

    private static Node rotateRight(Node x) {
        Node leftChild = x.left;

        x.left = leftChild.right;
        leftChild.right = x;

        updateHeight(x);
        updateHeight(leftChild);

        return leftChild;
    }

    private static Node rebalance(Node x) {
        int balance = balanceFactor(x);
        if (balance > 1) {  // L-L or L-LR
            if (balanceFactor(x.left) >= 0) x = rotateRight(x); // L-L
            else {                                              // L-LR
                x.left = rotateLeft(x.left);
                x = rotateRight(x);
            }
        } else if (balance < -1) {  // R-R or R-RL
            if (balanceFactor(x.right) <= 0) x = rotateLeft(x); // R-R
            else {
                x.right = rotateRight(x.right);
                x = rotateLeft(x);
            }
        }
        return x;
    }

    static Node insert(Node root, int val) {
        if (root == null) {
            Node newNode = new Node();
            newNode.val = val;
            newNode.left = null; newNode.right = null;
            newNode.ht = 0;
            return newNode;
        }

        if (val < root.val) root.left = insert(root.left, val);
        else if (val > root.val) root.right = insert(root.right, val);

        updateHeight(root);

        return rebalance(root);
    }
}
