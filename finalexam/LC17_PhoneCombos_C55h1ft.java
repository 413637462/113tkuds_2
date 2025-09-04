package finalexam;

import java.util.*;

public class LC17_PhoneCombos_C55h1ft {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine();
        
        if (digits.isEmpty()) {
            return;
        }
        
        String[] mapping = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };
        
        List<String> result = new ArrayList<>();
        backtrack(result, digits, mapping, new StringBuilder(), 0);
        
        for (String s : result) {
            System.out.println(s);
        }
    }
    
    private static void backtrack(List<String> result, String digits, String[] mapping, 
                                 StringBuilder current, int index) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        
        String letters = mapping[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            current.append(c);
            backtrack(result, digits, mapping, current, index + 1);
            current.deleteCharAt(current.length() - 1);
        }
    }
}