package facades;

import deploy.DeploymentConfiguration;
import entity.Currency;
import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class XmlReaderDemo extends DefaultHandler implements Runnable {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Document (Sax-event)");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End Document (Sax-event)");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
          EntityManager em = emf.createEntityManager();
        System.out.print("Element: " + localName + ": ");
        for (int i = 0; i < attributes.getLength(); i++) {
           // System.out.print("[Atribute: NAME: " + attributes.getLocalName(i) + " VALUE: " + attributes.getValue(i) + "] ");
          // System.out.println(attributes.getQName(i) + attributes.getValue(i));
            if (attributes.getQName(i).equals("code") && attributes.getQName(i+1).equals("desc") && attributes.getQName(i+2).equals("rate")) {
               
               String ccode = attributes.getValue(i);
               String cdesc = attributes.getValue(i+1);
               String rate = attributes.getValue(i+2);
               Date newdate = new Date();
               java.sql.Date sqlDate = new java.sql.Date(newdate.getTime());
                       
                if (rate.equals("-")) {
                    float realRate = 0;
                    Currency cur = new Currency(ccode, cdesc, realRate, sqlDate);
                     //  System.out.println(cur.getCode() + ": " + cur.getDesc()+ ": " + cur.getRate());
                     em.getTransaction().begin();
                     em.persist(cur);
                     em.getTransaction().commit();
                }else{
                     //   int realRate = Integer.parseInt(rate);
                    float realRate = Float.parseFloat(rate);
                    Currency cur = new Currency(ccode, cdesc, realRate, sqlDate);
                    // System.out.println(cur.getCode() + ": " + cur.getDesc()+ ": " + cur.getRate());
                      em.getTransaction().begin();
                     em.persist(cur);
                    em.getTransaction().commit();
                }
         
                
              
               
            }
        }
         
        em.close();
        System.out.println("");
        
    }
     public static void insertCurrency(){
      try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(new XmlReaderDemo());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
            
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
     
     }
//    public static void main(String[] argv) {
//       insertCurrency();
//    }

    @Override
    public void run() {
        insertCurrency();
    }
}
