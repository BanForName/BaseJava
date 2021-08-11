package com.topjava.webapp.storage.mapStorage;

import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.AbstractStorage;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> resumeMap = new LinkedHashMap<>();

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void saveResume(Resume resume, Object searchKey) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(Resume resume, Object searchKey) {
        return resumeMap.get(resume.getUuid());
    }

    @Override
    protected void deleteResume(Resume resume, Object searchKey) {
        resumeMap.remove(resume.getUuid());
    }

    @Override
    protected boolean isExist(Object uuid) {
        return uuid != null;
    }

    @Override
    protected Object getKey(String uuid) {
        return resumeMap.get(uuid);
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public Resume[] getAll() {
        return resumeMap.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
