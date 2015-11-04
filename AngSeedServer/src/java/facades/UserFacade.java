package facades;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserFacade {

    private static Gson gson = new Gson();

    private final Map<String, User> users = new HashMap<>();

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA3PU");
    static EntityManager em = emf.createEntityManager();

//    public static void main(String[] args) {
//        insertUsers();
//    }
    public UserFacade() {

    }

    public void createUser(String name, String password) {

        User newUser = new User(name, password);
        newUser.AddRole("User");

        em.getTransaction().begin();
        em.persist(newUser);
        em.getTransaction().commit();
        em.close();
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

    public static User getUserByUserId(String id) {

        return em.find(User.class, id);

    }

    public static void deleteUser(String id) {

        User user = getUserByUserId(id);

        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
        em.close();

    }

    /*
     Return the Roles if users could be authenticated, otherwise null
     */
    public List<String> authenticateUser(String userName, String password) {
        User user = getUserByUserId(userName);
        return user != null && user.getPassword().equals(password) ? user.getRoles() : null;
    }

    public static String getAllUsers() {
        Query query = em.createNativeQuery("SELECT * FROM user", User.class);

        List<User> list = (List<User>) query.getResultList();
        JsonArray jsonArray = new JsonArray();

        for (User user : list) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("username", user.getUserName());
            jsonArray.add(jsonObject);
        }
        return gson.toJson(jsonArray);

    }

}
