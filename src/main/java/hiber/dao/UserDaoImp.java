package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUser(Long id) {
        User user = sessionFactory.getCurrentSession().get(User.class, id);
        if (user == null) {
            System.out.println("Юзер не существует");
        }
        return user;
    }

    @Override
    public User getUserByCar(String model, int series) {
        User user = null;
        String hqlQuery = "FROM User u WHERE u.car.model = :model AND u.car.series = :series";
        try {
            List<User> userresultList = sessionFactory.getCurrentSession()
                    .createQuery(hqlQuery)
                    .setParameter("model", model)
                    .setParameter("series", series)
                    .getResultList();
            user = userresultList.get(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Юзера с такой машиной нет");
            e.printStackTrace();
        }
        return user;
    }
}
