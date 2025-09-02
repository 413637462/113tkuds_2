package LeetCode;

import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 先排序
        
        for (int i = 0; i < nums.length - 2; i++) {
            // 跳過重複的第一個數字
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // 跳過重複的左指針元素
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // 跳過重複的右指針元素
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; // 和太小，左指針右移
                } else {
                    right--; // 和太大，右指針左移
                }
            }
        }
        
        return result;
    }
}