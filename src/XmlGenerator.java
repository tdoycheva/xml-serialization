import models.Product;
import models.Store;
import serialization.XmlSerializer;

import javax.xml.bind.JAXBException;
import java.util.Arrays;

public class XmlGenerator {
    public static void main(String[] args) {
        Store store = new Store();
        store.setName("Amazon");

        Product product1 = new Product();
        product1.setName("printer");
        product1.setStore(store);
        Product product2 = new Product();
        product2.setName("laptop");
        product2.setStore(store);
        store.setProducts(Arrays.asList(product1, product2));

        XmlSerializer serializer = new XmlSerializer();
        try {
            String xmlText = serializer.serialize(store);
            System.out.println(xmlText);
        } catch ( JAXBException e) {
            System.out.println(e.getMessage());
        }

    }
}
