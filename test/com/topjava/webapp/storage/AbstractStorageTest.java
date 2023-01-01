package com.topjava.webapp.storage;

import com.topjava.webapp.Config;
import com.topjava.webapp.ResumeTestData;
import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.ContactType;
import com.topjava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    protected final Storage storage;

    private static final String UUID_1 = UUID.randomUUID().toString();
    private final Resume resume1 = ResumeTestData.createResume(UUID_1, "Bilbo beggins");

    private static final String UUID_2 = UUID.randomUUID().toString();
    private final Resume resume2 = ResumeTestData.createResume(UUID_2, "John Catcher");

    private static final String UUID_3 = UUID.randomUUID().toString();
    private final Resume resume3 = ResumeTestData.createResume(UUID_3, "Tom Carter");

    private static final String UUID_4 = UUID.randomUUID().toString();
    private final Resume resume4 = ResumeTestData.createResume(UUID_4, "Robert Wield");

    public AbstractStorageTest(Storage storage) {
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
        Resume resume5 = new Resume(UUID_1, "TOMMY");
        resume5.setContact(ContactType.PHONE, "7513146");
        resume5.setContact(ContactType.EMAIL, "tommmy@mail.com");
        resume5.setContact(ContactType.GITHUB, "tommmy");
        storage.update(resume5);
        Assert.assertEquals(resume5, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(resume4);
    }

    @Test
    public void save() {
        storage.save(resume4);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(resume4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume1);
    }

    @Test
    public void get() {
        Assert.assertEquals(resume1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void getAll() {
        List<Resume> allResume = storage.getAllSorted();
        Assert.assertEquals(3, allResume.size());
        Assert.assertEquals(allResume, Arrays.asList(resume1, resume2, resume3));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        storage.get(UUID_1);
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(resume4.getUuid());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }
}