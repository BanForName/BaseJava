package com.topjava.webapp.storage.fileStorage;

import com.topjava.webapp.exception.StorageException;
import com.topjava.webapp.model.Resume;
import com.topjava.webapp.storage.AbstractStorage;
import com.topjava.webapp.storage.fileStorage.serialization.StreamSerialization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final StreamSerialization serialization;

    public PathStorage(String dir, StreamSerialization serialization) {
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
            serialization.resumeWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("unable to create a file", resume.getUuid(), e);
        }
    }

    @Override
    protected void saveResume(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("unable to create a file", resume.getUuid(), e);
        }
        updateResume(resume, path);
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return serialization.resumeRead(new BufferedInputStream(Files.newInputStream(path)));
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
        return directory.resolve(uuid);
    }

    @Override
    protected List<Resume> allSortedResume() {
        return getFiles().map(this::getResume).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        getFiles().forEach(this::deleteResume);
    }

    @Override
    public int size() {
        return (int) getFiles().count();
    }

    private Stream<Path> getFiles() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Can't read " + directory.getRoot() + " directory", null, e);
        }
    }
}