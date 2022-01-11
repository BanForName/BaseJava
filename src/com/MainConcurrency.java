package com;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    private static final Object LOCK = new Object();
    private static final int THREADS_NUMBER = 10_000;
    private static int counter;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", "
                + Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());

        final MainConcurrency concurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread1 = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    concurrency.inc();
                }
            });
            thread1.start();
            threads.add(thread1);
            thread1.join();
        }

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(counter);
    }

    private synchronized void inc() {
        counter++;
    }
}
