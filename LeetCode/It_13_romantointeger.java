package LeetCode;

class Solution {
    public int romanToInt(String s) {
        // 創建羅馬數字到數值的映射
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        
        int result = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            int current = romanMap.get(s.charAt(i));
            
            // 檢查是否為減法情況（當前符號值 < 下一個符號值）
            if (i < n - 1 && current < romanMap.get(s.charAt(i + 1))) {
                result -= current; // 減去當前值
            } else {
                result += current; // 加上當前值
            }
        }
        
        return result;
    }
}