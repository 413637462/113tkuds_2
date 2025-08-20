import java.util.Scanner;

public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        
        long gcd = gcdRecursive(a, b);
        long lcm = lcm(a, b, gcd);
        
        System.out.println("GCD: " + gcd);
        System.out.println("LCM: " + lcm);
    }
    
    // 遞迴計算最大公因數 (GCD)
    private static long gcdRecursive(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcdRecursive(b, a % b);
    }
    
    // 計算最小公倍數 (LCM)，避免乘法溢位
    private static long lcm(long a, long b, long gcd) {
        return a / gcd * b;
    }
}/* Time Complexity: O(log(min(a, b)))
 * 說明：歐幾里得算法的時間複雜度與輸入數值的位數成比例，每次遞迴呼叫會將問題規模減少約黃金比例倍，
 *       因此時間複雜度為對數級別 O(log(min(a, b)))。
 */