import java.util.ArrayList;
import java.util.List;

class Player {
    String id;
    int score;
    
    public Player(String id, int score) {
        this.id = id;
        this.score = score;
    }
}

class LeaderboardNode {
    Player player;
    LeaderboardNode left, right;
    int height;
    int size; // 子树大小，用于排名计算

    public LeaderboardNode(Player player) {
        this.player = player;
        this.height = 1;
        this.size = 1;
    }
}

public class AVLLeaderboardSystem {
    private LeaderboardNode root;

    // 1. 添加或更新玩家分数
    public void addOrUpdate(String playerId, int score) {
        Player player = new Player(playerId, score);
        root = addOrUpdate(root, player);
    }

    private LeaderboardNode addOrUpdate(LeaderboardNode node, Player player) {
        if (node == null) {
            return new LeaderboardNode(player);
        }

        // 如果玩家已存在，更新分数
        if (node.player.id.equals(player.id)) {
            node.player.score = player.score;
            return node;
        }

        // 按分数排序，分数相同按ID排序
        if (comparePlayers(player, node.player) < 0) {
            node.left = addOrUpdate(node.left, player);
        } else {
            node.right = addOrUpdate(node.right, player);
        }

        // 更新高度和大小
        node.height = 1 + Math.max(height(node.left), height(node.right));
        node.size = 1 + size(node.left) + size(node.right);

        // 平衡
        return balance(node);
    }

    // 2. 查询玩家排名
    public int getRank(String playerId) {
        return getRank(root, playerId, 0);
    }

    private int getRank(LeaderboardNode node, String playerId, int count) {
        if (node == null) {
            return -1; // 玩家不存在
        }

        if (node.player.id.equals(playerId)) {
            return count + size(node.right) + 1;
        }

        Player target = new Player(playerId, 0);
        if (comparePlayers(target, node.player) < 0) {
            return getRank(node.left, playerId, count + size(node.right) + 1);
        } else {
            return getRank(node.right, playerId, count);
        }
    }

    // 3. 查询前K名玩家
    public List<Player> getTopK(int k) {
        List<Player> result = new ArrayList<>();
        getTopK(root, k, result);
        return result;
    }

    private void getTopK(LeaderboardNode node, int k, List<Player> result) {
        if (node == null || result.size() >= k) {
            return;
        }

        // 先访问右子树(分数高的)
        getTopK(node.right, k, result);
        
        if (result.size() < k) {
            result.add(node.player);
            // 然后访问左子树
            getTopK(node.left, k - result.size(), result);
        }
    }

    // 比较玩家，按分数降序，分数相同按ID升序
    private int comparePlayers(Player a, Player b) {
        if (a.score != b.score) {
            return Integer.compare(b.score, a.score);
        }
        return a.id.compareTo(b.id);
    }

    // 辅助方法
    private int height(LeaderboardNode node) {
        return node == null ? 0 : node.height;
    }

    private int size(LeaderboardNode node) {
        return node == null ? 0 : node.size;
    }

    // 平衡方法 (类似之前的AVL平衡)
    private LeaderboardNode balance(LeaderboardNode node) {
        int balanceFactor = height(node.left) - height(node.right);

        if (balanceFactor > 1) {
            if (height(node.left.left) >= height(node.left.right)) {
                return rightRotate(node);
            } else {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        if (balanceFactor < -1) {
            if (height(node.right.right) >= height(node.right.left)) {
                return leftRotate(node);
            } else {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }

    private LeaderboardNode rightRotate(LeaderboardNode y) {
        LeaderboardNode x = y.left;
        LeaderboardNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        y.size = size(y.left) + size(y.right) + 1;
        x.size = size(x.left) + size(x.right) + 1;

        return x;
    }

    private LeaderboardNode leftRotate(LeaderboardNode x) {
        LeaderboardNode y = x.right;
        LeaderboardNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        x.size = size(x.left) + size(x.right) + 1;
        y.size = size(y.left) + size(y.right) + 1;

        return y;
    }
}