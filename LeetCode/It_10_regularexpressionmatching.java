package LeetCode;
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        
        // dp[i][j] 表示 s 的前 i 個字符和 p 的前 j 個字符是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; // 空字符串匹配空模式
        
        // 初始化第一行（空字符串與模式的匹配）
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2]; // * 可以匹配零個前導字符
            }
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                
                if (pc == '.' || pc == sc) {
                    // 當前字符匹配，取決於前一個狀態
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    // 處理 * 的情況
                    char prevChar = p.charAt(j - 2);
                    
                    // 情況1: * 匹配零個前導字符
                    dp[i][j] = dp[i][j - 2];
                    
                    // 情況2: * 匹配一個或多個前導字符
                    if (prevChar == '.' || prevChar == sc) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }
        
        return dp[m][n];
    }
}