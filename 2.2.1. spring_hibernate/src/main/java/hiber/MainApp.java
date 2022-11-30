package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car nissan = new Car("Nissan", 12);
      Car toyota = new Car("Toyota", 52);

      userService.add(new User("Shamil", "Bosayev", "eleven@mail.ru", nissan));
      userService.add(new User("Jordan", "Bellford", "ws@mail.ru", toyota));

      for (User user : userService.listUsers()) {
         System.out.println(user.toString());
      }

      System.out.println(userService.getUserByCar("Toyota", 52));

      try {
         User notFoundUser = userService.getUserByCar("BELAZ", 34);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
