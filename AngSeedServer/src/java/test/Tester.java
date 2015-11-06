/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import deploy.DeploymentConfiguration;
import entity.User;
import facades.UserFacade;
import facades.XmlFacade;
import javax.persistence.Persistence;
import security.PasswordHash;


/**
 *
 * @author nicolaiharbo
 */
public class Tester {
    
    static UserFacade facade = new UserFacade( Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));
    static XmlFacade xf = new XmlFacade();

    public static void main(String[] args) {
        
//        System.out.println(facade.getAllUsers());
//        System.out.println(UserFacade.getUserByUserId("admin"));
        System.out.println(xf.getCurrency());
        
//        createUser("user", "test");
        
        
        
    }
}
    

