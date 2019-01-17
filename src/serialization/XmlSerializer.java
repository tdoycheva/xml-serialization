package serialization;

import org.eclipse.persistence.jaxb.JAXBContextFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class XmlSerializer {
    public <T> String serialize(T object) throws JAXBException {
        StringWriter stringWriter = new StringWriter();
        JAXBContext context = JAXBContextFactory.createContext(new Class[]{object.getClass()}, new HashMap<>());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(object, stringWriter);
        return stringWriter.toString();
    }

    public <T> T deserialize (String serializedObject, Class<T> objectType) throws JAXBException, UnsupportedEncodingException {
        JAXBContext context = JAXBContextFactory.createContext(new Class[]{objectType}, new HashMap<>());
        Unmarshaller unmarshaller = context.createUnmarshaller();
        InputStream inputStream = getInputStreamFromString(serializedObject);
        T object = (T)unmarshaller.unmarshal(inputStream);
        return object;
    }

    private static InputStream getInputStreamFromString(String data) throws UnsupportedEncodingException {
        return new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8.name()));
    }
}
