package Utilites;

import java.io.*;

public class Deserializator {

    public Object toDeserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
            return object;
        } catch (Exception e) {
            return "nope";
        }
    }
}