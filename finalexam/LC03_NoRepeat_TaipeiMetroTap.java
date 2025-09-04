package finalexam;

import java.util.HashMap;
import java.util.Scanner;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        
        if (s.length() == 0) {
            System.out.println(0);
            return;
        }
        
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            if (map.containsKey(currentChar)) {
                left = Math.max(left, map.get(currentChar) + 1);
            }
            
            map.put(currentChar, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        System.out.println(maxLength);
    }
}