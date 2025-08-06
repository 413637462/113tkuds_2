public class RecursionVsIteration {
    // 二項式係數
    public static int binomialRecursive(int n, int k) {
        if(k==0 || k==n) return 1;
        return binomialRecursive(n-1,k-1) + binomialRecursive(n-1,k);
    }
    
    public static int binomialIterative(int n, int k) {
        int res = 1;
        if(k>n-k) k=n-k;
        for(int i=0; i<k; i++) {
            res *= (n-i);
            res /= (i+1);
        }
        return res;
    }
    
    // 括號配對檢查
    public static boolean isBalancedRecursive(String s) {
        return checkBalance(s,0,0)==0;
    }
    
    private static int checkBalance(String s, int i, int count) {
        if(count<0) return -1;
        if(i==s.length()) return count;
        char c = s.charAt(i);
        if(c=='(') return checkBalance(s,i+1,count+1);
        if(c==')') return checkBalance(s,i+1,count-1);
        return checkBalance(s,i+1,count);
    }
    
    public static boolean isBalancedIterative(String s) {
        int count = 0;
        for(char c : s.toCharArray()) {
            if(c=='(') count++;
            if(c==')') if(--count<0) return false;
        }
        return count==0;
    }
}