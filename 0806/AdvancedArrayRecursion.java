import java.util.Arrays;

public class AdvancedArrayRecursion {
    public static void quickSort(int[] arr, int low, int high) {
        if(low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low-1;
        for(int j=low; j<high; j++)
            if(arr[j] < pivot) swap(arr, ++i, j);
        swap(arr, i+1, high);
        return i+1;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }
    
    public static int[] mergeSorted(int[] a, int[] b) {
        if(a.length==0) return b;
        if(b.length==0) return a;
        return a[0]<b[0] 
            ? concat(a[0], mergeSorted(Arrays.copyOfRange(a,1,a.length), b))
            : concat(b[0], mergeSorted(a, Arrays.copyOfRange(b,1,b.length)));
    }
    
    private static int[] concat(int x, int[] arr) {
        int[] result = new int[arr.length+1];
        result[0] = x;
        System.arraycopy(arr,0,result,1,arr.length);
        return result;
    }
}