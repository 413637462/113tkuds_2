import java.util.ArrayList;
import java.util.List;

class Node {
    int key;
    Node left, right;

    public Node(int key) {
        this.key = key;
    }
}

public class AVLRangeQueryExercise {
    private Node root;

    // 范围查询
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQuery(root, min, max, result);
        return result;
    }

    private void rangeQuery(Node node, int min, int max, List<Integer> result) {
        if (node == null) {
            return;
        }

        // 如果当前节点大于min，则搜索左子树
        if (node.key > min) {
            rangeQuery(node.left, min, max, result);
        }

        // 如果当前节点在[min, max]范围内，添加到结果
        if (node.key >= min && node.key <= max) {
            result.add(node.key);
        }

        // 如果当前节点小于max，则搜索右子树
        if (node.key < max) {
            rangeQuery(node.right, min, max, result);
        }
    }

    // 插入方法 (用于构建测试树)
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
        }

        return node;
    }
}