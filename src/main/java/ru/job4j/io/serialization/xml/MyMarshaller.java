package ru.job4j.io.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class MyMarshaller {
    public static void main(String[] args) throws JAXBException {
        Client clientJhon = new Client(
                "Jhon", 1, true, new Contact("999999"),
                "building", "education", "food"
        );

        JAXBContext content = JAXBContext.newInstance(Client.class);
        Marshaller myMarshaller = content.createMarshaller();
        myMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream("clientJhon.xml"))
        ) {
            myMarshaller.marshal(clientJhon, out);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("clientJhon.xml"))) {
            String str = reader.readLine();
            while (str != null) {
                System.out.println(str);
                str = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Unmarshaller unmarshaller = content.createUnmarshaller();
        try (BufferedReader reader = new BufferedReader(new FileReader("clientJhon.xml"))) {
            Client newJhon = (Client) unmarshaller.unmarshal(reader);
            System.out.println(newJhon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
