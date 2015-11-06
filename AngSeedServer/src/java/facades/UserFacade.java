package facades;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import deploy.DeploymentConfiguration;
import entity.Currency;
import entity.User;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import security.PasswordHash;

public class UserFacade {

    private static Gson gson = new Gson();

    private final Map<String, User> users = new HashMap<>();

    //   static EntityManagerFactory emf;
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA3PU");

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
//        insertUsers();
        System.out.println(getUserByUserId("kay").getUserName());

    }

    public UserFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public static void createUser(String name, String password) {

        try {
            EntityManager em = emf.createEntityManager();

            User newUser = new User(name, password);
            newUser.AddRole("User");

            String hashPwd = PasswordHash.createHash(newUser.getPassword());
            newUser.setPassword(hashPwd);

            em.getTransaction().begin();
            em.persist(newUser);
            em.getTransaction().commit();
            em.close();

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertUsers() throws NoSuchAlgorithmException, InvalidKeySpecException {

        EntityManager em = emf.createEntityManager();
        //Test Users

        String hashPwd = PasswordHash.createHash("test");
        User user = new User("user", hashPwd);
        user.AddRole("User");

        String hashPwd1 = PasswordHash.createHash("test");
        User admin = new User("admin", hashPwd1);
        admin.AddRole("Admin");

        String hashPwd2 = PasswordHash.createHash("test");
        User both = new User("user_admin", hashPwd2);
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

        EntityManager em = emf.createEntityManager();
        if (em.find(User.class, id) == null) {
            User noUser = new User("noUserFound", "noUserFound");
            return noUser;
        } else {
            return em.find(User.class, id);
        }
    }

    public static void deleteUser(String id) {

        EntityManager em = emf.createEntityManager();

//        User user = getUserByUserId(id);
        em.getTransaction().begin();

        em.remove(em.find(User.class, id));
        em.getTransaction().commit();
        em.close();

    }

    /*
     Return the Roles if users could be authenticated, otherwise null
     */
    public List<String> authenticateUser(String userName, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {

        User user = getUserByUserId(userName);
//        String wholeHash = PasswordHash.createHash(password);
        if (PasswordHash.validatePassword(password, user.getPassword())) {
            return user.getRoles();

        } else {
            return null;

        }
    }

    public String getAllUsers() {

        EntityManager em = emf.createEntityManager();
        //Fix så den kun henter user-roles..
        Query query = em.createNativeQuery("SELECT * FROM user", User.class);
//        Query query1 = em.createNamedQuery("Currency.getCurrencys");

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
