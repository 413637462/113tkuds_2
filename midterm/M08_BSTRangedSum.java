import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class M08_BSTRangedSum {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Integer[] levelOrder = new Integer[n];
        
        for (int i = 0; i < n; i++) {
            if (scanner.hasNextInt()) {
                levelOrder[i] = scanner.nextInt();
            }
        }
        
        int L = scanner.nextInt();
        int R = scanner.nextInt();
        
        TreeNode root = buildTreeFromLevelOrder(levelOrder);
        int sum = rangeSumBST(root, L, R);
        System.out.println("Sum: " + sum);
    }
    
    // 從層序遍歷構建二元樹
    private static TreeNode buildTreeFromLevelOrder(Integer[] levelOrder) {
        if (levelOrder.length == 0 || levelOrder[0] == -1) {
            return null;
        }
        
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        int index = 1;
        while (!queue.isEmpty() && index < levelOrder.length) {
            TreeNode current = queue.poll();
            
            // 處理左子節點
            if (index < levelOrder.length && levelOrder[index] != -1) {
                current.left = new TreeNode(levelOrder[index]);
                queue.offer(current.left);
            }
            index++;
            
            // 處理右子節點
            if (index < levelOrder.length && levelOrder[index] != -1) {
                current.right = new TreeNode(levelOrder[index]);
                queue.offer(current.right);
            }
            index++;
        }
        
        return root;
    }
    
    // 計算BST區間總和
    private static int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        
        int sum = 0;
        
        // 如果當前節點在區間內，加入總和
        if (root.val >= L && root.val <= R) {
            sum += root.val;
        }
        
        // 如果當前節點大於L，需要檢查左子樹
        if (root.val > L) {
            sum += rangeSumBST(root.left, L, R);
        }
        
        // 如果當前節點小於R，需要檢查右子樹
        if (root.val < R) {
            sum += rangeSumBST(root.right, L, R);
        }
        
        return sum;
    }
}