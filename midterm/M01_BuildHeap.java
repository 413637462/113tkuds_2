import java.util.Scanner;

public class M01_BuildHeap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String type = scanner.nextLine();
        int n = Integer.parseInt(scanner.nextLine());
        int[] arr = new int[n];
        
        String[] values = scanner.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }
        
        buildHeap(arr, type);
        
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
    
    public static void buildHeap(int[] arr, String type) {
        int n = arr.length;
        // 從最後一個非葉子節點開始，自底向上進行堆化
        for (int i = n/2 - 1; i >= 0; i--) {
            if (type.equals("max")) {
                maxHeapify(arr, n, i);
            } else {
                minHeapify(arr, n, i);
            }
        }
    }
    
    // 最大堆堆化
    private static void maxHeapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        if (largest != i) {
            swap(arr, i, largest);
            maxHeapify(arr, n, largest);
        }
    }
    
    // 最小堆堆化
    private static void minHeapify(int[] arr, int n, int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && arr[left] < arr[smallest]) {
            smallest = left;
        }
        
        if (right < n && arr[right] < arr[smallest]) {
            smallest = right;
        }
        
        if (smallest != i) {
            swap(arr, i, smallest);
            minHeapify(arr, n, smallest);
        }
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}/* Time Complexity: O(n)
 * 說明：雖然每個節點都需要堆化操作，但大多數節點位於堆的底層，需要堆化的深度較小。
 *       通過數學分析可得，自底向上建堆的總操作次數上限為2n，因此時間複雜度是線性的。
 */