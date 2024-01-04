package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao USERDAO;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.USERDAO = userDao;
    }

    @Transactional
    @Override
    public void add(User user) {
        USERDAO.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return USERDAO.listUsers();
    }

    @Transactional
    @Override
    public User getUser(Long id) {
        return USERDAO.getUser(id);
    }

    @Transactional
    @Override
    public User getUserByCar(String model, int series) {
        return USERDAO.getUserByCar(model, series);
    }

}
