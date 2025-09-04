package finalexam;

import java.util.Scanner;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        double[] nums1 = new double[n];
        double[] nums2 = new double[m];
        
        for (int i = 0; i < n; i++) {
            nums1[i] = sc.nextDouble();
        }
        for (int i = 0; i < m; i++) {
            nums2[i] = sc.nextDouble();
        }
        
        if (n > m) {
            double[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            int tmp = n;
            n = m;
            m = tmp;
        }
        
        int totalLeft = (n + m + 1) / 2;
        int left = 0;
        int right = n;
        
        while (left <= right) {
            int i = left + (right - left) / 2;
            int j = totalLeft - i;
            
            double maxLeft1 = (i == 0) ? Double.NEGATIVE_INFINITY : nums1[i - 1];
            double minRight1 = (i == n) ? Double.POSITIVE_INFINITY : nums1[i];
            double maxLeft2 = (j == 0) ? Double.NEGATIVE_INFINITY : nums2[j - 1];
            double minRight2 = (j == m) ? Double.POSITIVE_INFINITY : nums2[j];
            
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                if ((n + m) % 2 == 1) {
                    System.out.printf("%.1f\n", Math.max(maxLeft1, maxLeft2));
                } else {
                    double median = (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                    System.out.printf("%.1f\n", median);
                }
                return;
            } else if (maxLeft1 > minRight2) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }
    }
}