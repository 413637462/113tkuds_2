public class ValidMaxHeapChecker {
    public static boolean isValidMaxHeap(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return true;
        }

        int lastNonLeaf = (arr.length - 2) / 2;

        for (int i = 0; i <= lastNonLeaf; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < arr.length && arr[i] < arr[left]) {
                System.out.println("false (索引" + left + "的" + arr[left] + "大於父節點" + arr[i] + ")");
                return false;
            }

            if (right < arr.length && arr[i] < arr[right]) {
                System.out.println("false (索引" + right + "的" + arr[right] + "大於父節點" + arr[i] + ")");
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] test1 = {100, 90, 80, 70, 60, 75, 65};
        System.out.println(isValidMaxHeap(test1)); // true

        int[] test2 = {100, 90, 80, 95, 60, 75, 65};
        System.out.println(isValidMaxHeap(test2)); // false (索引3的95大於父節點90)

        int[] test3 = {50};
        System.out.println(isValidMaxHeap(test3)); // true

        int[] test4 = {};
        System.out.println(isValidMaxHeap(test4)); // true
    }
}