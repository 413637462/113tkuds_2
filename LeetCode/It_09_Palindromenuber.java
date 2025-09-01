package LeetCode;
class Solution {
    public boolean isPalindrome(int x) {
        // 負數和以0結尾的非零數都不是回文數
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        
        int reversed = 0;
        int original = x;
        
        // 反轉一半的數字
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        
        // 對於偶數位數：x == reversed
        // 對於奇數位數：x == reversed / 10
        return x == reversed || x == reversed / 10;
    }
}