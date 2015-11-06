package entity;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


//@NamedQueries({
//    @NamedQuery(name = "Currency.getCurrencys", query = "SELECT c FROM currency c ORDERBY c.dato")})
@Entity
//Nedenstående gør det muligt at have 2 PK's
@IdClass(CurrencyPK.class)
@Table (name = "currency")
public class Currency implements Serializable {
    
    

    @Id
    private String ccode;
    private String cdesc;
    private float rate;
    
    @Id
    @Temporal(TemporalType.DATE)
    private Date dato;

    public Currency() {

    }

    public Currency(String code, String desc, float rate, Date dato) {
        this.ccode = code;
        this.cdesc = desc;
        this.rate = rate;
        this.dato = dato;
    }

    public String getDesc() {
        return cdesc;
    }

    public void setDesc(String desc) {
        this.cdesc = desc;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getCode() {
        return ccode;
    }

    public void setCode(String code) {
        this.ccode = code;
    }

    public Date getDate() {
        return dato;
    }

    public void setDate(Date dato) {
        this.dato = dato;
    }

    
    
    
    
}
