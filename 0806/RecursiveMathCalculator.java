public class RecursiveMathCalculator {
    public static int combination(int n, int k) {
        if(k==0 || k==n) return 1;
        return combination(n-1,k-1) + combination(n-1,k);
    }
    
    public static int catalan(int n) {
        if(n<=1) return 1;
        int sum = 0;
        for(int i=0; i<n; i++) sum += catalan(i)*catalan(n-1-i);
        return sum;
    }
    
    public static int hanoi(int n) {
        return n==1 ? 1 : 2*hanoi(n-1)+1;
    }
    
    public static boolean isPalindrome(int num) {
        return num == reverse(num,0);
    }
    
    private static int reverse(int num, int temp) {
        if(num==0) return temp;
        return reverse(num/10, temp*10 + num%10);
    }
}