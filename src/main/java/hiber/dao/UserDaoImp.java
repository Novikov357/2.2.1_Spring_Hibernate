package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Random;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

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
    public void dropTables() {
        Query query = sessionFactory.getCurrentSession().createSQLQuery("DROP TABLE IF EXISTS users");
        Query query1 = sessionFactory.getCurrentSession().createSQLQuery("DROP TABLE IF EXISTS cars");
        query.executeUpdate();
        query1.executeUpdate();
    }

    @Override
    public User getUser(Long id) {
        User user = sessionFactory.getCurrentSession().byId(User.class).load(id);
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
            List<User> result = sessionFactory.getCurrentSession().createQuery(hqlQuery).setParameter("model", model)
                    .setParameter("series", series).getResultList();
            user = result.get(new Random().nextInt(result.size()));
            return user;
        } catch (IllegalArgumentException e) {
            System.out.println("Юзера с такой машиной нет");
            e.printStackTrace();
        }
        return user;
    }
}
