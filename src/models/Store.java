package models;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Store implements Serializable{

    @XmlElement
    private String name;

    @XmlElement(name="product")
    @XmlInverseReference(mappedBy="store")
    @XmlElementWrapper(name="products")
    private List<Product > products;

    public Store(String name, List<Product> products){
        this.name = name;
        this.products = products;
    }

    public Store(String name){
        this(name, new ArrayList<Product>());
    }

    public Store(){
        this("");
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

    public List<Product> getProducts(){
        return products;
    }

    public void setProducts(List<Product> products){
        if(products == null){
            throw new IllegalArgumentException("Products can't be null");
        }

        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Store store = (Store) o;

        if (name != null ? !name.equals(store.name) : store.name != null) return false;
        if (products != null) {
            if(store.products != null && products.size() != store.products.size()){
                return false;
            }

            for(Product product: products){
                boolean hasProduct = false;

                if(store.products!=null){
                    for(Product storeProduct: store.products){
                        if(product != null && storeProduct!=null && product.getName().equals(storeProduct.getName())){
                            hasProduct = true;
                            break;
                        }
                    }
                }

                if(!hasProduct){
                    return false;
                }
            }

            return true;
        } else {
            return store.products == null;
        }
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (products != null ? products.hashCode() : 0);
        return result;
    }
}
