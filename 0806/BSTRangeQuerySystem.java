public class BSTRangeQuerySystem {
    public List<Integer> rangeQuery(TreeNode root, int low, int high) {
        List<Integer> res = new ArrayList<>();
        dfs(root, low, high, res);
        return res;
    }
    
    private void dfs(TreeNode node, int low, int high, List<Integer> res) {
        if(node == null) return;
        if(node.val > low) dfs(node.left, low, high, res);
        if(node.val >= low && node.val <= high) res.add(node.val);
        if(node.val < high) dfs(node.right, low, high, res);
    }
    
    public int rangeSum(TreeNode root, int low, int high) {
        if(root == null) return 0;
        if(root.val < low) return rangeSum(root.right, low, high);
        if(root.val > high) return rangeSum(root.left, low, high);
        return root.val + rangeSum(root.left, low, high) + rangeSum(root.right, low, high);
    }
    
    public int closestValue(TreeNode root, double target) {
        int closest = root.val;
        while(root != null) {
            closest = Math.abs(root.val - target) < Math.abs(closest - target) ? root.val : closest;
            root = target < root.val ? root.left : root.right;
        }
        return closest;
    }
}