package LeetCode;
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        while (head != null) {
            ListNode tail = prev;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return dummy.next;
                }
            }
            ListNode nextGroup = tail.next;
            ListNode[] reversed = reverse(head, tail);
            head = reversed[0];
            tail = reversed[1];
            prev.next = head;
            tail.next = nextGroup;
            prev = tail;
            head = tail.next;
        }
        
        return dummy.next;
    }
    
    private ListNode[] reverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode current = head;
        while (prev != tail) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return new ListNode[]{tail, head};
    }
}