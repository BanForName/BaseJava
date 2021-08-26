package com.topjava.webapp.storage;

import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.Resume;

public abstract class AbstractStorage<SK> implements Storage {

    @Override
    public void update(Resume resume) {
        SK searchKey = getExistSearchKey(resume.getUuid());
        updateResume(resume, searchKey);
    }

    @Override
    public void save(Resume resume) {
        SK searchKey = getNotExistSearchKey(resume.getUuid());
        saveResume(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = getExistSearchKey(uuid);
        return getResume(searchKey);
    }

    @Override
    public void delete(String uuid) {
        SK searchKey = getExistSearchKey(uuid);
        deleteResume(searchKey);
    }

    private SK getNotExistSearchKey(String uuid) {
        SK key = getKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    private SK getExistSearchKey(String uuid) {
        SK key = getKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    protected abstract void updateResume(Resume resume, SK searchKey);

    protected abstract void saveResume(Resume resume, SK searchKey);

    protected abstract Resume getResume(SK searchKey);

    protected abstract void deleteResume(SK searchKey);

    protected abstract boolean isExist(SK object);

    protected abstract SK getKey(String uuid);
}
