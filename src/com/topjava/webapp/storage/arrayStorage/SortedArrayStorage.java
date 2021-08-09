package com.topjava.webapp.storage.arrayStorage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    public void insertElement(Resume resume, int index) {
        int inIndex = -index - 1;
        System.arraycopy(storage, inIndex, storage, inIndex + 1, size - inIndex);
        storage[inIndex] = resume;
    }
}
