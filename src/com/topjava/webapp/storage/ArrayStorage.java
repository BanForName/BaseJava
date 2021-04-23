package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private int size;
    private Resume[] storage = new Resume[10000];

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        if (checkResume(uuid)) {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    storage[i] = resume;
                }
            }
        } else {
            System.out.println(uuid + " отсутствует в хранилище.");
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (checkResume(resume.getUuid())) {
            System.out.println(resume.getUuid() + " уже существует.");
        } else if (!checkResume(resume.getUuid()) && size != storage.length){
            storage[size] = resume;
            size++;
        } else {
            System.out.println("Хранилище переполнено.");
        }
    }

    public Resume get(String uuid) {
        if (checkResume(uuid)) {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) return storage[i];
            }
        } else {
            System.out.println(uuid + " отсутствует в хранилище");
        }
        return null;
    }

    public void delete(String uuid) {
        if (checkResume(uuid)) {
            int index = 0;
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    index = i;
                    size--;
                    break;
                }
            }
            if (size - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - index);
        } else {
            System.out.println(uuid + " отсутствует в хранилище");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private boolean checkResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return true;
            }
        }
        return false;
    }
}