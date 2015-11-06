/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import deploy.DeploymentConfiguration;
import facades.UserFacade;
import facades.XmlFacade;
import javax.persistence.Persistence;
import static org.hamcrest.Matchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nicolaiharbo
 */
public class FacadeTest {
    
    UserFacade facade = new UserFacade(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));
    XmlFacade xfacade = new XmlFacade();
    
    //Using a test-database containing 3 users:
    // - username -  /  - password - / - role -
    //    user       /     test      /   user
    //    admin      /     test      /   admin
    //    user_admin /     test      /   user + admin
    
    public FacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       
    }
    
    @AfterClass
    public static void tearDownClass() {
       
    }
    
    @Before
    public void setUp() {
         facade.createUser("deleteTest", "delete");
    }
    
    @After
    public void tearDown() {
        if (facade.getUserByUserId("deleteTest").getUserName().equals("deleteTest")) {
            facade.deleteUser("deleteTest");
        }
        
    }
    
    @Test
    public void TestFindUserRoleById(){
        assertEquals("Admin", facade.getUserByUserId("admin").getRoles().get(0));
    }
    
    @Test
    public void DeleteUserFromDatabase(){
        assertEquals("deleteTest", facade.getUserByUserId("deleteTest").getUserName());
        facade.deleteUser("deleteTest");
        assertEquals("noUserFound", facade.getUserByUserId("deleteTest").getUserName());
    }
    
    @Test
    public void GetAllUsersFromDB(){
        String result = "[{\"username\":\"admin\"},{\"username\":\"deleteTest\"},{\"username\":\"user\"},{\"username\":\"user_admin\"}]";
        assertEquals(result, facade.getAllUsers());
    
    }
    
    @Test
    public void GetCurrencyFromDB(){
        // indsæt hvad du vil se om findes i Json Arrayet
        assertTrue(xfacade.getCurrency().contains("AUD"));
        assertTrue(xfacade.getCurrency().contains("Australian dollars"));
        //Currency kan ændre sig fra dag til dag, check derfor DB for den nyeste kurs til testen!
        //assertTrue(xfacade.getCurrency().contains("490.66"));
        
    }
}
