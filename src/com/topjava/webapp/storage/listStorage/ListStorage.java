package com.topjava.webapp.storage.listStorage;

import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.AbstractStorage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    protected void updateResume(Resume resume, int index) {
        resumeList.set(index, resume);
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        resumeList.add(resume);
    }

    @Override
    protected Resume getResume(Resume resume, int index) {
        return resumeList.get(index);
    }

    @Override
    protected void deleteResume(Resume resume, int index) {
        resumeList.remove(resume);
    }

    @Override
    protected Resume[] getAllResume() {
        return resumeList.toArray(new Resume[size()]);
    }

    @Override
    public int getIndex(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (uuid.equals(resumeList.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
