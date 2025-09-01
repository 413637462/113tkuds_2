import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedArrays {
    static class ArrayElement implements Comparable<ArrayElement> {
        int value;
        int arrayIndex;
        int elementIndex;

        public ArrayElement(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }

        @Override
        public int compareTo(ArrayElement other) {
            return this.value - other.value;
        }
    }

    public static int[] mergeKSortedArrays(List<int[]> arrays) {
        if (arrays == null || arrays.size() == 0) {
            return new int[0];
        }

        PriorityQueue<ArrayElement> minHeap = new PriorityQueue<>();
        int totalSize = 0;

        // 初始化堆，加入每個陣列的第一個元素
        for (int i = 0; i < arrays.size(); i++) {
            if (arrays.get(i) != null && arrays.get(i).length > 0) {
                minHeap.offer(new ArrayElement(arrays.get(i)[0], i, 0));
                totalSize += arrays.get(i).length;
            }
        }

        int[] result = new int[totalSize];
        int index = 0;

        while (!minHeap.isEmpty()) {
            ArrayElement current = minHeap.poll();
            result[index++] = current.value;

            // 如果當前陣列還有下一個元素，加入堆中
            if (current.elementIndex + 1 < arrays.get(current.arrayIndex).length) {
                minHeap.offer(new ArrayElement(
                    arrays.get(current.arrayIndex)[current.elementIndex + 1],
                    current.arrayIndex,
                    current.elementIndex + 1
                ));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // 測試案例1
        List<int[]> test1 = Arrays.asList(
            new int[]{1, 4, 5},
            new int[]{1, 3, 4},
            new int[]{2, 6}
        );
        System.out.println(Arrays.toString(mergeKSortedArrays(test1))); // [1, 1, 2, 3, 4, 4, 5, 6]

        // 測試案例2
        List<int[]> test2 = Arrays.asList(
            new int[]{1, 2, 3},
            new int[]{4, 5, 6},
            new int[]{7, 8, 9}
        );
        System.out.println(Arrays.toString(mergeKSortedArrays(test2))); // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // 測試案例3
        List<int[]> test3 = Arrays.asList(
            new int[]{1},
            new int[]{0}
        );
        System.out.println(Arrays.toString(mergeKSortedArrays(test3))); // [0, 1]

        // 邊界測試：空輸入
        System.out.println(Arrays.toString(mergeKSortedArrays(new ArrayList<>()))); // []
    }
}