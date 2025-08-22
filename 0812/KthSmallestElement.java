import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallestElement {
    // 方法1：使用大小為K的Max Heap
    public static int findKthSmallestWithMaxHeap(int[] arr, int k) {
        if (arr == null || arr.length < k || k <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());

        for (int num : arr) {
            if (maxHeap.size() < k) {
                maxHeap.offer(num);
            } else if (num < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(num);
            }
        }

        return maxHeap.peek();
    }

    // 方法2：使用Min Heap然後提取K次
    public static int findKthSmallestWithMinHeap(int[] arr, int k) {
        if (arr == null || arr.length < k || k <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.offer(num);
        }

        int result = 0;
        for (int i = 0; i < k; i++) {
            result = minHeap.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 10, 4, 3, 20, 15};
        int k1 = 3;
        System.out.println("方法1結果: " + findKthSmallestWithMaxHeap(arr1, k1)); // 7
        System.out.println("方法2結果: " + findKthSmallestWithMinHeap(arr1, k1)); // 7

        int[] arr2 = {1};
        int k2 = 1;
        System.out.println("方法1結果: " + findKthSmallestWithMaxHeap(arr2, k2)); // 1
        System.out.println("方法2結果: " + findKthSmallestWithMinHeap(arr2, k2)); // 1

        int[] arr3 = {3, 1, 4, 1, 5, 9, 2, 6};
        int k3 = 4;
        System.out.println("方法1結果: " + findKthSmallestWithMaxHeap(arr3, k3)); // 3
        System.out.println("方法2結果: " + findKthSmallestWithMinHeap(arr3, k3)); // 3

        // 效率比較
        System.out.println("\n效率比較:");
        compareEfficiency();
    }

    private static void compareEfficiency() {
        int[] largeArr = new int[1000000];
        for (int i = 0; i < largeArr.length; i++) {
            largeArr[i] = (int)(Math.random() * 1000000);
        }
        int k = 50000;

        long startTime = System.nanoTime();
        findKthSmallestWithMaxHeap(largeArr, k);
        long maxHeapTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        findKthSmallestWithMinHeap(largeArr, k);
        long minHeapTime = System.nanoTime() - startTime;

        System.out.println("Max Heap方法時間: " + maxHeapTime / 1000000 + " ms");
        System.out.println("Min Heap方法時間: " + minHeapTime / 1000000 + " ms");
    }
}