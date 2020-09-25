package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS `pre-project`.`user` (" +
                            "`id` BIGINT NOT NULL AUTO_INCREMENT, " +
                            "`name` VARCHAR(255) NOT NULL, " +
                            "`lastName` VARCHAR(255) NOT NULL, " +
                            "`age` TINYINT(127) NOT NULL,  " +
                            "PRIMARY KEY (`id`), " +
                            "UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);");
            ps.executeUpdate();
            System.out.println("База данных [user] создана!");

        } catch (SQLException exc) {
            System.out.println("Ошибка создания базы данных [user]");
            exc.getStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "DROP TABLE IF EXISTS `pre-project`.`user`;");
            ps.executeUpdate();
            System.out.println("База данных [user] удалена!");

        } catch (SQLException exc) {
            System.out.println("Ошибка удаления базы данных [user]");
            exc.getStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO `pre-project`.`user` (`name`, `lastName`, `age`) VALUES (?, ?, ?);");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.println("Пользователь [name = " + name + "] добавлен в базу данных [user]!");

        } catch (SQLException exc) {
            System.out.println("Ошибка сохранения пользователья в базе данных [user]");
            exc.getStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM `pre-project`.`user` WHERE (`id` = ?);");
            ps.setLong(1, id);
            ps.executeUpdate();

            System.out.println("Пользователь [id = " + id + "] удален из базы данных [user]!");

        } catch (SQLException exc) {
            System.out.println("Ошибка удаления пользователя из базы данных [user]");
            exc.getStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> listAllUsers = new ArrayList<>();

        try (Connection connection = Util.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `pre-project`.`user`;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge(rs.getByte(4));
                listAllUsers.add(user);
            }

        } catch (SQLException exc) {
            System.out.println("Ошибка получения списка всех пользователей из базы данных [user]");
            exc.getStackTrace();
        }
        return listAllUsers;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("TRUNCATE `pre-project`.`user`;");
            ps.executeUpdate();
            System.out.println("База данных [user] очищена!");

        } catch (SQLException exc) {
            System.out.println("Ошибка очистки базы данных [user]");
            exc.getStackTrace();
        }
    }
}
