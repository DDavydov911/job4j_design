package ru.job4j.jdbc;

import java.io.FileReader;
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
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("login"),
                    properties.getProperty("password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        execute(String.format(
                "CREATE TABLE IF NOT EXISTS %s();", tableName
        ));
    }

    public void dropTable(String tableName) {
        execute(String.format(
                "DROP TABLE IF EXISTS %s;", tableName
        ));
    }

    public void addColumn(String tableName, String columnName, String type) {
        execute(String.format(
                "ALTER TABLE %s ADD COLUMN %s %s;",
                tableName, columnName, type
        ));
    }

    public void dropColumn(String tableName, String columnName) {
        execute(String.format(
                "ALTER TABLE %s DROP COLUMN %s;",
                tableName, columnName
        ));
    }

    public void renameColumn(
            String tableName, String columnName, String newColumnName
    ) {
        execute(String.format(
                "ALTER TABLE %s RENAME %s TO %s;",
                tableName, columnName, newColumnName
        ));
    }

    private void execute(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
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
        Properties properties = new Properties();
        try (FileReader fr = new FileReader("app.properties")) {
            properties.load(fr);
        }
        TableEditor te = new TableEditor(properties);
        te.initConnection();
        te.createTable("TETable");
        System.out.println(getTableScheme(te.connection, "TETable"));
        te.addColumn("TETable", "id", "serial primary key");
        System.out.println(getTableScheme(te.connection, "TETable"));
        te.addColumn("TETable", "name", "varchar(255)");
        System.out.println(getTableScheme(te.connection, "TETable"));
        te.addColumn("TETable", "fullName", "varchar(355)");
        System.out.println(getTableScheme(te.connection, "TETable"));
        te.renameColumn("TETable", "fullName", "full_name");
        System.out.println(getTableScheme(te.connection, "TETable"));
        te.dropColumn("TETable", "full_name");
        System.out.println(getTableScheme(te.connection, "TETable"));
        te.dropTable("TETable");
        te.close();
    }
}