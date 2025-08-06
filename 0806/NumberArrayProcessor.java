import java.util.*;

public class NumberArrayProcessor {
    public static int[] removeDuplicates(int[] arr) {
        return Arrays.stream(arr).distinct().toArray();
    }

    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];
        System.arraycopy(a, 0, merged, 0, a.length);
        System.arraycopy(b, 0, merged, a.length, b.length);
        Arrays.sort(merged);
        return merged;
    }

    public static int mostFrequent(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num : arr) freq.put(num, freq.getOrDefault(num, 0) + 1);
        return Collections.max(freq.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static void splitArray(int[] arr) {
        int splitIndex = arr.length / 2;
        System.out.println("前半: " + Arrays.toString(Arrays.copyOfRange(arr, 0, splitIndex)));
        System.out.println("後半: " + Arrays.toString(Arrays.copyOfRange(arr, splitIndex, arr.length)));
    }
}