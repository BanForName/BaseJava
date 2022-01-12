package com;

// Lazy singleton double checked locking
public class LazySingleton {
    volatile private static LazySingleton INSTANCE;
    int i;
    double sin = Math.sin(10);

    private LazySingleton() {
    }

//    public static LazySingleton getInstance() {
//        if (INSTANCE == null) {
//            synchronized (LazySingleton.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new LazySingleton();
//                }
//            }
//        }
//        return INSTANCE;
//    }

    private static class LazySingletonHolder {
        private static final LazySingleton INSTANCE = new LazySingleton();

    }

    public static LazySingleton getInstance() {
        return LazySingletonHolder.INSTANCE;
    }
}