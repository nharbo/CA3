/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import facades.UserFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author nicolaiharbo
 */
@Path("unknown")
public class unknown {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of unknown
     */
    public unknown() {
    }

    /**
     * Retrieves representation of an instance of rest.unknown
     * @return an instance of java.lang.String
     */
  
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/create/{name}/{password}")
  public String createUser(@PathParam("name") String name, @PathParam("password") String password){
      UserFacade uf = new UserFacade();
      
      uf.createUser(name, password);
      
      return "You succesfully Registered as" + name;
  }


    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
