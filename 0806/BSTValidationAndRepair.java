public class BSTValidationAndRepair {
    TreeNode prev = null, first = null, second = null;
    
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }
    
    private boolean validate(TreeNode node, Integer min, Integer max) {
        if(node == null) return true;
        if((min != null && node.val <= min) || (max != null && node.val >= max)) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }
    
    public void recoverTree(TreeNode root) {
        inorder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    
    private void inorder(TreeNode root) {
        if(root == null) return;
        inorder(root.left);
        if(prev != null && prev.val > root.val) {
            if(first == null) first = prev;
            second = root;
        }
        prev = root;
        inorder(root.right);
    }
}