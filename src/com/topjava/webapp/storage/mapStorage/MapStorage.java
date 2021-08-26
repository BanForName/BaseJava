package com.topjava.webapp.storage.mapStorage;

import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.AbstractStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {
    private Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected void updateResume(Resume resume, String searchKey) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void saveResume(Resume resume, String searchKey) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getResume(String searchKey) {
        return resumeMap.get(searchKey);
    }

    @Override
    protected void deleteResume(String searchKey) {
        resumeMap.remove(searchKey);
    }

    @Override
    protected boolean isExist(String searchKey) {
        return resumeMap.containsKey(searchKey);
    }

    @Override
    protected String getKey(String searchKey) {
        return searchKey;
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
