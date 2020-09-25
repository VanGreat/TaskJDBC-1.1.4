package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        // Создание таблицы User(ов)
        userService.createUsersTable();

        //Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления
        // должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        userService.saveUser("Vladimir", "Putin", (byte) 67);
        userService.saveUser("Donald", "Trump", (byte) 74);
        userService.saveUser("Angela", "Merkel", (byte) 66);
        userService.saveUser("Bill", "Gates", (byte) 64);

        // Удаление пользователя id=2
        userService.removeUserById(1);

        //Получение всех User из базы и вывод в консоль
        System.out.println(userService.getAllUsers());

        //Очистка таблицы User(ов)
        userService.cleanUsersTable();

        //Удаление таблицы
        userService.dropUsersTable();
    }
}
