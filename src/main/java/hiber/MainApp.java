package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru",new Car(325,"Bmw")));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru",new Car(500,"Nissan")));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru",new Car(5,"Audi")));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru",new Car(8,"Renaut")));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
         System.out.println("1. _____________________________________________");
      }


      System.out.println(userService.getByCar("Nissan", 500));
      System.out.println("2. _____________________________________________");


      try {
         User notFoundUser = userService.getByCar("Mers-Bens", 600);
      } catch (NoResultException e) {
         System.out.println("User not found");
         System.out.println("3. _____________________________________________");
      }

//      List<User> users = userService.listUsers();
//      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//         System.out.println();
//      }

      context.close();
   }
}
