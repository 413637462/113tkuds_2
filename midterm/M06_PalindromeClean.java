import java.util.Scanner;

public class M06_PalindromeClean {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        
        if (isPalindrome(input)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
    
    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // 跳過左邊的非英文字母
            while (left < right && !Character.isLetter(s.charAt(left))) {
                left++;
            }
            // 跳過右邊的非英文字母
            while (left < right && !Character.isLetter(s.charAt(right))) {
                right--;
            }
            
            // 比較字母（忽略大小寫）
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
}