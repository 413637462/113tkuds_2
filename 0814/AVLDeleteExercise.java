class Node {
    int key;
    Node left, right;
    int height;

    public Node(int key) {
        this.key = key;
        this.height = 1;
    }
}

public class AVLDeleteExercise {
    private Node root;

    // 删除节点
    public void delete(int key) {
        root = delete(root, key);
    }

    private Node delete(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            // 情况1：叶子节点或情况2：只有一个子节点
            if (node.left == null || node.right == null) {
                Node temp = (node.left != null) ? node.left : node.right;
                
                // 叶子节点情况
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    // 只有一个子节点情况
                    node = temp;
                }
            } else {
                // 情况3：有两个子节点 - 找后继节点(右子树的最小值)
                Node temp = minValueNode(node.right);
                node.key = temp.key;
                node.right = delete(node.right, temp.key);
            }
        }

        // 如果树只有一个节点，删除后返回null
        if (node == null) {
            return null;
        }

        // 更新高度
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 重新平衡
        return balance(node);
    }

    // 找最小值的节点
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // 获取节点高度
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // 获取平衡因子
    private int getBalanceFactor(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // 平衡节点 (使用练习2中的旋转方法)
    private Node balance(Node node) {
        int balanceFactor = getBalanceFactor(node);

        // 左左情况
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        // 左右情况
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 右右情况
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        // 右左情况
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 旋转方法 (来自练习2)
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }
}