package com;

public class TestSingleton {
    private static final TestSingleton istance = new TestSingleton();

    public static TestSingleton getInstance() {
        return istance;
    }

    private TestSingleton() {

    }
}