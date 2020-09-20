package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/pre-project-1.1.3?serverTimezone=Europe/Moscow";
    private static final String USER = "root";
    private static final String PASSWD = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWD);
    }

}
