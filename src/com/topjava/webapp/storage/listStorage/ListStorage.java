package com.topjava.webapp.storage.listStorage;

import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.AbstractStorage;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public List<Resume> allSortedResume() {
        return new ArrayList<>(resumeList);
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    protected void updateResume(Resume resume, Integer searchKey) {
        resumeList.set(searchKey, resume);
    }

    @Override
    protected void saveResume(Resume resume, Integer searchKey) {
        resumeList.add(resume);
    }

    @Override
    protected Resume getResume(Integer searchKey) {
        return resumeList.get(searchKey);
    }

    @Override
    protected void deleteResume(Integer searchKey) {
        resumeList.remove(searchKey.intValue());
    }

    @Override
    protected boolean isExist(Integer searchKey) {
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
