/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import facades.UserFacade;
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
         facades.UserFacade.createUser("deleteTest", "delete");
    }
    
    @After
    public void tearDown() {
        // facades.UserFacade.deleteUser("deleteTest");
    }
    
    @Test
    public void TestFindUserRoleById(){
        assertEquals("Admin", facades.UserFacade.getUserByUserId("admin").getRoles().get(0));
    }
    
    @Test
    public void DeleteUserFromDatabase(){
        assertEquals("deleteTest", facades.UserFacade.getUserByUserId("deleteTest").getUserName());
        facades.UserFacade.deleteUser("deleteTest");
        assertEquals("noUserFound", facades.UserFacade.getUserByUserId("deleteTest").getUserName());
    }
    
}
