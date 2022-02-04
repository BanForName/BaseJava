package com.topjava.webapp.storage.sqlStorage;

import com.topjava.webapp.exception.NotExistStorageException;
import com.topjava.webapp.model.ContactType;
import com.topjava.webapp.model.Resume;
import com.topjava.webapp.sql.SqlHelper;
import com.topjava.webapp.storage.Storage;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() == 0) throw new NotExistStorageException(r.getUuid());
                ps.executeUpdate();
            }
            deleteContacts(r.getUuid());
            saveContact(connection, r);
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
            saveContact(connection, r);
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resume r " +
                "LEFT JOIN contact c " +
                "ON r.uuid = c.resume_uuid " +
                " WHERE r.uuid = ?", ps -> {
            ps.setString(1, uuid);
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) throw new NotExistStorageException(uuid);
            Resume resume = new Resume(uuid, resultSet.getString("full_name"));
            do {
                ContactType contactType = ContactType.valueOf(resultSet.getString("type"));
                String value = resultSet.getString("value");
                resume.setContact(contactType, value);
            } while (resultSet.next());
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
        return sqlHelper.execute("SELECT * FROM resume r" +
                " LEFT JOIN contact c" +
                " ON r.uuid = c.resume_uuid" +
                " ORDER BY full_name, uuid", ps -> {
            ResultSet resultSet = ps.executeQuery();
            Map<String, Resume> map = new LinkedHashMap<>();
            while (resultSet.next()) {
                String uuid = resultSet.getString("uuid");
                String fullName = resultSet.getString("full_name");
                String value = resultSet.getString("value");
                ContactType contactType = ContactType.valueOf(resultSet.getString("type"));
                Resume resume = map.get(uuid);
                if (resume == null) {
                    resume = new Resume(uuid, fullName);
                    map.put(uuid, resume);
                }
                resume.setContact(contactType, value);
            }
            return new ArrayList<>(map.values());
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

    private void deleteContacts(String uuid) {
        sqlHelper.execute("DELETE FROM contact WHERE resume_uuid = ?", ps -> {
            ps.setString(1, uuid);
            return ps.execute();
        });
    }

    private void saveContact(Connection connection, Resume r) throws SQLException {
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
}