package com.topjava.webapp.exception;

import java.sql.SQLException;

public class StorageException  extends RuntimeException {
    private final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String message, String uuid, Exception exception) {
        super(message, exception);
        this.uuid = uuid;
    }

    public StorageException(Exception e) {
        this(e.getMessage(), null, e);
    }

    public String getUuid() {
        return uuid;
    }
}
