package com.topjava.webapp.storage;

import com.Config;
import com.topjava.webapp.storage.sqlStorage.SqlStorage;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(new SqlStorage(Config.get().getDbUrl(), Config.get().getDbUSer(), Config.get().getDbPassword()));
    }
}