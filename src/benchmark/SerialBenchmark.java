package benchmark;

import models.Wear;
import models.Product;
import models.Store;
import org.openjdk.jmh.annotations.*;
import serialization.JavaObjectSerializer;
import serialization.XmlSerializer;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class SerialBenchmark {

    private XmlSerializer xmlSerializer = new XmlSerializer();
    private JavaObjectSerializer javaObjectSerializer = new JavaObjectSerializer();
    private Store testCyclicObject = createStoreWithProducts();
    private Wear testSimpleObject = createWear();

    @Benchmark
    @Warmup(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    public void testJavaSerialization() throws IOException, ClassNotFoundException {
        javaObjectSerializer.serialize(testCyclicObject);
    }

    @Benchmark
    @Warmup(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    public void testXmlSerialization() throws JAXBException, UnsupportedEncodingException {
        xmlSerializer.serialize(testCyclicObject);
    }

    @Benchmark
    @Warmup(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    public void testSimpleObjectToXml() throws JAXBException, UnsupportedEncodingException {
        String serialized = xmlSerializer.serialize(testSimpleObject);
        Wear deserialized = xmlSerializer.deserialize(serialized, Wear.class);
    }

    @Benchmark
    @Warmup(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    public void testSimpleObjectJava() throws IOException, ClassNotFoundException {
        InputStream serialized = javaObjectSerializer.serialize(testSimpleObject);
        Wear deserialized = javaObjectSerializer.deserialize(serialized);
    }

    @Benchmark
    @Warmup(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    public void testCyclicObjectToXml() throws JAXBException, UnsupportedEncodingException {
        String serialized = xmlSerializer.serialize(testCyclicObject);
        Store deserialized = xmlSerializer.deserialize(serialized, Store.class);
    }

    @Benchmark
    @Warmup(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    public void testCyclicObjectJava() throws IOException, ClassNotFoundException {
        InputStream serialized = javaObjectSerializer.serialize(testCyclicObject);
        Store deserialized = javaObjectSerializer.deserialize(serialized);
    }

    private static Store createStoreWithProducts(){
        Store store = new Store("store");

        Product firstProduct = new Product("product1", store);
        Product secondProduct = new Product("product2", store);
        Product thirdProduct = new Product("product3", store);
        Product fourthProduct = new Product("product4", store);
        Product fifthProduct = new Product("product5", store);

        store.setProducts(Arrays.asList(firstProduct, secondProduct, thirdProduct, fourthProduct, fifthProduct));

        return store;
    }

    private static Wear createWear(){
        Wear wear = new Wear();
        wear.setModel("T-shirt");
        return wear;
    }
}
