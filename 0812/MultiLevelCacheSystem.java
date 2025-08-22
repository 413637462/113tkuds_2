import java.util.*;

public class MultiLevelCacheSystem {
    private List<CacheLevel> levels;

    public MultiLevelCacheSystem(int cap1, int cap2, int cap3) {
        levels = new ArrayList<>();
        levels.add(new CacheLevel(cap1)); // L1
        levels.add(new CacheLevel(cap2)); // L2
        levels.add(new CacheLevel(cap3)); // L3
    }

    public String get(String key) {
        for (int i = 0; i < levels.size(); i++) {
            if (levels.get(i).map.containsKey(key)) {
                String val = levels.get(i).map.get(key);
                // 將資料提升到 L1
                moveToTop(i, key, val);
                return val;
            }
        }
        return null;
    }

    public void put(String key, String value) {
        moveToTop(levels.size() - 1, key, value); // 新資料直接放到 L1
    }

    private void moveToTop(int fromLevel, String key, String value) {
        // 從原層刪除
        if (fromLevel < levels.size()) {
            levels.get(fromLevel).map.remove(key);
        }
        // 放到 L1
        insert(0, key, value);
    }

    private void insert(int levelIndex, String key, String value) {
        CacheLevel level = levels.get(levelIndex);
        if (level.map.size() >= level.capacity) {
            String oldestKey = level.order.poll();
            String oldestValue = level.map.remove(oldestKey);
            if (levelIndex + 1 < levels.size()) {
                insert(levelIndex + 1, oldestKey, oldestValue); // 移到下一層
            }
        }
        level.map.put(key, value);
        level.order.offer(key);
    }

    // Cache 層級定義
    static class CacheLevel {
        int capacity;
        Map<String, String> map;
        Queue<String> order; // 模擬 heap 優先序

        CacheLevel(int capacity) {
            this.capacity = capacity;
            this.map = new LinkedHashMap<>();
            this.order = new LinkedList<>();
        }
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem(2, 2, 2);
        cache.put("A", "1");
        cache.put("B", "2");
        cache.put("C", "3"); // A 會被移到 L2
        System.out.println(cache.get("A")); // 從 L2 提升到 L1
        System.out.println(cache.get("B")); // 從 L1
    }
}
