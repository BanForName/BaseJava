package com;

public class MainDeadLock {
    private static final Object objectLock1 = new Object();
    private static final Object objectLock2 = new Object();

    public static void main(String[] args) {
        lockObject(objectLock1, objectLock2);
        lockObject(objectLock2, objectLock1);
    }

    private static void lockObject(Object lock1, Object lock2) {
        new Thread(() -> {
            tryHoldingMessage(lock1);
            synchronized (lock1) {
                holdingMessage(lock1);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tryHoldingMessage(lock2);
                synchronized (lock2) {
                    holdingMessage(lock2);
                }
            }
        }).start();
    }

    private static void holdingMessage(Object lock) {
        System.out.println(Thread.currentThread().getName() + " захватил " + lock);
    }

    private static void tryHoldingMessage(Object lock) {
        System.out.println(Thread.currentThread().getName() + " пытается захватить " + lock);
    }
}