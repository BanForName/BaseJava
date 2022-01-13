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
            System.out.println(lock1);
            synchronized (lock1) {
                System.out.println("lock object 1 " + lock1);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(lock2);

                synchronized (lock2) {
                    System.out.println("lock object 2 " + lock2);
                }
            }
        }).start();
    }
}