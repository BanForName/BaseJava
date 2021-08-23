package com.topjava.webapp.storage.listStorage;

import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.AbstractStorage;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return resumeList;
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        resumeList.set((Integer) searchKey, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object searchKey) {
        resumeList.add(resume);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return resumeList.get((Integer) searchKey);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        resumeList.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (uuid.equals(resumeList.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }
}
