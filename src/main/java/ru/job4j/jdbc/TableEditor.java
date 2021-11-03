package ru.job4j.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            properties.load(new FileReader("app.properties"));
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("login"),
                    properties.getProperty("password")
            );
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s();",
                    tableName
            );
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName));
        }
    }

    public void dropTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "DROP TABLE IF EXISTS %s;",
                    tableName
            );
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s%n"
                            + "ADD COLUMN %s %s;",
                    tableName, columnName, type
            );
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName));
        }
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "alter table %s%n"
                            + "drop column %s;",
                    tableName, columnName
            );
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName));
        }
    }

    public void renameColumn(
            String tableName, String columnName, String newColumnName
    ) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s%n"
                            + "RENAME %s TO %s;",
                            tableName, columnName, newColumnName
            );
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName));
        }
    }


    public static String getTableScheme(
            Connection connection, String tableName
    ) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        TableEditor te = new TableEditor(new Properties());
        te.initConnection();
        te.createTable("TETable");
        te.addColumn("TETable", "id", "serial primary key");
        te.addColumn("TETable", "name", "varchar(255)");
        te.addColumn("TETable", "fullName", "varchar(355)");
        te.renameColumn("TETable", "fullName", "full_name");
        te.dropColumn("TETable", "full_name");
        te.dropTable("TETable");
    }
}