package models;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Product implements Serializable {

    @XmlElement
    @XmlInverseReference(mappedBy="products")
    private Store store;

    @XmlElement
    private String name;

    public Product(String name, Store store){
        this.name = name;
        this.store = store;
    }

    public Product(){
        this("",new Store());
    }

    public Store getStore(){
        return store;
    }

    public void setStore(Store store){
        if(store == null){
            throw new IllegalArgumentException("Store can't be null");
        }

        this.store = store;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        if(name == null){
            throw new IllegalArgumentException("Name can't be null");
        }

        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (store != null ? !store.equals(product.store) : product.store != null) return false;
        return name != null ? name.equals(product.name) : product.name == null;
    }

    @Override
    public int hashCode() {
        int result = store != null ? store.getName().hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
