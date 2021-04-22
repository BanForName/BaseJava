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
        if (checkResume(resume.getUuid())) {
            int index = getIndex(resume.getUuid());
            if (resume.getUuid().equals(storage[index])) {
                storage[index] = resume;
            }
        } else {
            System.out.println(resume.getUuid() + " отсутствует в хранилище");
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (checkResume(r.getUuid())) {
            System.out.println(r.getUuid() + " уже существует.");
        } else if (!checkResume(r.getUuid()) && size != storage.length){
            storage[size] = r;
            size++;
        } else {
            System.out.println("Хранилище переполнено.");
        }
    }

    public Resume get(String uuid) {
        if (checkResume(uuid)) {
            int index = getIndex(uuid);
            if (storage[index].getUuid().equals(uuid)) return storage[index];
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

            for (int i = index; i < size; i++) {
                storage[i] = storage[i + 1];
            }
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

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}