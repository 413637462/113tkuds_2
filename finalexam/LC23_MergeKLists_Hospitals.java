package finalexam;

import java.util.*;

public class LC23_MergeKLists_Hospitals {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        
        for (int i = 0; i < k; i++) {
            ListNode dummy = new ListNode(0);
            ListNode current = dummy;
            while (true) {
                int num = sc.nextInt();
                if (num == -1) break;
                current.next = new ListNode(num);
                current = current.next;
            }
            if (dummy.next != null) {
                pq.offer(dummy.next);
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            current.next = node;
            current = current.next;
            if (node.next != null) {
                pq.offer(node.next);
            }
        }
        
        // 輸出結果
        ListNode result = dummy.next;
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}