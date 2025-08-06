public class BSTConversionAndBalance {
    TreeNode prev = null, head = null;
    
    public TreeNode treeToDoublyList(TreeNode root) {
        if(root == null) return null;
        convert(root);
        head.left = prev;
        prev.right = head;
        return head;
    }
    
    private void convert(TreeNode node) {
        if(node == null) return;
        convert(node.left);
        if(prev == null) head = node;
        else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;
        convert(node.right);
    }
    
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length-1);
    }
    
    private TreeNode build(int[] nums, int left, int right) {
        if(left > right) return null;
        int mid = left + (right-left)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = build(nums, left, mid-1);
        node.right = build(nums, mid+1, right);
        return node;
    }
}