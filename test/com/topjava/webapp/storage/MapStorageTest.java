package com.topjava.webapp.storage;

import com.topjava.webapp.storage.mapStorage.MapStorage;

public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }
}