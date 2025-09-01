package LeetCode;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            // 如果當前字符已經存在，移動左指針直到移除重複字符
            while (set.contains(currentChar)) {
                set.remove(s.charAt(left));
                left++;
            }
            
            set.add(currentChar);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}