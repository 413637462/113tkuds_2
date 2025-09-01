package LeetCode;
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 確保 nums1 是較短的陣列，以減少二分搜索的範圍
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        
        int m = nums1.length;
        int n = nums2.length;
        int totalLeft = (m + n + 1) / 2; // 左半部分應該包含的元素數量
        
        int left = 0;
        int right = m;
        
        while (left <= right) {
            int partition1 = left + (right - left) / 2; // nums1 的分割點
            int partition2 = totalLeft - partition1;    // nums2 的分割點
            
            int nums1Left = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int nums1Right = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];
            int nums2Left = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int nums2Right = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];
            
            if (nums1Left <= nums2Right && nums2Left <= nums1Right) {
                // 找到正確的分割點
                if ((m + n) % 2 == 1) {
                    return Math.max(nums1Left, nums2Left);
                } else {
                    return (Math.max(nums1Left, nums2Left) + Math.min(nums1Right, nums2Right)) / 2.0;
                }
            } else if (nums1Left > nums2Right) {
                right = partition1 - 1;
            } else {
                left = partition1 + 1;
            }
        }
        
        throw new IllegalArgumentException("Input arrays are not sorted");
    }
}