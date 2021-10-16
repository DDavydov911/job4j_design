package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class Client {
    String name;
    int id;
    boolean resident;
    Contact contact;
    String[] projects;

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

    public static void main(String[] args) {
        Client first = new Client("Jhon", 1, true,
                new Contact("999-999"), "building", "education", "food");
/**
 *         Gson gson = new GsonBuilder().create();
 *         String jsonstr = gson.toJson(first);
 *         System.out.println(jsonstr);
 *         Client clientFromJson = gson.fromJson(jsonstr, Client.class);
 *         System.out.println(clientFromJson);
 */


        List<String> list = List.of("hi", "fi");
        JSONObject jhonObject = new JSONObject(first);
 /**
 * jhonObject.put("name", first.name);
 *         jhonObject.put("id", first.id);
 *         jhonObject.put("resident", first.resident);
 *         jhonObject.put("contact", new Contact("888888"));
 *         jhonObject.put("projects", first.projects);
 */

        System.out.println(jhonObject);
 /**
 * System.out.println(new JSONArray(list));
 */
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isResident() {
        return resident;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getProjects() {
        return projects;
    }
}
