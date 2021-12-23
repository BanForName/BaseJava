package com.topjava.webapp.storage.fileStorage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.AbstractStorage;
import com.topjava.webapp.storage.fileStorage.serialization.StreamSerialization;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private final StreamSerialization serialization;

    public FileStorage(File directory, StreamSerialization serialization) {
        Objects.requireNonNull(directory + " must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        this.serialization = serialization;
    }

    @Override
    protected void updateResume(Resume resume, File file) {
        try {
            serialization.resumeWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void saveResume(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
        updateResume(resume, file);
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return serialization.resumeRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) throw new StorageException("File delete error", file.getName());
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
        for (File file : fileList()) {
            deleteResume(file);
        }
    }

    @Override
    public List<Resume> allSortedResume() {
        return Arrays.stream(fileList()).map(this::getResume).collect(Collectors.toList());
    }

    @Override
    public int size() {
        return fileList().length;
    }

    private File[] fileList() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("IO error", null);
        }
        return files;
    }
}
