package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        if (getIndex(uuid) != -1) {
            System.out.println(uuid + " уже существует.");
        } else if (size != STORAGE_LIMIT) {
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Хранилище переполнено.");
        }
    }

    public int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}