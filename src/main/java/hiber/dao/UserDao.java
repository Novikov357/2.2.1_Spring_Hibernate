package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();

   User getUser(Long id);

   User getUserByCar (String model, int series);

}
