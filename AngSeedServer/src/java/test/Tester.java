/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import deploy.DeploymentConfiguration;
import entity.User;
import facades.UserFacade;
import static facades.UserFacade.createUser;
import static facades.UserFacade.getAllUsers;
import javax.persistence.Persistence;
import security.PasswordHash;


/**
 *
 * @author nicolaiharbo
 */
public class Tester {
    
    UserFacade facade = new UserFacade( Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));
    

    public static void main(String[] args) {
        
//        System.out.println(getAllUsers());
//        System.out.println(UserFacade.getUserByUserId("admin"));
        
        createUser("user", "test");
        
        
        
    }
}
    

