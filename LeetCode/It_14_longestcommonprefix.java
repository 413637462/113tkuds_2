package LeetCode;

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        
        // 以第一個字符串作為基準
        String first = strs[0];
        
        for (int i = 0; i < first.length(); i++) {
            char currentChar = first.charAt(i);
            
            // 檢查其他字符串在相同位置是否匹配
            for (int j = 1; j < strs.length; j++) {
                // 如果當前字符串長度不足或字符不匹配
                if (i >= strs[j].length() || strs[j].charAt(i) != currentChar) {
                    return first.substring(0, i); // 返回當前匹配的前綴
                }
            }
        }
        
        return first; // 第一個字符串就是最長公共前綴
    }
}