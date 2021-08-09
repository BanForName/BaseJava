package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.Collection;
import java.util.LinkedList;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        updateResume(r);
    }

    @Override
    public void save(Resume r) {
        saveResume(r);
    }

    @Override
    public Resume get(String uuid) {
        return getResume(new Resume(uuid));
    }

    @Override
    public void delete(String uuid) {
        deleteResume(new Resume(uuid));
    }

    @Override
    public Resume[] getAll() {
        return getAllResume();
    }

    protected abstract void updateResume(Resume resume);

    protected abstract void saveResume(Resume resume);

    protected abstract Resume getResume(Resume resume);

    protected abstract void deleteResume(Resume resume);

    protected abstract Resume[] getAllResume();

    protected abstract int getIndex(String uuid);
}
