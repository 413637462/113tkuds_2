import java.util.Scanner;

public class M11_HeapSortWithTie {
    static class Student {
        int score;
        int index;
        
        Student(int score, int index) {
            this.score = score;
            this.index = index;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Student[] students = new Student[n];
        
        for (int i = 0; i < n; i++) {
            int score = scanner.nextInt();
            students[i] = new Student(score, i);
        }
        
        heapSort(students);
        
        for (int i = 0; i < n; i++) {
            System.out.print(students[i].score);
            if (i < n - 1) {
                System.out.print(" ");
            }
        }
    }
    
    // 堆排序
    private static void heapSort(Student[] arr) {
        int n = arr.length;
        
        // 建立最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // 一個個從堆頂取出元素
        for (int i = n - 1; i > 0; i--) {
            // 將當前堆頂元素與最後一個元素交換
            Student temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            
            // 重新堆化剩餘的元素
            heapify(arr, i, 0);
        }
    }
    
    // 堆化函數
    private static void heapify(Student[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        // 比較左子節點
        if (left < n && compareStudents(arr[left], arr[largest]) > 0) {
            largest = left;
        }
        
        // 比較右子節點
        if (right < n && compareStudents(arr[right], arr[largest]) > 0) {
            largest = right;
        }
        
        // 如果最大值不是當前節點，交換並繼續堆化
        if (largest != i) {
            Student swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            
            heapify(arr, n, largest);
        }
    }
    
    // 自定義比較函數：分數相同時索引較小者優先
    private static int compareStudents(Student a, Student b) {
        if (a.score != b.score) {
            return a.score - b.score; // 按分數升序比較
        } else {
            return b.index - a.index; // 分數相同時，索引較大者優先（因為我們用最大堆）
        }
    }
}/* Time Complexity: O(n log n)
 * 說明：堆排序的時間複雜度由兩個主要部分組成：
 *       1. 建立堆：O(n)
 *       2. 進行n次提取最大元素操作，每次需要O(log n)時間來重新堆化
 *       總時間複雜度為O(n log n)，這是堆排序的標準時間複雜度。
 */