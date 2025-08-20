import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class M07_BinaryTreeLeftView {
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
        
        TreeNode root = buildTreeFromLevelOrder(levelOrder);
        printLeftView(root);
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
    
    // 輸出左視圖
    private static void printLeftView(TreeNode root) {
        if (root == null) {
            System.out.println("LeftView: ");
            return;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder result = new StringBuilder("LeftView: ");
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // 每層的第一個節點就是左視圖的節點
            TreeNode firstNode = queue.peek();
            result.append(firstNode.val).append(" ");
            
            // 處理當前層的所有節點
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        
        System.out.println(result.toString().trim());
    }
}