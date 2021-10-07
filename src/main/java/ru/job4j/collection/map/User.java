package ru.job4j.collection.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday.getTime()
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        Calendar myCalendar = new GregorianCalendar(1970, Calendar.JANUARY, 1);
        User user1 = new User("Ivan", 0, myCalendar);
        User user2 = new User("Ivan", 0, myCalendar);
        Object obj = new Object();
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println("a".hashCode());
        System.out.println("b".hashCode());
        System.out.println("ab".hashCode());
        System.out.println(3105 / 98);
        System.out.println(0xff);
    }

}
