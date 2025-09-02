package LeetCode;

class Solution {
    public String intToRoman(int num) {
        // 定義所有可能的羅馬數字符號和對應數值（從大到小排序）
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder result = new StringBuilder();
        
        // 使用貪心算法：從最大的符號開始嘗試減去
        for (int i = 0; i < values.length; i++) {
            // 當當前數值可以被減去時，將對應符號加入結果
            while (num >= values[i]) {
                num -= values[i];
                result.append(symbols[i]);
            }
        }
        
        return result.toString();
    }
}