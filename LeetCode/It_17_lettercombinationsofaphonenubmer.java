package LeetCode;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // 數字到字母的映射
    private static final String[] LETTERS = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };
    
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        backtrack(result, digits, new StringBuilder(), 0);
        return result;
    }
    
    private void backtrack(List<String> result, String digits, StringBuilder current, int index) {
        // 如果已經處理完所有數字，將當前組合加入結果
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        
        // 獲取當前數字對應的字母
        char digit = digits.charAt(index);
        String letters = LETTERS[digit - '0'];
        
        // 遍歷所有可能的字母
        for (char letter : letters.toCharArray()) {
            current.append(letter);
            backtrack(result, digits, current, index + 1);
            current.deleteCharAt(current.length() - 1); // 回溯
        }
    }
}