package finalexam;

import java.util.*;

public class LC24_SwapPairs_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        
        if (input.length == 0 || input[0].isEmpty()) {
            return;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (String s : input) {
            current.next = new ListNode(Integer.parseInt(s));
            current = current.next;
        }
        
        ListNode prev = dummy;
        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;
            
            first.next = second.next;
            second.next = first;
            prev.next = second;
            
            prev = prev.next.next;
        }
        
        // 輸出結果
        ListNode result = dummy.next;
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}