package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTask {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config data = new Config("app.properties");
        data.load();
        Class.forName(data.value("driver"));
        try (Connection connection = DriverManager.getConnection(
                data.value("url"), data.value("login"), data.value("password")
        )) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}