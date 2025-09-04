package finalexam;

import java.util.*;

public class LC25_ReverseKGroup_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.nextLine(); // 消耗換行符
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
        while (true) {
            ListNode start = prev.next;
            ListNode end = prev;
            
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            
            if (end == null) break;
            
            ListNode nextGroup = end.next;
            ListNode[] reversed = reverse(start, end);
            prev.next = reversed[0];
            reversed[1].next = nextGroup;
            prev = reversed[1];
        }
        
        // 輸出結果
        ListNode result = dummy.next;
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
    
    private static ListNode[] reverse(ListNode start, ListNode end) {
        ListNode prev = end.next;
        ListNode current = start;
        while (prev != end) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return new ListNode[]{end, start};
    }
}