package LeetCode;
class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int index = 0;
        int n = s.length();
        int sign = 1;
        int result = 0;
        
        // 1. 跳過前導空白字符
        while (index < n && s.charAt(index) == ' ') {
            index++;
        }
        
        // 2. 處理符號
        if (index < n && (s.charAt(index) == '+' || s.charAt(index) == '-')) {
            sign = (s.charAt(index) == '-') ? -1 : 1;
            index++;
        }
        
        // 3. 轉換數字並處理溢出
        while (index < n && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index) - '0';
            
            // 檢查是否會溢出
            if (result > Integer.MAX_VALUE / 10 || 
                (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            
            result = result * 10 + digit;
            index++;
        }
        
        return result * sign;
    }
}