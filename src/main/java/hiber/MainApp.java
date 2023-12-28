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

        UserService userService = context.getBean(UserService.class);


        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("model1", 1)));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("model1", 1)));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("model3", 3)));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("model4", 4)));

        User user1 = userService.getUser(1L);
        User user2 = userService.getUser(2L);
        User user3 = userService.getUser(3L);
        User user4 = userService.getUser(4L);
        System.out.println("\n" + user1 + "\n");

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }

        User userByCar = userService.getUserByCar("model1", 1);
        System.out.println(userByCar);

//        userService.dropTables();
        context.close();
    }
}
