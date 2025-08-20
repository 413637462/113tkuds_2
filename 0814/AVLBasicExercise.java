class Node {
    int key;
    Node left, right;
    int height;

    public Node(int key) {
        this.key = key;
        this.height = 1;
    }
}

public class AVLBasicExercise {
    private Node root;

    // 1. 插入节点
    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node; // 不允许重复键
        }

        // 更新高度
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 检查平衡并旋转
        return balance(node);
    }

    // 2. 搜索节点
    public boolean search(int key) {
        return search(root, key) != null;
    }

    private Node search(Node node, int key) {
        if (node == null || node.key == key) {
            return node;
        }

        return key < node.key ? search(node.left, key) : search(node.right, key);
    }

    // 3. 计算树的高度
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // 4. 检查是否为有效的AVL树
    public boolean isValidAVL() {
        return isValidAVL(root);
    }

    private boolean isValidAVL(Node node) {
        if (node == null) {
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor < -1 || balanceFactor > 1) {
            return false;
        }

        return isValidAVL(node.left) && isValidAVL(node.right);
    }

    // 辅助方法：获取平衡因子
    private int getBalanceFactor(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // 平衡节点
    private Node balance(Node node) {
        int balanceFactor = getBalanceFactor(node);

        // 左左情况 - 右旋
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        // 右右情况 - 左旋
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        // 左右情况 - 先左旋再右旋
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 右左情况 - 先右旋再左旋
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 右旋 (将在练习2中实现)
    private Node rightRotate(Node y) {
        // 练习2中实现
        return y;
    }

    // 左旋 (将在练习2中实现)
    private Node leftRotate(Node x) {
        // 练习2中实现
        return x;
    }
}