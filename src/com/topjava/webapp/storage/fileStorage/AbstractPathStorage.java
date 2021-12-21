package com.topjava.webapp.storage.fileStorage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.AbstractStorage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private final Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not directory or is not writable");
        }
    }

    @Override
    protected void updateResume(Resume resume, Path path) {
        try {
            resumeWrite(resume, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("unable to create a file", resume.getUuid(), e);
        }
    }

    @Override
    protected void saveResume(Resume resume, Path path) {
        try {
            Files.createFile(path);
            resumeWrite(resume, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("unable to create a file", resume.getUuid(), e);
        }
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return resumeRead(Files.newInputStream(path));
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
        directory.forEach(file -> {
            try {
                list.add(resumeRead(Files.newInputStream(file)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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

    protected abstract void resumeWrite(Resume resume, OutputStream os) throws IOException;

    protected abstract Resume resumeRead(InputStream is) throws IOException;
}
