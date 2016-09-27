package test;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    
    private class Node {
        int key, value;
        Node prev, next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private int capacity;
    private Map<Integer, Node> map; // store <key, node>
    private Node head, tail;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer, Node>();
        head = null;
        tail = null;
    }
    
    public int get(int key) {
        if (!map.containsKey(key))  return -1;
        else {
            moveToEnd(map.get(key));
            return map.get(key).value;
        }
    }
    
    public void set(int key, int value) {
        if (!map.containsKey(key)) {    // if the node doesn't exist, add a new node to end
            // remove LRU (first node) if it reaches capacity
            if (map.size() == capacity) {
                map.remove(head.key);
                remove(head);
            }
            // add a new node at the end
            Node node = new Node(key, value);
            map.put(key, node);
            addToEnd(node);
        } else {                        // if the node exists, revise it and move to end
            map.get(key).value = value;
            moveToEnd(map.get(key));
        }
    }
    
    private void moveToEnd(Node node) {
        if (node == tail) {
            return;
        } else {    // node is either head or middle node
            remove(node);
            addToEnd(node);
        }
    }
    
    private void remove(Node node) {
        if (node == head) {
            head = head.next;
            if (head != null)   head.prev = null;   // List has only one node
        } else if (node == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }
    
    private void addToEnd(Node node) {
        if (head == null) { // List is empty
            head = node;
            tail = node;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
    }
    
    
}