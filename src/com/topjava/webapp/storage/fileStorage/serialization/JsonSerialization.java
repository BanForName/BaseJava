package com.topjava.webapp.storage.fileStorage.serialization;

import com.topjava.webapp.model.Resume;
import com.topjava.webapp.util.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonSerialization implements Serialization {

    @Override
    public void resumeWrite(Resume resume, OutputStream os) throws IOException {
        try (Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            JsonParser.write(resume, writer);
        }
    }

    @Override
    public Resume resumeRead(InputStream is) throws IOException {
        try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return JsonParser.read(reader, Resume.class);
        }
    }
}
