package com.topjava.webapp.storage;

import com.topjava.webapp.storage.abstractStorage.ArrayStorage;

public class ArrayStorageTest extends AbstractArrayStorageTest{

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }
}