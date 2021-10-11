package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;


@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int id;

    @XmlAttribute
    private boolean resident;

    private Contact contact;

    @XmlElementWrapper(name = "projects")
    @XmlElement(name = "project")
    private String[] projects;

    public Client() { }

    public Client(String name, int id, boolean resident, Contact contact, String... projects) {
        this.name = name;
        this.id = id;
        this.resident = resident;
        this.contact = contact;
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Client{"
                + "name='" + name + '\''
                + ", id=" + id
                + ", resident=" + resident
                + ", contact=" + contact
                + ", projects=" + Arrays.toString(projects)
                + '}';
    }
}
