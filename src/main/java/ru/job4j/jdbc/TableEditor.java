package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private static Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, IOException {
        this.properties = properties;
        initConnection();
    }

    /**
     * Метод выполняет подключение к базе данных через DriverManager.
     */
    private void initConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(inputStream);
        }
        connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("login"),
                (properties.getProperty("password")));
    }

    /**
     * Метод выполняет заявление на изменение данных таблицы.
     * @param query строка в которой содержится команда
     */
    public void initStatement(String query) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
    }

    /**
     * Метод создает пустую таблицу без столбцов с указанным именем
     * @param tableName имя таблицы
     */
    public void createTable(String tableName) throws SQLException {
        String sql = String.format(
                "create table if not exists %s();",
                tableName
        );
        initStatement(sql);
    }

    /**
     * Метод удаляет таблицу по указанному имени
     * @param tableName имя таблицы
     */
    public void dropTable(String tableName) throws SQLException {
        String sql = String.format(
                "drop table if exists %s;",
                tableName
        );
        initStatement(sql);
    }

    /**
     * Метод добавляет столбец в таблицу
     * @param tableName имя таблицы
     * @param columnName имя столбца
     * @param type тип
     */
    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "alter table %s add column %s %s;",
                tableName, columnName, type
        );
        initStatement(sql);
    }

    /**
     * Метод удаляет столбец из таблицы
     * @param tableName имя таблицы
     * @param columnName имя столбца
     */
    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format(
                "alter table %s drop column %s;",
                tableName, columnName
        );
        initStatement(sql);
    }

    /**
     * Метод переименовывает столбец в таблице
     * @param tableName имя таблицы
     * @param columnName имя столбца
     * @param newColumnName новое имя столбца
     */
    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format(
                "alter table %s rename column %s to %s;",
                tableName, columnName, newColumnName
        );
        initStatement(sql);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
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
        try (InputStream inputStream = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(inputStream);
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable("demoTable");
            System.out.println(getTableScheme(connection, "demoTable"));
            tableEditor.addColumn("demoTable", "name", "varchar(255)");
            System.out.println(getTableScheme(connection, "demoTable"));
            tableEditor.renameColumn("demoTable", "name", "phone");
            System.out.println(getTableScheme(connection, "demoTable"));
            tableEditor.dropColumn("demoTable", "phone");
            System.out.println(getTableScheme(connection, "demoTable"));
            tableEditor.dropTable("demoTable");
        }
    }
}
