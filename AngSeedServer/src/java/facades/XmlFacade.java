/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.Currency;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Florent
 */
public class XmlFacade {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("CA3PU");
    private static Gson gson = new Gson();
    
    
    public XmlFacade(){}
    
    public String getCurrency(){
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createNativeQuery("SELECT * FROM currency", Currency.class);
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
