package com.topjava.webapp.storage;

import com.topjava.webapp.storage.fileStorage.serialization.ObjectStreamPathStorage;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {
    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(STORAGE_DIR.toString()));
    }
}
