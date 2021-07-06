package com.topjava.webapp.storage;

import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "UUID_1";
    private Resume resume1 = new Resume(UUID_1);

    private static final String UUID_2 = "UUID_2";
    private final Resume resume2 = new Resume(UUID_2);

    private static final String UUID_3 = "UUID_3";
    private final Resume resume3 = new Resume(UUID_3);

    private static final String UUID_4 = "UUID_4";
    private final Resume resume4 = new Resume(UUID_4);

    private final Resume[] resumes = new Resume[3];

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(resume1);
        Assert.assertSame(resume1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(resume4);
    }

    @Test
    public void save() {
        storage.save(resume4);
        storage.get(UUID_4);
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        resume1 = new Resume(UUID_1);
        storage.save(resume1);
    }

    @Test(expected = StorageException.class)
    public void storageFilled() {
        try {
            for (int i = 4; i <= 10000; i++) {
                storage.save(new Resume("UUID_" + i));
            }
        } catch (StorageException e) {
            Assert.fail("Переполнение произошло раньше времени");
        }
        storage.save(new Resume("UUID_N"));
    }

    @Test
    public void get() {
        resume1 = new Resume(UUID_1);
        Assert.assertEquals(resume1, storage.get("UUID_1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        storage.get(UUID_1);
        Assert.assertEquals(2, storage.size());
    }

    @Test
    public void getAll() {
        resumes[0] = resume1;
        resumes[1] = resume2;
        resumes[2] = resume3;
        Resume[] allResume = storage.getAll();
        Assert.assertArrayEquals(resumes, allResume);
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}