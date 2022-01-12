package com;

public class MainDeadLock {
    private static Object objectLock1 = new Object();
    private static Object objectLock2 = new Object();

    public static void main(String[] args) {
        Thread1 th1 = new Thread1();
        Thread2 th2 = new Thread2();
        th1.start();
        th2.start();
    }

    private static class Thread1 extends Thread {
        private static int count;

        @Override
        public void run() {
            synchronized (objectLock1) {
                for (int i = 0; i < 10_000; i++) {
                    counter();
                }
            }
        }
        public static void counter() {
            synchronized (objectLock2) {
                count++;
                System.out.println(count);
            }
        }
    }

    private static class Thread2 extends Thread {
        private static int count;

        @Override
        public void run() {
            synchronized (objectLock2) {
                for (int i = 0; i < 10_000; i++) {
                    counter();
                }
            }
        }

        public static void counter() {
            synchronized (objectLock1) {
                count++;
                System.out.println(count);
            }
        }
    }
}