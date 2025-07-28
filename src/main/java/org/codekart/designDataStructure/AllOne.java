package org.codekart.designDataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AllOne {
    /*
     * Notes:
     * 1. AllOne is a data structure that supports the following operations:
     * 2. insert(key, value) - insert a key-value pair into the data structure.
     * 3. remove(key) - remove the key from the data structure.
     * 4. getMaxKey() - return the key with the maximum value.
     * 5. getMinKey() - return the key with the minimum value.
     */

    class DLLNode {
        Set<String> keys;
        int frequency;
        DLLNode prev;
        DLLNode next;

        public DLLNode() {
            this.keys = new HashSet<>();
            this.frequency = 0;
            this.prev = null;
            this.next = null;
        }

        public DLLNode(String key, int frequency) {
            this.keys = new HashSet<>();
            this.keys.add(key);
            this.frequency = frequency;
            this.prev = null;
            this.next = null;
        }

    }

    private Map<String, DLLNode> keyToNode;

    private DLLNode head;
    private DLLNode last;

    public AllOne() {
        this.keyToNode = new HashMap<>();
        this.head = new DLLNode();
        this.last = head;
    }

    public void inc(String key) {
        if (!keyToNode.containsKey(key)) {
            if (head.next == null || head.next.frequency != 1) {
                DLLNode newNode = new DLLNode(key, 1);
                newNode.next = head.next;
                newNode.prev = head;
                head.next = newNode;
                if (newNode.next != null) {
                    newNode.next.prev = newNode;
                }
                keyToNode.put(key, newNode);
            } else {
                DLLNode current = keyToNode.get(key);
                current.keys.add(key);
            }
        } else {
            DLLNode current = keyToNode.get(key);
            int freq = current.frequency;
            if (current.next == null || current.next.frequency != freq + 1) {
                DLLNode newNode = new DLLNode(key, freq + 1);
                newNode.next = current.next;
                newNode.prev = current;
                current.next = newNode;
                if (current.next != null) {
                    current.next.prev = newNode;
                }
            } else {
                current.next.keys.add(key);
                keyToNode.put(key, current.next);
            }
            current.keys.remove(key);
            if (current.keys.isEmpty()) {
                if (current == head.next) {
                    head.next = current.next;
                    if (current.next != null) {
                        current.next.prev = head;
                    }
                } else {
                    current.prev.next = current.next;
                    if (current.next != null) {
                        current.next.prev = current.prev;
                    }
                }
            }
        }
    }

    // TODO : complete the dec method
}
