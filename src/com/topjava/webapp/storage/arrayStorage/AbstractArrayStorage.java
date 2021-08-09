package com.topjava.webapp.storage.arrayStorage;

import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.AbstractStorage;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void updateResume(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    public void saveResume(Resume resume, int index) {
        if (size != STORAGE_LIMIT) {
            insertElement(resume, index);
            size++;
        } else {
            throw new StorageException("Хранилище переполнено", resume.getUuid());
        }
    }

    @Override
    public Resume getResume(Resume resume, int index) {
        return storage[index];
    }

    @Override
    public void deleteResume(Resume resume, int index) {
        size--;
        if (size - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - index);
    }

    @Override
    public Resume[] getAllResume() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void insertElement(Resume resume, int index);
}
