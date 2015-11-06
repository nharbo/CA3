/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import deploy.DeploymentConfiguration;
import entity.Currency;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Florent
 */
public class XmlFacade {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
    //static EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA3PU");
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public XmlFacade() {
    }

    public String getCurrency() {
        EntityManager em = emf.createEntityManager();

        //Returning todays date and hours + minutes
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        int hours = now.getHours();
        float min = now.getMinutes();
        float exact = hours + (min/100);
        String strDate = dateformat.format(now);
        System.out.println(exact);
        System.out.println("today: " + strDate);
        
        //Returning yesterdays date
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE, -1);
        String DateYesterday = dateformat.format(yesterday.getTime());
        System.out.println("yesterday: " + DateYesterday);
        
        Query query;
        
        if(exact > 14.15){
            query = em.createNativeQuery("SELECT * FROM currency WHERE DATO = " + '"' + strDate + '"', Currency.class);
        } else {
            query = em.createNativeQuery("SELECT * FROM currency WHERE DATO = " + '"' + DateYesterday + '"', Currency.class);
        }    

        JsonArray jal = new JsonArray();
        List<Currency> datalist = query.getResultList();

        for (Currency cur : datalist) {
            JsonObject jobj = new JsonObject();
            jobj.addProperty("code", cur.getCode());
            jobj.addProperty("desc", cur.getDesc());
            jobj.addProperty("rate", cur.getRate());
            jobj.addProperty("date", gson.toJson(cur.getDate()));
            jal.add(jobj);
        }
        return gson.toJson(jal);
    }
}
