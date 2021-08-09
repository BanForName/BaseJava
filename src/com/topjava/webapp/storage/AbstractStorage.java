package com.topjava.webapp.storage;

import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) throw new NotExistStorageException(r.getUuid());
        updateResume(r, index);
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) throw new ExistStorageException(resume.getUuid());
        saveResume(resume, index);
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) throw new NotExistStorageException(uuid);
        return getResume(new Resume(uuid), index);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) throw new NotExistStorageException(uuid);
        deleteResume(new Resume(uuid), index);
    }

    @Override
    public Resume[] getAll() {
        return getAllResume();
    }

    protected abstract void updateResume(Resume resume, int index);

    protected abstract void saveResume(Resume resume, int index);

    protected abstract Resume getResume(Resume resume, int index);

    protected abstract void deleteResume(Resume resume, int index);

    protected abstract Resume[] getAllResume();

    protected abstract int getIndex(String uuid);
}
