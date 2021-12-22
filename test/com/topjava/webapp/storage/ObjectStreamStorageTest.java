package com.topjava.webapp.storage;

import com.topjava.webapp.storage.fileStorage.FileStorage;
import com.topjava.webapp.storage.fileStorage.serialization.ObjectStreamStorage;

public class ObjectStreamStorageTest extends AbstractStorageTest {
    public ObjectStreamStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStorage()));
    }
}