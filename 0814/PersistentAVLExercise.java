import java.util.HashMap;
import java.util.Map;

/**
 * 持久化AVL树实现，支持版本控制
 */
final class AVLNode {
    final int key;
    final AVLNode left, right;
    final int height;
    
    AVLNode(int key, AVLNode left, AVLNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.height = 1 + Math.max(height(left), height(right));
    }
    
    static int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }
    
    int balanceFactor() {
        return height(left) - height(right);
    }
}

public class PersistentAVLExercise {
    private final Map<Integer, AVLNode> versionHistory = new HashMap<>();
    private int currentVersion = 0;
    
    public PersistentAVLExercise() {
        versionHistory.put(currentVersion, null); // 初始空树
    }
    
    /**
     * 插入新节点并创建新版本
     */
    public void insert(int key) {
        AVLNode newRoot = insert(versionHistory.get(currentVersion), key);
        versionHistory.put(++currentVersion, newRoot);
    }
    
    private AVLNode insert(AVLNode node, int key) {
        if (node == null) {
            return new AVLNode(key, null, null);
        }
        
        if (key < node.key) {
            AVLNode newLeft = insert(node.left, key);
            return balance(new AVLNode(node.key, newLeft, node.right));
        } else if (key > node.key) {
            AVLNode newRight = insert(node.right, key);
            return balance(new AVLNode(node.key, node.left, newRight));
        } else {
            return node; // 键已存在，不修改
        }
    }
    
    /**
     * 平衡AVL树
     */
    private AVLNode balance(AVLNode node) {
        int balanceFactor = node.balanceFactor();
        
        // 左左情况
        if (balanceFactor > 1 && node.left.balanceFactor() >= 0) {
            return rightRotate(node);
        }
        
        // 左右情况
        if (balanceFactor > 1 && node.left.balanceFactor() < 0) {
            AVLNode newLeft = leftRotate(node.left);
            AVLNode newRoot = new AVLNode(node.key, newLeft, node.right);
            return rightRotate(newRoot);
        }
        
        // 右右情况
        if (balanceFactor < -1 && node.right.balanceFactor() <= 0) {
            return leftRotate(node);
        }
        
        // 右左情况
        if (balanceFactor < -1 && node.right.balanceFactor() > 0) {
            AVLNode newRight = rightRotate(node.right);
            AVLNode newRoot = new AVLNode(node.key, node.left, newRight);
            return leftRotate(newRoot);
        }
        
        return node;
    }
    
    /**
     * 右旋转
     */
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        
        // 创建新节点而不是修改现有节点
        AVLNode newY = new AVLNode(y.key, T2, y.right);
        return new AVLNode(x.key, x.left, newY);
    }
    
    /**
     * 左旋转
     */
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        
        // 创建新节点而不是修改现有节点
        AVLNode newX = new AVLNode(x.key, x.left, T2);
        return new AVLNode(y.key, newX, y.right);
    }
    
    /**
     * 获取指定版本的树
     */
    public AVLNode getVersion(int version) {
        return versionHistory.get(version);
    }
    
    /**
     * 获取当前版本号
     */
    public int getCurrentVersion() {
        return currentVersion;
    }
    
    /**
     * 检查指定版本中是否包含键
     */
    public boolean contains(int version, int key) {
        return contains(versionHistory.get(version), key);
    }
    
    private boolean contains(AVLNode node, int key) {
        if (node == null) {
            return false;
        }
        
        if (key < node.key) {
            return contains(node.left, key);
        } else if (key > node.key) {
            return contains(node.right, key);
        } else {
            return true;
        }
    }
    
    /**
     * 测试用例
     */
    public static void main(String[] args) {
        PersistentAVLExercise tree = new PersistentAVLExercise();
        
        // 版本0: 空树
        System.out.println("Version 0: " + tree.contains(0, 10)); // false
        
        // 版本1: 插入10
        tree.insert(10);
        System.out.println("Version 1: " + tree.contains(1, 10)); // true
        
        // 版本2: 插入20
        tree.insert(20);
        System.out.println("Version 2: " + tree.contains(2, 20)); // true
        
        // 版本3: 插入30 (会触发旋转)
        tree.insert(30);
        System.out.println("Version 3: " + tree.contains(3, 30)); // true
        
        // 检查旧版本仍然可用
        System.out.println("Version 1 still valid: " + tree.contains(1, 10)); // true
        System.out.println("Version 1 doesn't have 30: " + !tree.contains(1, 30)); // true
    }
}