import java.util.PriorityQueue;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class SlidingWindowMedian {
    private PriorityQueue<Integer> maxHeap; // 存儲較小的一半數字
    private PriorityQueue<Integer> minHeap; // 存儲較大的一半數字

    public SlidingWindowMedian() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new double[0];
        }

        double[] result = new double[nums.length - k + 1];
        
        for (int i = 0; i < nums.length; i++) {
            addNumber(nums[i]);
            
            // 當窗口大小達到k時，開始計算中位數
            if (i >= k - 1) {
                result[i - k + 1] = findMedian();
                
                // 移除窗口最左邊的元素
                removeNumber(nums[i - k + 1]);
            }
        }
        
        return result;
    }

    private void addNumber(int num) {
        // 先加入maxHeap，然後平衡兩個堆
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        
        // 保持maxHeap的大小 >= minHeap
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    private void removeNumber(int num) {
        // 從對應的堆中移除元素
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        
        // 重新平衡兩個堆
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        } else if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
    }

    private double findMedian() {
        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            return 0;
        }
        
        if (maxHeap.size() == minHeap.size()) {
            return ((double)maxHeap.peek() + (double)minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian solution = new SlidingWindowMedian();
        
        // 測試案例1
        int[] nums1 = {1,3,-1,-3,5,3,6,7};
        int k1 = 3;
        double[] result1 = solution.medianSlidingWindow(nums1, k1);
        System.out.println(Arrays.toString(result1)); // [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]
        
        // 測試案例2
        int[] nums2 = {1,2,3,4};
        int k2 = 2;
        double[] result2 = solution.medianSlidingWindow(nums2, k2);
        System.out.println(Arrays.toString(result2)); // [1.5, 2.5, 3.5]
        
        // 邊界測試：空輸入
        double[] result3 = solution.medianSlidingWindow(new int[0], 0);
        System.out.println(Arrays.toString(result3)); // []
    }
}