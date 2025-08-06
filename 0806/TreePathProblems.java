public class TreePathProblems {
    public List<List<Integer>> allPaths(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, new ArrayList<>(), res);
        return res;
    }
    
    private void dfs(TreeNode node, List<Integer> path, List<List<Integer>> res) {
        if(node == null) return;
        path.add(node.val);
        if(node.left == null && node.right == null) res.add(new ArrayList<>(path));
        dfs(node.left, path, res);
        dfs(node.right, path, res);
        path.remove(path.size()-1);
    }
    
    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }
    
    private int maxGain(TreeNode node) {
        if(node == null) return 0;
        int left = Math.max(maxGain(node.left), 0);
        int right = Math.max(maxGain(node.right), 0);
        maxSum = Math.max(maxSum, node.val + left + right);
        return node.val + Math.max(left, right);
    }
}