package com.topjava.webapp.storage;

import com.topjava.webapp.storage.fileStorage.FileStorage;
import com.topjava.webapp.storage.fileStorage.serialization.ObjectSerialization;

public class ObjectStreamFileStorageTest extends AbstractStorageTest {
    public ObjectStreamFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectSerialization()));
    }
}