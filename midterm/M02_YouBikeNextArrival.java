import java.util.Scanner;

public class M02_YouBikeNextArrival {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] times = new int[n];
        
        // 讀取並轉換時間為分鐘數
        for (int i = 0; i < n; i++) {
            String timeStr = scanner.nextLine();
            times[i] = convertToMinutes(timeStr);
        }
        
        String queryStr = scanner.nextLine();
        int queryMinutes = convertToMinutes(queryStr);
        
        // 使用二分搜尋找到第一個大於查詢時間的時間
        int resultIndex = binarySearch(times, queryMinutes);
        
        if (resultIndex == -1) {
            System.out.println("No bike");
        } else {
            // 轉換回時間格式輸出
            System.out.println(convertToTimeFormat(times[resultIndex]));
        }
    }
    
    // 將HH:mm格式轉換為分鐘數
    private static int convertToMinutes(String timeStr) {
        String[] parts = timeStr.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
    
    // 將分鐘數轉換回HH:mm格式
    private static String convertToTimeFormat(int minutes) {
        int hours = minutes / 60;
        int mins = minutes % 60;
        return String.format("%02d:%02d", hours, mins);
    }
    
    // 二分搜尋找到第一個大於目標值的元素
    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                result = mid;
                right = mid - 1;
            }
        }
        
        return result;
    }
}