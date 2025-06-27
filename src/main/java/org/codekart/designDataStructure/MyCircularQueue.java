package org.codekart.designDataStructure;

public class MyCircularQueue {
   
    /*
     * Notes:
     * 1. Circular Queue is a queue that is implemented using a circular array.
     * 2. The front and rear pointers are used to indicate the position of the front and rear elements of the queue.
     * 3. The front pointer is used to indicate the position of the front element of the queue.
     * 4. The rear pointer is used to indicate the position of the rear element of the queue.
     * 
     *  insert at rear, shift rear pointer to next position --> (rear + 1) % capacity
     *  insert at front, shift front pointer to previous position --> (front - 1 + capacity) % capacity
     * 
     *  remove from front, shift front pointer to next position --> (front + 1) % capacity
     *  remove from rear, shift rear pointer to previous position --> (rear - 1 + capacity) % capacity
     * 
     */

    // single line comment
    // leetcode 641 - Design Circular Deque
    private int[] deque;
    private int front;
    private int rear;
    private int capacity;
    private int currentSize;

    public MyCircularQueue(int k) {
        this.deque = new int[k];
        this.front = 0;
        this.rear = 0;
        this.capacity = k;
        this.currentSize = 0;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            front = rear = 0;
        } else {    
            front = (front - 1 + capacity) % capacity;
        }
        deque[front] = value;
        currentSize++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            front = rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }
        deque[rear] = value;
        currentSize++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % capacity;
        currentSize--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        rear = (rear - 1 + capacity) % capacity;
        currentSize--;
        return true;
    }

    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return deque[front];
    }

    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return deque[rear];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == capacity;
    }

    // main method
    public static void main(String[] args) {
        MyCircularQueue myCircularQueue = new MyCircularQueue(3);
        System.out.println(myCircularQueue.insertLast(1)); // return True
        System.out.println(myCircularQueue.insertLast(2)); // return True
        System.out.println(myCircularQueue.insertFront(3)); // return True
        System.out.println(myCircularQueue.insertFront(4)); // return False, the queue is full.
        System.out.println(myCircularQueue.getRear());     // return 2
        System.out.println(myCircularQueue.isFull());     // return True
        System.out.println(myCircularQueue.deleteLast()); // return True
        System.out.println(myCircularQueue.insertFront(4)); // return True
        System.out.println(myCircularQueue.getFront());    // return 4
        
    }

}
