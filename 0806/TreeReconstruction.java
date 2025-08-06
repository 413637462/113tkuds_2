public class TreeReconstruction {
    public TreeNode buildTreeFromPreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<inorder.length; i++) map.put(inorder[i], i);
        return build(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, map);
    }
    
    private TreeNode build(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd, Map<Integer, Integer> map) {
        if(preStart > preEnd) return null;
        TreeNode root = new TreeNode(pre[preStart]);
        int inRoot = map.get(root.val);
        int leftSize = inRoot - inStart;
        root.left = build(pre, preStart+1, preStart+leftSize, in, inStart, inRoot-1, map);
        root.right = build(pre, preStart+leftSize+1, preEnd, in, inRoot+1, inEnd, map);
        return root;
    }
}