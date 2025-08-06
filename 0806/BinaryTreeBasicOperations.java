class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class BinaryTreeBasicOperations {
    int sum = 0, count = 0, maxWidth = 0;
    
    public int sumNodes(TreeNode root) {
        if(root == null) return 0;
        return root.val + sumNodes(root.left) + sumNodes(root.right);
    }
    
    public int maxNode(TreeNode root) {
        if(root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(maxNode(root.left), maxNode(root.right)));
    }
    
    public int widthOfBinaryTree(TreeNode root) {
        List<Integer> lefts = new ArrayList<>();
        return dfs(root, 1, 0, lefts);
    }
    
    private int dfs(TreeNode node, int id, int depth, List<Integer> lefts) {
        if(node == null) return 0;
        if(depth >= lefts.size()) lefts.add(id);
        return Math.max(id - lefts.get(depth) + 1, 
               Math.max(dfs(node.left, id*2, depth+1, lefts), 
                        dfs(node.right, id*2+1, depth+1, lefts)));
    }
    
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean end = false;
        while(!q.isEmpty()) {
            TreeNode node = q.poll();
            if(node == null) end = true;
            else {
                if(end) return false;
                q.offer(node.left);
                q.offer(node.right);
            }
        }
        return true;
    }
}