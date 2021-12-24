package com.topjava.webapp.storage;

import com.topjava.webapp.storage.fileStorage.PathStorage;
import com.topjava.webapp.storage.fileStorage.serialization.XmlSerialization;

public class XmlStorageTest extends AbstractStorageTest {
    public XmlStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlSerialization()));
    }
}
