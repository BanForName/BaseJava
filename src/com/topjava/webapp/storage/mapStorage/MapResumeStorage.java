package com.topjava.webapp.storage.mapStorage;

import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.AbstractStorage;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private Map<String, Resume> resumeMap = new TreeMap<>();

    @Override
    protected void updateResume(Resume resume, Resume searchKey) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void saveResume(Resume resume, Resume searchKey) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Resume searchKey) {
        return searchKey;
    }

    @Override
    protected void deleteResume(Resume searchKey) {
        resumeMap.remove(searchKey.getUuid());
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }

    @Override
    protected Resume getKey(String uuid) {
        return resumeMap.get(uuid);
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<>(resumeMap.values());
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}