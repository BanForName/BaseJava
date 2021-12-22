package com.topjava.webapp.storage.fileStorage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.AbstractStorage;
import com.topjava.webapp.storage.fileStorage.serialization.ObjectSerialization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final ObjectSerialization serialization;

    public PathStorage(String dir, ObjectSerialization serialization) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not directory or is not writable");
        }
        this.serialization = serialization;
    }

    @Override
    protected void updateResume(Resume resume, Path path) {
        try {
            serialization.resumeWrite(resume, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("unable to create a file", resume.getUuid(), e);
        }
    }

    @Override
    protected void saveResume(Resume resume, Path path) {
        try {
            Files.createFile(path);
            serialization.resumeWrite(resume, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("unable to create a file", resume.getUuid(), e);
        }
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return serialization.resumeRead(Files.newInputStream(path));
        } catch (IOException e) {
            throw new StorageException("unable to read the file" + path.getFileName(), null, e);
        }
    }

    @Override
    protected void deleteResume(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new StorageException("can't be deleted " + path.getFileName() + " resume", null, e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected Path getKey(String uuid) {
        return Path.of(directory.toString(), uuid);
    }

    @Override
    protected List<Resume> allSortedResume() {
        List<Resume> list = new ArrayList<>();
        try {
            Files.list(directory).forEach(file -> {
                try {
                    list.add(serialization.resumeRead(Files.newInputStream(file)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::deleteResume);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null, e);
        }
    }

    @Override
    public int size() {
        try {
            return (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("no such file", null, e);
        }
    }
}
