package rest;

import facades.UserFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("demoadmin")
//@RolesAllowed("Admin")
public class Admin {
   

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSomething() {
        String now = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss").format(new Date());
        return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated ADMINS\",\n"
                + "\"serverTime\": \"" + now + "\"}";
    }

    @GET
    @Produces("application/json")
    @Path("getAllUsers")
    public String getUsers() {
       
        
        return UserFacade.getAllUsers();
    }
    
    @POST
    @Produces("application/json")
    @Path("user/{id}")
    public void deleteUser(@PathParam("id") String id) {
       
        UserFacade.deleteUser(id);
       
    }

}
