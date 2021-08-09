package com.topjava.webapp.storage;

import com.topjava.webapp.storage.abstractStorage.SortedArrayStorage;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }
}