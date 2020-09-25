package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS `pre-project`.`user` (" +
                    "`id` BIGINT NOT NULL AUTO_INCREMENT, " +
                    "`name` VARCHAR(255) NOT NULL, " +
                    "`lastName` VARCHAR(255) NOT NULL, " +
                    "`age` TINYINT(127) NOT NULL, " +
                    "PRIMARY KEY (`id`), UNIQUE INDEX " +
                    "`id_UNIQUE` (`id` ASC) VISIBLE);").executeUpdate();
            transaction.commit();
            System.out.println("База данных успешно создана!");

        } catch (HibernateException exc) {
            System.out.println("Ошибка создания базы данных!");
            exc.getStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS `pre-project`.`user`;").executeUpdate();
            transaction.commit();
            System.out.println("База данных успешно удалена!");

        } catch (HibernateException exc) {
            System.out.println("Ошибка удаления базы данных!");
            exc.getStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.persist(user);
            transaction.commit();
            System.out.println("Пользователь " + name + " успешно добавлен в базу данных!");

        } catch (HibernateException exc) {
            System.out.println("Ошибка добавления пользователья " + name + " в базу данных!");
            exc.getStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User WHERE id = :id").setParameter("id", id).executeUpdate();
            transaction.commit();
            System.out.println("Пользователь " + id + " успешно удален из базы данных!");

        } catch (HibernateException exc) {
            System.out.println("Ошибка удаления пользователя " + id + " из базы данных!");
            exc.getStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listAllUsers = new ArrayList<>();

        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            listAllUsers = session.createQuery("FROM User").list();
            transaction.commit();

        } catch (HibernateException exc) {
            System.out.println("Ошибка получения списка пользователей из базы данных!");
            exc.getStackTrace();
        }
        return listAllUsers;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
            System.out.println("База данных успешно очищена!");

        } catch (HibernateException exc) {
            System.out.println("Ошибка очистки базы данных!");
            exc.getStackTrace();
        }
    }
}
