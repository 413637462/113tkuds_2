class MultiWayNode {
    int val;
    List<MultiWayNode> children;
    MultiWayNode(int x) { val = x; children = new ArrayList<>(); }
}

public class MultiWayTreeAndDecisionTree {
    public void dfsTraversal(MultiWayNode root) {
        if(root == null) return;
        System.out.print(root.val + " ");
        for(MultiWayNode child : root.children) dfsTraversal(child);
    }
    
    public void bfsTraversal(MultiWayNode root) {
        if(root == null) return;
        Queue<MultiWayNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            MultiWayNode node = q.poll();
            System.out.print(node.val + " ");
            for(MultiWayNode child : node.children) q.offer(child);
        }
    }
    
    public int getHeight(MultiWayNode root) {
        if(root == null) return 0;
        int max = 0;
        for(MultiWayNode child : root.children) max = Math.max(max, getHeight(child));
        return max + 1;
    }
}