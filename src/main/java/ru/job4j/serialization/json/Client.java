package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

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

        Gson gson = new GsonBuilder().create();
        String jsonstr = gson.toJson(first);
        System.out.println(jsonstr);
        Client clientFromJson = gson.fromJson(jsonstr, Client.class);
        System.out.println(clientFromJson);
    }

}
