import java.util.Scanner;

public class M04_TieredTaxSimple {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] incomes = new int[n];
        int[] taxes = new int[n];
        int totalTax = 0;
        
        for (int i = 0; i < n; i++) {
            incomes[i] = Integer.parseInt(scanner.nextLine());
            taxes[i] = calculateTax(incomes[i]);
            totalTax += taxes[i];
        }
        
        for (int i = 0; i < n; i++) {
            System.out.println("Tax: " + taxes[i]);
        }
        
        int average = (int) Math.round((double) totalTax / n);
        System.out.println("Average: " + average);
    }
    
    private static int calculateTax(int income) {
        int tax = 0;
        
        // 第一級距: 0-120000, 5%
        if (income > 120000) {
            tax += 120000 * 0.05;
        } else {
            tax += income * 0.05;
            return tax;
        }
        
        // 第二級距: 120001-500000, 12%
        if (income > 500000) {
            tax += (500000 - 120000) * 0.12;
        } else {
            tax += (income - 120000) * 0.12;
            return tax;
        }
        
        // 第三級距: 500001-1000000, 20%
        if (income > 1000000) {
            tax += (1000000 - 500000) * 0.20;
        } else {
            tax += (income - 500000) * 0.20;
            return tax;
        }
        
        // 第四級距: 1000001以上, 30%
        tax += (income - 1000000) * 0.30;
        
        return tax;
    }
}/* Time Complexity: O(n)
 * 說明：對於每個收入值，我們進行固定次數的比較和計算（最多4次比較），
 *       因此每個收入值的計算時間是常數O(1)，總共n個收入值，總時間複雜度為O(n)。
 */