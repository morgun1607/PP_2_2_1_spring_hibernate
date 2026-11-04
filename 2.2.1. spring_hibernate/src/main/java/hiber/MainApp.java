package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        try {
            UserService userService = context.getBean(UserService.class);

            // Создаем пользователей с автомобилями
            User user1 = new User("Ivan", "Ivanov", "ivan@mail.ru",
                    new Car("BMW", 5));

            User user2 = new User("Petr", "Petrov", "petr@mail.ru",
                    new Car("Mercedes", 3));

            User user3 = new User("Alex", "Sidorov", "alex@mail.ru",
                    new Car("Audi", 6));

            User user4 = new User("Artem", "Smirnov", "artem@mail.ru",
                    new Car("Toyota", 7));

            // Сохраняем пользователей и автомобили
            userService.add(user1);
            userService.add(user2);
            userService.add(user3);
            userService.add(user4);

            // Получаем пользователей из базы данных
            System.out.println("\n=== ALL USERS ===");

            List<User> users = userService.listUsers();

            for (User user : users) {
                System.out.println(user);
            }

            // Ищем владельца BMW 5 серии
            System.out.println("\n=== SEARCH USER BY CAR ===");

            User foundUser = userService.getUserByCar("BMW", 5);

            if (foundUser != null) {
                System.out.println("Car owner found:");
                System.out.println(foundUser);
            } else {
                System.out.println("User not found");
            }

        } finally {
            context.close();
        }
    }
}