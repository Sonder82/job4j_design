package ru.job4j.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PrepareStatementDemo {

    private static Connection connection;

    public PrepareStatementDemo() throws ClassNotFoundException, SQLException {
        initConnection();
    }

    public void initConnection() throws ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        Class.forName(properties.getProperty("driver_class"));
        connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("login"),
                properties.getProperty("password"));
    }

    /**
     * Метод выполняет операцию вставки данных в таблицу.
     * @param city объект
     */
    public void insert(City city) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("insert into cities(name, population) values (?, ?)")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
        }
    }

    /**
     * Метод выполняет изменение данных в столбцах таблицы.
     * @param city объект
     * @return "false" - если изменение не выполнено. "true" - если изменение выполнено.
     */
    public boolean update(City city) throws SQLException {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement("update cities set name = ?, population = ? where id = ?")) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getId());
            statement.setInt(3, city.getId());
            result = statement.executeUpdate() > 0;
        }
        return result;
    }

    /**
     * Метод выполняет удаление данных из столбца таблицы
     * @param id номер id
     * @return "false" - если удаление не выполнено. "true" - если удаление выполнено.
     */
    public boolean delete(int id) throws SQLException {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement("delete from cities where id = ?")) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        }
        return result;
    }

    /**
     * Метод позволяет получить все элементы таблицы.
     * @return возвращает список элементов
     */
    public List<City> findAll() throws SQLException {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select * from cities")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")));
                }
            }
        }
        return cities;
    }

    /**
     * Метод позволяет получить id вставленного элемента
     * @param city объект
     * @return объект
     */
    public City insertAndGetId(City city) throws SQLException {
        try (PreparedStatement statement =
                     connection.prepareStatement("insert into cities(name, population) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        }
        return city;
    }
}
