package org.codekart.LinkedList;

public class BinaryNumberConversion {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // leetcode 1290 - Convert Binary Number in a Linked List to Integer
    // Approach - 1: Using Stack
    public int getDecimalValue(ListNode head) {
       ListNode current = head;
       StringBuilder sb = new StringBuilder();
       while(current != null) {
        sb.append(current.val);
        current = current.next;
       }
       // convert binary to decimal
       int decimal = 0;
       for(int i = 0; i < sb.length(); i++) {
        decimal += (sb.charAt(i) - '0') * Math.pow(2, sb.length() - i - 1);
       }
       return decimal;
    }

    // Approach - 2: Using Bit Manipulation
    public int getDecimalValueOptimized(ListNode head) {
        ListNode current = head;
        int decimal = 0;
        while(current != null) {
            decimal = (decimal << 1) | current.val;
            current = current.next;
        }
        return decimal;
    }
}
