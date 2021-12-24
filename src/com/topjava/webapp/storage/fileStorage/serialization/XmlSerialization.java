package com.topjava.webapp.storage.fileStorage.serialization;

import com.topjava.webapp.model.*;
import com.topjava.webapp.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlSerialization implements Serialization {
    private final XmlParser xmlParser;

    public XmlSerialization() {
        xmlParser = new XmlParser(
                Resume.class, Organization.class, Link.class, OrganizationSection.class,
                TextSection.class, ListSection.class, Organization.Experience.class);
    }

    @Override
    public void resumeWrite(Resume resume, OutputStream os) throws IOException {
        try (Writer writer = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(resume, writer);
        }
    }

    @Override
    public Resume resumeRead(InputStream is) throws IOException {
        try (Reader reader = new InputStreamReader(is)) {
            return xmlParser.unmarshall(reader);
        }
    }
}
