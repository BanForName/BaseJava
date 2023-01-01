package com.topjava.webapp.storage.sqlStorage;

import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.AbstractSection;
import com.topjava.webapp.model.ContactType;
import com.topjava.webapp.model.Resume;
import com.topjava.webapp.model.SectionType;
import com.topjava.webapp.sql.SqlHelper;
import com.topjava.webapp.storage.Storage;
import com.topjava.webapp.util.JsonParser;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(connection -> {
            String uuid = r.getUuid();
            try (PreparedStatement ps = connection.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, uuid);
                if (ps.executeUpdate() != 1) throw new NotExistStorageException(uuid);
                ps.executeUpdate();
            }
            deleteContacts(connection, uuid);
            saveContacts(connection, r);
            deleteSections(connection, uuid);
            saveSections(connection, r);
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?, ?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
            }
            saveContacts(connection, r);
            saveSections(connection, r);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(connection -> {
            Resume resume = null;
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM resume WHERE uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet resultSet = ps.executeQuery();
                if (!resultSet.next()) throw new NotExistStorageException(uuid);
                resume = new Resume(uuid, resultSet.getString("full_name"));
            }

            try (PreparedStatement ps = connection.prepareStatement("SELECT *FROM contact WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContact(resume, rs);
                }
            }

            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM section WHERE resume_uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(resume, rs);
                }
            }
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) throw new NotExistStorageException(uuid);
            return ps.execute();
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(connection -> {
            Map<String, Resume> resumeMap = new LinkedHashMap<>();
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM resume r ORDER BY full_name, uuid")) {
                ResultSet resultSet = ps.executeQuery();
                while (resultSet.next()) {
                    String uuid = resultSet.getString("uuid");
                    String fullName = resultSet.getString("full_name");
                    resumeMap.put(uuid, new Resume(uuid, fullName));
                }
            }

            getContacts(connection, resumeMap);
            getSections(connection, resumeMap);
            return new ArrayList<>(resumeMap.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", ps -> {
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        });
    }

    private void deleteContacts(Connection connection, String uuid) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {
            ps.setString(1, uuid);
            ps.execute();
        }
    }

    private void saveContacts(Connection connection, Resume r) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void getContacts(Connection connection, Map<String, Resume> resumeMap) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * from contact")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Resume resume = resumeMap.get(resultSet.getString("resume_uuid"));
                addContact(resume, resultSet);
            }
        }
    }

    private void addContact(Resume resume, ResultSet resultSet) throws SQLException {
        String value = resultSet.getString("value");
        if (value != null) resume.setContact(ContactType.valueOf(resultSet.getString("type")), value);
    }

    private void deleteSections(Connection connection, String uuid) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM section WHERE resume_uuid = ?")) {
            ps.setString(1, uuid);
            ps.execute();
        }
    }

    private void saveSections(Connection connection, Resume resume) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO section(type, value, resume_uuid) VALUES (?, ?, ?)")) {
            for (Map.Entry<SectionType, AbstractSection> e : resume.getSections().entrySet()) {
                ps.setString(1, e.getKey().name());
                ps.setString(2, JsonParser.write(e.getValue(), AbstractSection.class));
                ps.setString(3, resume.getUuid());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void getSections(Connection connection, Map<String, Resume> resumeMap) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * from section")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Resume resume = resumeMap.get(resultSet.getString("resume_uuid"));
                addSection(resume, resultSet);
            }
        }
    }

    private void addSection(Resume resume, ResultSet resultSet) throws SQLException {
        String value = resultSet.getString("value");
        if (value != null) {
            SectionType type = SectionType.valueOf(resultSet.getString("type"));
            resume.setSection(type, JsonParser.read(value, AbstractSection.class));
        }
    }
}