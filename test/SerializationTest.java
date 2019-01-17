import models.Product;
import models.Store;

import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.*;
import java.io.*;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import serialization.JavaObjectSerializer;
import serialization.XmlSerializer;

public class SerializationTest {

    private XmlSerializer xmlSerializer;
    private JavaObjectSerializer javaObjectSerializer;

    @Before
    public void setUp(){
        xmlSerializer = new XmlSerializer();
        javaObjectSerializer = new JavaObjectSerializer();
    }

    @Test
    public void testStoreWithProductsXml() throws JAXBException, UnsupportedEncodingException {
        Store store = createTestingStoreWithProducts();

        String serialized = xmlSerializer.serialize(store);
        Store deserialized = xmlSerializer.deserialize(serialized, Store.class);

        assertThat(deserialized, is(store));
    }

    @Test
    public void testStoreWithProductsJava() throws IOException, ClassNotFoundException {
        Store store = createTestingStoreWithProducts();

        InputStream serialized = javaObjectSerializer.serialize(store);
        Store deserialized = javaObjectSerializer.deserialize(serialized);

        assertThat(deserialized, is(store));
    }

    private static Store createTestingStoreWithProducts(){
        Store store = new Store("store");

        Product firstProduct = new Product("product1", store);
        Product secondProduct = new Product("product2", store);
        Product thirdProduct = new Product("product3", store);
        Product fourthProduct = new Product("product4", store);
        Product fifthProduct = new Product("product5", store);

        store.setProducts(Arrays.asList(firstProduct, secondProduct, thirdProduct, fourthProduct, fifthProduct));

        return store;
    }

}
