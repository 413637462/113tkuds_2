package LeetCode;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }
        
        int currentRow = 0;
        boolean goingDown = false;
        
        for (char c : s.toCharArray()) {
            rows.get(currentRow).append(c);
            
            // 判斷是否需要改變方向
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }
            
            currentRow += goingDown ? 1 : -1;
        }
        
        // 合併所有行的字符串
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        
        return result.toString();
    }
}