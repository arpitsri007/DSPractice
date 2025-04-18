package org.codekart.arrays;

import java.util.HashMap;
import java.util.Map;

import org.codekart.arrays.DoublyLinkedList.Node;

class DoublyLinkedList {

    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public  DoublyLinkedList() {
        head = new Node(0, 0); // Dummy head
        tail = new Node(0, 0); // Dummy tail
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addAtBeginning(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        size++;
    }

    public void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    public Node removeLast() {
        if (size == 0) return null;
        Node lastNode = tail.prev;
        remove(lastNode);
        return lastNode;
    }

    public int size() {
        return size;
    }
}

public class LRUCache {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1); // cache is {1=1}
        lruCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lruCache.get(1));    // return 1
        lruCache.put(3, 3); // evicts key 2, cache is {1=1, 3=3}
        System.out.println(lruCache.get(2));    // return -1 (not found)
        lruCache.put(4, 4); // evicts key 1, cache is {4=4, 3=3}
        System.out.println(lruCache.get(1));    // return -1 (not found)
        System.out.println(lruCache.get(3));    // return 3
        System.out.println(lruCache.get(4));    // return 4
    }

    private int capacity;
    private DoublyLinkedList cache;
    private Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new DoublyLinkedList();
        this.map = new HashMap<>();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        cache.remove(node); // Remove from current position
        cache.addAtBeginning(node); // Move to head
        return node.value; // Return the value
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value; // Update value
            cache.remove(node); // Remove from current position
            cache.addAtBeginning(node); // Move to head
        } else {
            if (cache.size() == capacity) {
                Node lastNode = cache.removeLast(); // Remove LRU
                map.remove(lastNode.key); // Remove from map
            }
            Node newNode = new Node(key, value);
            cache.addAtBeginning(newNode); // Add new node to head
            map.put(key, newNode); // Add to map
        }
    }

}
