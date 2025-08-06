public class SelectionSortImplementation {
    public static void selectionSort(int[] arr) {
        int comparisons = 0, swaps = 0;
        for(int i=0; i<arr.length-1; i++) {
            int minIndex = i;
            for(int j=i+1; j<arr.length; j++) {
                comparisons++;
                if(arr[j] < arr[minIndex]) minIndex = j;
            }
            if(minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }
            System.out.println("第"+(i+1)+"輪: "+Arrays.toString(arr));
        }
        System.out.println("比較次數: "+comparisons+" 交換次數: "+swaps);
    }
    
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        selectionSort(arr);
    }
}