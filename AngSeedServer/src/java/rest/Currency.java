/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import deploy.DeploymentConfiguration;
import facades.XmlFacade;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Florent
 */
@Path("Currency")
public class Currency {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Currency
     */
    public Currency() {
    }

    /**
     * Retrieves representation of an instance of rest.Currency
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCurrencyAsJson() {
        XmlFacade xf = new XmlFacade();
        
        return xf.getCurrency();
    }

   
}
