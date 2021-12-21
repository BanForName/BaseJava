package com.topjava.webapp.storage.fileStorage.serialization;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.fileStorage.AbstractPathStorage;

import java.io.*;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    public ObjectStreamPathStorage(String dir) {
        super(dir);

    }

    @Override
    protected void resumeWrite(Resume resume, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
        }
    }

    @Override
    protected Resume resumeRead(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Unable to read the file", null, e);
        }
    }
}