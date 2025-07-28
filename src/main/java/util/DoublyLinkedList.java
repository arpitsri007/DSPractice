package util;

public class DoublyLinkedList {
    
    // create a node
    static class Node {
        int data;
        Node next;
        Node prev;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    // create a doubly linked list
    Node head;
    Node tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    
    public void addAtBeginning(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
            tail = newNode;
            
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        return;
    }

    public void addAtEnd(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }
    public void addAtPosition(int val, int pos) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else if (pos == 0) {
            addAtBeginning(val);
        } else {
            Node current = head;
            for (int i = 0; i < pos - 1 && current != null; i++) {
                current = current.next;
            }
            if (current == null) {
                return;
            }
            newNode.next = current.next;
            newNode.prev = current;
            if (current.next != null) {
                current.next.prev = newNode;
            }
            current.next = newNode;
        }
    }


    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
        

    // main function
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.addAtEnd(0);
        dll.addAtEnd(1);
        dll.addAtEnd(2);
        dll.addAtEnd(3);
        dll.addAtEnd(4);
        dll.display();
    }

}
