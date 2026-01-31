package com.pourist.swingy.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:data/heroes.db";
    private static Connection connection;

    private DatabaseManager() {

    }

    public static void init() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            createTablesIfNotExist();
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to initialize database", e);
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            throw new IllegalStateException("Database not initialized");
        }
        return connection;
    }

    private static void createTablesIfNotExist() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS heroes (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL UNIQUE,
                hero_class TEXT NOT NULL,
                level INTEGER NOT NULL,
                experience INTEGER NOT NULL,

                weapon_name  TEXT,
                weapon_bonus INTEGER,

                armor_name   TEXT,
                armor_bonus  INTEGER,

                helm_name    TEXT,
                helm_bonus   INTEGER
            );
        """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }
}