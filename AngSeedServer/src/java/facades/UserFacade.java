package facades;

import entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserFacade {

    private final Map<String, User> users = new HashMap<>();
    
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA3PU");
    static EntityManager em = emf.createEntityManager();

    
    public static void main(String[] args) {
        insertUsers();
    }
    public static void insertUsers() {
        //Test Users

        User user = new User("user", "test");
        user.AddRole("User");

        User admin = new User("admin", "test");
        admin.AddRole("Admin");

        User both = new User("user_admin", "test");
        both.AddRole("User");
        both.AddRole("Admin");

        em.getTransaction().begin();
        em.persist(user);
        em.persist(admin);
        em.persist(both);
        em.getTransaction().commit();
        em.close();

    }

    public UserFacade() {

    }

    public User getUserByUserId(String id) {
        
        return em.find(User.class, id);
        
    }
    /*
     Return the Roles if users could be authenticated, otherwise null
     */

    public List<String> authenticateUser(String userName, String password) {
        User user = getUserByUserId(userName);
        return user != null && user.getPassword().equals(password) ? user.getRoles() : null;
    }

}