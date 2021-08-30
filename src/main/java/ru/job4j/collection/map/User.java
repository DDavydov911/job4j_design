package ru.job4j.collection.map;

import java.util.Calendar;

public class User {
    private String name;
    private int getChildren;
    private Calendar birthday;

    public User(String name, int getChildren, Calendar birthday) {
        this.name = name;
        this.getChildren = getChildren;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getGetChildren() {
        return getChildren;
    }

    public Calendar getBirthday() {
        return birthday;
    }
}
