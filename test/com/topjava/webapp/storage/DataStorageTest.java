package com.topjava.webapp.storage;

import com.topjava.webapp.storage.fileStorage.PathStorage;
import com.topjava.webapp.storage.fileStorage.serialization.DataSerialization;

public class DataStorageTest extends AbstractStorageTest {
    public DataStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataSerialization()));
    }
}