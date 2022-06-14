package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * Метод выполняет операцию по созданию коллекции <User>.
     * Объекты User будут добавляться с параметрами, которые получим в результате разделения строки из файла dump.
     * @return List<User>
     */
    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines()
                    .forEach(s -> {
                        String[] line = s.split(";", 2);
                        if (line.length != 2 || line[0].isEmpty() || line[1].isEmpty()) {
                            throw new IllegalArgumentException("Формат данных строк файла указан неверно");
                        }
                        users.add(new User(line[0], line[1]));
                    });
        }
        return users;
}

    /**
     *Метод выполняет вставку аргументов в SQL-запрос.
     * @param users список users
     */
    public void save(List<User> users) throws ClassNotFoundException, SQLException {
    Class.forName(cfg.getProperty("jdbc.driver"));
    try (Connection connection = DriverManager.getConnection(cfg.getProperty("jdbc.url"), cfg.getProperty("jdbc.login"),
            cfg.getProperty("jdbc.password"))) {
        for (User user : users) {
            try (PreparedStatement ps = connection.prepareStatement("insert into users(name, email) values (?, ?)")) {
                ps.setString(1, user.name);
                ps.setString(2, user.email);
                ps.execute();
            }
        }
    }
}

private static class User {
      String name;
      String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./data/dump.txt");
        db.save(db.load());
    }
}
