package com.topjava.webapp.sql;

import com.topjava.webapp.exception.ExistStorageException;
import com.topjava.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T execute(String sqlExpession, SqlExecutor<T> executor) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlExpession)) {
            return executor.execute(preparedStatement);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) throw new ExistStorageException(null);
            throw new StorageException(e);
        }
    }
}