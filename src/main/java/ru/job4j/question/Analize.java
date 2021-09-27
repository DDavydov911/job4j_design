package ru.job4j.question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Set<User> deleted = new HashSet<>(previous);
        Set<User> added = new HashSet<>(current);
        deleted.removeAll(current);
        info.setDeleted(deleted.size());
        added.removeAll(previous);
        info.setAdded(added.size());
        for (User user : previous) {
            for (User user1 : current) {
                if (user.getId() == user1.getId() && !user.getName().equals(user1.getName())) {
                    info.setChanged(info.getChanged() + 1);
                }
            }
        }
        return info;
    }
}