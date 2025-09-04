package finalexam;

import java.util.*;

public class LC21_MergeTwoLists_Clinics {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        ListNode dummy1 = new ListNode(0);
        ListNode current1 = dummy1;
        for (int i = 0; i < n; i++) {
            current1.next = new ListNode(sc.nextInt());
            current1 = current1.next;
        }
        
        ListNode dummy2 = new ListNode(0);
        ListNode current2 = dummy2;
        for (int i = 0; i < m; i++) {
            current2.next = new ListNode(sc.nextInt());
            current2 = current2.next;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        ListNode list1 = dummy1.next;
        ListNode list2 = dummy2.next;
        
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        
        current.next = (list1 != null) ? list1 : list2;
        
        // 輸出結果
        ListNode result = dummy.next;
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}