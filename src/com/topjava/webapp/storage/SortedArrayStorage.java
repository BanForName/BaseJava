package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        int inIndex = -index - 1;
        System.arraycopy(storage, inIndex, storage, inIndex + 1, size - inIndex);
        storage[inIndex] = resume;
    }
}
