package lists.cycle;

class SinglyLinkedListNode {
    int data;
    SinglyLinkedListNode next;

    SinglyLinkedListNode(int data) {
        this.data = data;
    }
}

public class Solution {
    static boolean hasCycle(SinglyLinkedListNode head) {
        SinglyLinkedListNode current = head, next = head;
        while (next != null) {
            current = current.next;
            next = next.next;
            if (next != null) {
                next = next.next;
                if (next != null && next == current) {
                    return true;
                }
            }
        }
        return false;
    }
}
