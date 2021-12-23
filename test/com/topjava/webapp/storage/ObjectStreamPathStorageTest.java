package com.topjava.webapp.storage;

import com.topjava.webapp.storage.fileStorage.PathStorage;
import com.topjava.webapp.storage.fileStorage.serialization.ObjectStreamSerialization;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {
    public ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerialization()));
    }
}
