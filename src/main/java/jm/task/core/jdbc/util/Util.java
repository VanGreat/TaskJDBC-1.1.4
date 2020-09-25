package jm.task.core.jdbc.util;

import java.sql.*;

import java.util.Properties;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import jm.task.core.jdbc.model.User;


public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/pre-project?serverTimezone=Europe/Moscow";
    private static final String USER = "root";
    private static final String PASSWD = "password";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();
                properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
                properties.setProperty("hibernate.connection.url", URL);
                properties.setProperty("hibernate.connection.username", USER);
                properties.setProperty("hibernate.connection.password", PASSWD);
                properties.setProperty("hibernate.show_sql", "true");

                sessionFactory = new Configuration()
                        .addPackage("jm.task.core.jdbc.model")
                        .addProperties(properties)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
                return sessionFactory;

            } catch (HibernateException exc) {
                exc.getStackTrace();
            }
        }
        return sessionFactory;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWD);
    }
}
