import benchmark.SerialBenchmark;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Main {

    private static SerialBenchmark test = new SerialBenchmark();

    public static void main(String[] args)
    {
        long time_1, time_2, time_3;
        time_1 = 0;
        time_2 = 0;
        time_3 = 0;
        try {
            time_1 = System.nanoTime();
            test.testXmlSerialization();
            time_2 = System.nanoTime();
            test.testJavaSerialization();
            time_3 = System.nanoTime();
        }
        catch(JAXBException | IOException | ClassNotFoundException e) {
            System.out.println("Test error." + e.getMessage());
        }
        System.out.println(" ");
        System.out.println("Java serialization against XML serialization");
        System.out.println("Xml: " + (time_2 - time_1)/1000 + " microseconds");
        System.out.println("Java: " + (time_3 - time_2)/1000 + " microseconds");

        try {
            time_1 = System.nanoTime();
            test.testSimpleObjectToXml();
            time_2 = System.nanoTime();
            test.testSimpleObjectJava();
            time_3 = System.nanoTime();
        }
        catch(JAXBException | IOException | ClassNotFoundException e) {
            System.out.println("Test error." + e.getMessage());
        }

        System.out.println();
        System.out.println("Simple Object");
        System.out.println("Xml: " +  (time_2 - time_1)/1000 + " microseconds");
        System.out.println("Java: " + (time_3 - time_2)/1000 + " microseconds");

        try {
            time_1 = System.nanoTime();
            test.testCyclicObjectToXml();
            time_2 = System.nanoTime();
            test.testCyclicObjectJava();
            time_3 = System.nanoTime();
        }
        catch(JAXBException | IOException | ClassNotFoundException e) {
            System.out.println("Test error." + e.getMessage());
        }

        System.out.println(" ");
        System.out.println("Cyclic Object");
        System.out.println("Xml: " + (time_2 - time_1)/1000 + " microseconds");
        System.out.println("Java: " + (time_3 - time_2)/1000 + " microseconds");

    }
}
