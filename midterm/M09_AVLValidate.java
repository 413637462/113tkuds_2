import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class M09_AVLValidate {
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
        String result = validateAVL(root);
        System.out.println(result);
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
    
    // 驗證是否為有效AVL樹
    private static String validateAVL(TreeNode root) {
        // 先檢查是否為BST
        if (!isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            return "Invalid BST";
        }
        
        // 再檢查是否為AVL（平衡因子）
        if (checkAVL(root) == -1) {
            return "Invalid AVL";
        }
        
        return "Valid";
    }
    
    // 檢查是否為BST（遞迴帶上下界）
    private static boolean isValidBST(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        
        if (node.val <= min || node.val >= max) {
            return false;
        }
        
        return isValidBST(node.left, min, node.val) && 
               isValidBST(node.right, node.val, max);
    }
    
    // 檢查是否為AVL樹，返回高度，如果返回-1表示不平衡
    private static int checkAVL(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftHeight = checkAVL(node.left);
        int rightHeight = checkAVL(node.right);
        
        // 如果子樹不平衡，直接返回-1
        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }
        
        // 檢查平衡因子
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        
        // 返回當前節點的高度
        return Math.max(leftHeight, rightHeight) + 1;
    }
}/* Time Complexity: O(n)
 * 說明：我們需要遍歷每個節點來檢查BST性質和AVL平衡性質。
 *       檢查BST需要O(n)時間，檢查AVL平衡也需要O(n)時間，
 *       因此總時間複雜度為O(n)。
 */