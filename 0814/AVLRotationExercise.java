class Node {
    int key;
    Node left, right;
    int height;

    public Node(int key) {
        this.key = key;
        this.height = 1;
    }
}

public class AVLRotationExercise {
    // 1. 左旋
    public Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // 执行旋转
        y.left = x;
        x.right = T2;

        // 更新高度
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // 2. 右旋
    public Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // 执行旋转
        x.right = y;
        y.left = T2;

        // 更新高度
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // 3. 左右旋
    public Node leftRightRotate(Node node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    // 4. 右左旋
    public Node rightLeftRotate(Node node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    // 辅助方法：获取节点高度
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    // 测试方法
    public static void main(String[] args) {
        AVLRotationExercise avl = new AVLRotationExercise();
        
        // 测试各种旋转情况
        // 可以创建测试树并应用不同的旋转操作
        // 然后检查旋转后的树结构是否正确
    }
}