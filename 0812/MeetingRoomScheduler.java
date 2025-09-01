import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomScheduler {
    
    // 方法1：計算最少需要多少會議室
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        
        // 按開始時間排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        // 最小堆存儲會議結束時間
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.offer(intervals[0][1]);
        
        for (int i = 1; i < intervals.length; i++) {
            // 如果當前會議開始時間 >= 最早結束的會議時間
            if (intervals[i][0] >= heap.peek()) {
                heap.poll(); // 可以重用會議室
            }
            heap.offer(intervals[i][1]); // 加入當前會議結束時間
        }
        
        return heap.size();
    }
    
    // 方法2：在N個會議室限制下最大化總會議時間
    public static int maxMeetingTime(int[][] intervals, int N) {
        if (intervals == null || intervals.length == 0 || N <= 0) {
            return 0;
        }
        
        // 按結束時間排序
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        
        // 最大堆存儲會議時間
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        int totalTime = 0;
        
        for (int[] interval : intervals) {
            int duration = interval[1] - interval[0];
            if (heap.size() < N) {
                heap.offer(duration);
                totalTime += duration;
            } else if (duration > heap.peek()) {
                totalTime -= heap.poll();
                heap.offer(duration);
                totalTime += duration;
            }
        }
        
        return totalTime;
    }

    public static void main(String[] args) {
        // 測試最少會議室數量
        int[][] meetings1 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println("最少需要會議室: " + minMeetingRooms(meetings1)); // 2
        
        int[][] meetings2 = {{9, 10}, {4, 9}, {4, 17}};
        System.out.println("最少需要會議室: " + minMeetingRooms(meetings2)); // 2
        
        int[][] meetings3 = {{1, 5}, {8, 9}, {8, 9}};
        System.out.println("最少需要會議室: " + minMeetingRooms(meetings3)); // 2
        
        // 測試在N個會議室限制下最大化總會議時間
        int[][] meetings4 = {{1, 4}, {2, 3}, {4, 6}};
        System.out.println("最大總會議時間: " + maxMeetingTime(meetings4, 1)); // 5
        
        int[][] meetings5 = {{1, 3}, {2, 4}, {3, 5}, {4, 6}};
        System.out.println("最大總會議時間: " + maxMeetingTime(meetings5, 2)); // 6
    }
}