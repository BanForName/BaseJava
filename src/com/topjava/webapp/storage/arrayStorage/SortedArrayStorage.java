package com.topjava.webapp.storage.arrayStorage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    @Override
    public void insertElement(Resume resume, int index) {
        int inIndex = -index - 1;
        System.arraycopy(storage, inIndex, storage, inIndex + 1, size - inIndex);
        storage[inIndex] = resume;
    }

    @Override
    protected Integer getKey(String uuid) {
        Resume searchKey = new Resume(uuid, "Tom");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }
}
