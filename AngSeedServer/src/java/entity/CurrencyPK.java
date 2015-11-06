/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nicolaiharbo
 */
public class CurrencyPK {
    
    private String ccode;
    private Date dato;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.ccode);
        hash = 29 * hash + Objects.hashCode(this.dato);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CurrencyPK other = (CurrencyPK) obj;
        if (!Objects.equals(this.ccode, other.ccode)) {
            return false;
        }
        if (!Objects.equals(this.dato, other.dato)) {
            return false;
        }
        return true;
    }
    
    
    
}
