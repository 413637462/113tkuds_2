package finalexam;

import java.util.*;

public class LC19_RemoveNth_Node_Clinic {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        for (int i = 0; i < n; i++) {
            current.next = new ListNode(sc.nextInt());
            current = current.next;
        }
        
        int k = sc.nextInt();
        
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        for (int i = 0; i <= k; i++) {
            fast = fast.next;
        }
        
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        slow.next = slow.next.next;
        
        // 輸出結果
        ListNode result = dummy.next;
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}