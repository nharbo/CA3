package rest;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import facades.UserFacade;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("demouser")
//@RolesAllowed("User")
public class User {
    private static Gson gson = new Gson();
    private static final JsonParser parser = new JsonParser();
    
    
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getSomething(){
    return "{\"message\" : \"This message was delivered via a REST call accesible by only authenticated USERS\"}"; 
  }
 
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/getcomp/{option}/{searchText}/{ctry}")
  public String getCompany(@PathParam("option") String option, @PathParam("searchText") String searchText, @PathParam("ctry") String ctry) throws MalformedURLException, IOException{
      String urlToUse = "http://cvrapi.dk/api?" + option + "=" + searchText + "& country=" + ctry;  
        
        URL url = new URL(urlToUse);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null; 
        if (scan.hasNext()) {
            jsonStr = scan.nextLine();
        }
        scan.close();
        
      return jsonStr;
  }
  
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/create/{name}/{password}")
  public void createUser(@PathParam("name") String name, @PathParam("password") String password){
      UserFacade uf = new UserFacade();
      
      uf.createUser(name, password);
      
      
  }
}