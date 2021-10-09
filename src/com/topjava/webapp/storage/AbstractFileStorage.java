package com.topjava.webapp.storage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File directory;

    public AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory + " must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected void updateResume(Resume resume, File file) {
        try {
            resumeWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void saveResume(Resume resume, File file) {
        try {
            file.createNewFile();
            resumeWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume getResume(File file) {
        Resume resume;
        try {
            resume = resumeRead(file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
        return resume;
    }

    @Override
    protected void deleteResume(File file) {
        file.delete();
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected File getKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    public void clear() {
        File[] folder = directory.listFiles();
        for (File entry : Objects.requireNonNull(folder)) {
            entry.delete();
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new LinkedList<>();
        for (File files : Objects.requireNonNull(directory.listFiles())) {
            try {
                list.add(resumeRead(files));
            } catch (IOException e) {
                throw new StorageException("IO error", files.getName(), e);
            }
        }
        return list;
    }

    @Override
    public int size() {
        return Objects.requireNonNull(directory.listFiles()).length;
    }

    protected abstract void resumeWrite(Resume resume, File file) throws IOException;

    protected abstract Resume resumeRead(File file) throws IOException;
}
