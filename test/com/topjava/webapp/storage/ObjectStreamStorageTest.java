package com.topjava.webapp.storage;

import com.topjava.webapp.storage.fileStorage.serialization.ObjectStreamStorage;

public class ObjectStreamStorageTest extends AbstractStorageTest {
    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }
}