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
    private Resume resume, resume2, resume3;

    private static final String UUID_1 = "UUID_1";
    private static final String UUID_2 = "UUID_2";
    private static final String UUID_3 = "UUID_3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        resume = new Resume("UUID_1");
        storage.update(resume);
        Assert.assertSame(resume, storage.get(UUID_1));
    }

    @Test
    public void save() {
        resume = new Resume("UUID_4");
        storage.save(resume);
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        resume = new Resume(UUID_1);
        storage.save(resume);
    }

    @Test(expected = StorageException.class)
    public void storageFilled() {
        try {
            for (int i = 4; i <= 10000; i++) {
                resume = new Resume("UUID_" + i);
                storage.save(resume);
            }
        } catch (StorageException e) {
            Assert.fail("Переполнение произошло раньше времени");
        }
        storage.save(new Resume("UUID_N"));
    }

    @Test
    public void get() {
        resume = new Resume(UUID_1);
        resume2 = new Resume(UUID_2);
        resume3 = new Resume(UUID_3);
        Assert.assertEquals(resume, storage.get("UUID_1"));
        Assert.assertEquals(resume2, storage.get("UUID_2"));
        Assert.assertEquals(resume3, storage.get("UUID_3"));
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
        Resume[] allResume = storage.getAll();
        Assert.assertSame(storage.get(UUID_1), allResume[0]);
        Assert.assertSame(storage.get(UUID_2), allResume[1]);
        Assert.assertSame(storage.get(UUID_3), allResume[2]);

    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}