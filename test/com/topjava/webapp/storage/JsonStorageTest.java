package com.topjava.webapp.storage;

import com.topjava.webapp.storage.fileStorage.PathStorage;
import com.topjava.webapp.storage.fileStorage.serialization.JsonSerialization;

public class JsonStorageTest extends AbstractStorageTest {
    public JsonStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonSerialization()));
    }
}