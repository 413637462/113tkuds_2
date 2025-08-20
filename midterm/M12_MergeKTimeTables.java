import java.util.Scanner;
import java.util.PriorityQueue;

public class M12_MergeKTimeTables {
    static class TimeEntry implements Comparable<TimeEntry> {
        int time;
        int listIndex;
        int elementIndex;
        
        TimeEntry(int time, int listIndex, int elementIndex) {
            this.time = time;
            this.listIndex = listIndex;
            this.elementIndex = elementIndex;
        }
        
        @Override
        public int compareTo(TimeEntry other) {
            return this.time - other.time;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int K = scanner.nextInt();
        int[][] timeTables = new int[K][];
        
        // 讀取K個時間表
        for (int i = 0; i < K; i++) {
            int len = scanner.nextInt();
            timeTables[i] = new int[len];
            for (int j = 0; j < len; j++) {
                timeTables[i][j] = scanner.nextInt();
            }
        }
        
        // 使用最小堆來合併時間表
        PriorityQueue<TimeEntry> minHeap = new PriorityQueue<>();
        
        // 將每個時間表的第一個元素加入堆中
        for (int i = 0; i < K; i++) {
            if (timeTables[i].length > 0) {
                minHeap.offer(new TimeEntry(timeTables[i][0], i, 0));
            }
        }
        
        StringBuilder result = new StringBuilder();
        
        // 不斷從堆中取出最小元素，並加入同來源的下一個元素
        while (!minHeap.isEmpty()) {
            TimeEntry current = minHeap.poll();
            result.append(current.time).append(" ");
            
            // 如果當前時間表還有下一個元素，加入堆中
            int nextIndex = current.elementIndex + 1;
            if (nextIndex < timeTables[current.listIndex].length) {
                minHeap.offer(new TimeEntry(
                    timeTables[current.listIndex][nextIndex],
                    current.listIndex,
                    nextIndex
                ));
            }
        }
        
        System.out.println(result.toString().trim());
    }
}