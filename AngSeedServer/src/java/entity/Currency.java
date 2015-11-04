package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "currency")
public class Currency implements Serializable {

    @Id
    private String ccode;
    private String cdesc;
    private float rate;

    public Currency() {

    }

    public Currency(String code, String desc, float rate) {
        this.ccode = code;
        this.cdesc = desc;
        this.rate = rate;
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
}
