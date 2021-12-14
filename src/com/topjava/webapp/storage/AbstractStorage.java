package com.topjava.webapp.storage;

import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<T> implements Storage {
    protected static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void update(Resume resume) {
        LOG.info("Upddate: " + resume);
        T searchKey = getExistSearchKey(resume.getUuid());
        updateResume(resume, searchKey);
    }

    @Override
    public void save(Resume resume) {
        LOG.info("save: " + resume);
        T searchKey = getNotExistSearchKey(resume.getUuid());
        saveResume(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("get: " + uuid);
        T searchKey = getExistSearchKey(uuid);
        return getResume(searchKey);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("delete: " + uuid);
        T searchKey = getExistSearchKey(uuid);
        deleteResume(searchKey);
    }

    private T getNotExistSearchKey(String uuid) {
        T key = getKey(uuid);
        if (isExist(key)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    private T getExistSearchKey(String uuid) {
        T key = getKey(uuid);
        if (!isExist(key)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = allSortedResume();
        Collections.sort(list);
        return list;
    }

    protected abstract void updateResume(Resume resume, T searchKey);

    protected abstract void saveResume(Resume resume, T searchKey);

    protected abstract Resume getResume(T searchKey);

    protected abstract void deleteResume(T searchKey);

    protected abstract boolean isExist(T searchKey);

    protected abstract T getKey(String uuid);

    protected abstract List<Resume> allSortedResume();
}
