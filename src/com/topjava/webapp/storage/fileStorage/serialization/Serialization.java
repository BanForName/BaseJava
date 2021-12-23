package com.topjava.webapp.storage.fileStorage.serialization;

import com.topjava.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Serialization {
    void resumeWrite(Resume resume, OutputStream os) throws IOException;

    Resume resumeRead(InputStream is) throws IOException;
}
