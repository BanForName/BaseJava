package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println(uuid + " отсутствует в хранилище.");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            size--;
            if (size - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - index);
        } else {
            System.out.println(uuid + " отсутствует в хранилище");
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) return storage[index];
        System.out.println(uuid + " отсутствует в хранилище");
        return null;
    }

    protected abstract int getIndex(String uuid);

    public abstract void save(Resume resume);
}
