import java.util.Scanner;

public class M10_RBPropertiesCheck {
    static class Node {
        int val;
        char color;
        boolean isNull;
        
        Node(int val, char color, boolean isNull) {
            this.val = val;
            this.color = color;
            this.isNull = isNull;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Node[] tree = new Node[n];
        
        // 讀取樹的節點
        for (int i = 0; i < n; i++) {
            int val = scanner.nextInt();
            char color = scanner.next().charAt(0);
            
            if (val == -1) {
                tree[i] = new Node(-1, 'B', true); // null節點視為黑色
            } else {
                tree[i] = new Node(val, color, false);
            }
        }
        
        String result = checkRBTree(tree);
        System.out.println(result);
    }
    
    private static String checkRBTree(Node[] tree) {
        // 檢查性質1: 根節點為黑
        if (!tree[0].isNull && tree[0].color != 'B') {
            return "RootNotBlack";
        }
        
        // 檢查性質2: 不得有相鄰紅節點
        for (int i = 0; i < tree.length; i++) {
            if (!tree[i].isNull && tree[i].color == 'R') {
                int leftIdx = 2 * i + 1;
                int rightIdx = 2 * i + 2;
                
                // 檢查左子節點
                if (leftIdx < tree.length && !tree[leftIdx].isNull && tree[leftIdx].color == 'R') {
                    return "RedRedViolation at index " + i;
                }
                
                // 檢查右子節點
                if (rightIdx < tree.length && !tree[rightIdx].isNull && tree[rightIdx].color == 'R') {
                    return "RedRedViolation at index " + i;
                }
            }
        }
        
        // 檢查性質3: 所有路徑黑高度一致
        if (!checkBlackHeight(tree, 0)) {
            return "BlackHeightMismatch";
        }
        
        return "RB Valid";
    }
    
    private static boolean checkBlackHeight(Node[] tree, int index) {
        if (index >= tree.length || tree[index].isNull) {
            return true;
        }
        
        // 遞迴檢查左右子樹的黑高度
        int leftIdx = 2 * index + 1;
        int rightIdx = 2 * index + 2;
        
        boolean leftValid = checkBlackHeight(tree, leftIdx);
        boolean rightValid = checkBlackHeight(tree, rightIdx);
        
        if (!leftValid || !rightValid) {
            return false;
        }
        
        // 計算左右子樹的黑高度
        int leftHeight = getBlackHeight(tree, leftIdx);
        int rightHeight = getBlackHeight(tree, rightIdx);
        
        return leftHeight == rightHeight;
    }
    
    private static int getBlackHeight(Node[] tree, int index) {
        if (index >= tree.length || tree[index].isNull) {
            return 1; // null節點視為黑色，高度為1
        }
        
        int leftIdx = 2 * index + 1;
        int rightIdx = 2 * index + 2;
        
        int leftHeight = getBlackHeight(tree, leftIdx);
        int rightHeight = getBlackHeight(tree, rightIdx);
        
        // 當前節點的黑高度 = 子樹黑高度 + (當前節點是黑色嗎？1:0)
        int currentHeight = Math.max(leftHeight, rightHeight);
        if (tree[index].color == 'B') {
            currentHeight++;
        }
        
        return currentHeight;
    }
}