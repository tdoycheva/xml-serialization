package serialization;

import java.io.*;

public class JavaObjectSerializer {

    public <T> InputStream serialize(T object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(object);
        return new ByteArrayInputStream(bos.toByteArray());
    }

    public <T> T deserialize(InputStream serializedObject) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(serializedObject);
        T object = (T) in.readObject();
        return  object;
    }
}
