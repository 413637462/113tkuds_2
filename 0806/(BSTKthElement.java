public class BSTKthElement {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while(true) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(--k == 0) return root.val;
            root = root.right;
        }
    }
    
    public int kthLargest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while(true) {
            while(root != null) {
                stack.push(root);
                root = root.right;
            }
            root = stack.pop();
            if(--k == 0) return root.val;
            root = root.left;
        }
    }
    
    public List<Integer> rangeKth(TreeNode root, int k, int j) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(--j < 0) break;
            if(--k <= 0) res.add(root.val);
            root = root.right;
        }
        return res;
    }
}