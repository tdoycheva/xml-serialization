package models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Wear implements Serializable {

    @XmlElement
    private String model;

    public void setModel(String model){
        this.model = model;
    }

    public String getModel(){
        return model;
    }
}
