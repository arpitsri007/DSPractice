package org.codekart.designDataStructure;

public class MyCircularQueue {
    // leetcode 622 - Design Circular Queue
    private int[] queue;
    private int front;
    private int rear;
    private int size;

    public MyCircularQueue(int k) {
        this.queue = new int[k];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        queue[rear] = value;
        rear = (rear + 1) % queue.length;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % queue.length;
        size--;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return queue[front];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return queue[(rear - 1 + queue.length) % queue.length];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == queue.length;
    }

    // main method
    public static void main(String[] args) {
        MyCircularQueue myCircularQueue = new MyCircularQueue(3);
    }
}
