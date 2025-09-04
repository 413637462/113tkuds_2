package finalexam;

import java.util.HashMap;
import java.util.Scanner;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] seats = new int[n];
        
        for (int i = 0; i < n; i++) {
            seats[i] = sc.nextInt();
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int complement = target - seats[i];
            if (map.containsKey(complement)) {
                System.out.println(map.get(complement) + " " + i);
                return;
            }
            map.put(seats[i], i);
        }
        
        System.out.println("-1 -1");
    }
}