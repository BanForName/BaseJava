package com.topjava.webapp.storage;

import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        Object index = notExistResume(resume.getUuid());
        updateResume(resume, index);
    }

    @Override
    public void save(Resume resume) {
        Object index = ExistResume(resume.getUuid());
        saveResume(resume, index);
    }

    @Override
    public Resume get(String uuid) {
        Object index = notExistResume(uuid);
        return getResume(new Resume(uuid), index);
    }

    @Override
    public void delete(String uuid) {
        Object index = notExistResume(uuid);
        deleteResume(new Resume(uuid), index);
    }

    private Object notExistResume(String uuid) {
        Object key = getKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private Object ExistResume(String uuid) {
        Object key = getKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    protected abstract void updateResume(Resume resume, Object index);

    protected abstract void saveResume(Resume resume, Object index);

    protected abstract Resume getResume(Resume resume, Object index);

    protected abstract void deleteResume(Resume resume, Object index);

    protected abstract boolean isExist(Object object);

    protected abstract Object getKey(String uuid);
}
