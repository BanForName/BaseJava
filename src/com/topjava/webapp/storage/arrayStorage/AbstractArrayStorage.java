package com.topjava.webapp.storage.arrayStorage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.AbstractStorage;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void updateResume(Resume resume, Integer searchKey) {
        storage[(int) searchKey] = resume;
    }

    @Override
    public void saveResume(Resume resume, Integer searchKey) {
        if (size != STORAGE_LIMIT) {
            insertElement(resume, (int) searchKey);
            size++;
        } else {
            throw new StorageException("Хранилище переполнено", resume.getUuid());
        }
    }

    @Override
    public Resume getResume(Integer searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    public void deleteResume(Integer searchKey) {
        int indx = (int) searchKey;
        size--;
        if (size - indx >= 0) System.arraycopy(storage, indx + 1, storage, indx, size - indx);
    }

    @Override
    public List<Resume> getAllSorted() {;
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected boolean isExist(Integer index) {
        return (int) index >= 0;
    }

    protected abstract Integer getKey(String uuid);

    protected abstract void insertElement(Resume resume, int index);
}
