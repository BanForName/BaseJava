package com.topjava.webapp.storage.fileStorage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.AbstractStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

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
            resumeWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void saveResume(Resume resume, File file) {
        try {
            file.createNewFile();
            resumeWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return resumeRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) throw  new StorageException("File delete error", file.getName());
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
    public List<Resume> allSortedResume() {
        List<Resume> list = new ArrayList<>();
        for (File files : Objects.requireNonNull(directory.listFiles())) {
            try {
                list.add(resumeRead(new BufferedInputStream(new FileInputStream(files))));
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

    protected abstract void resumeWrite(Resume resume, OutputStream os) throws IOException;

    protected abstract Resume resumeRead(InputStream is) throws IOException;
}
