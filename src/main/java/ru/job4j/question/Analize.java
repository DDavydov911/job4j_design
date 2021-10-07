package ru.job4j.question;

import java.util.*;

public class Analize {
    /**
     *         Есть ещё одно решение - не оптимальное
     *         Set<User> deleted = new HashSet<>(previous);
     *         Set<User> added = new HashSet<>(current);
     *         deleted.removeAll(current);
     *         info.setDeleted(deleted.size());
     *         added.removeAll(previous);
     *         info.setAdded(added.size());
     *         for(User user :previous) {
     *             for (User user1 : current) {
     *                 if (user.getId() == user1.getId() && !user.getName().equals(user1.getName())) {
     *                     info.setChanged(info.getChanged() + 1);
     *                 }
     *             }
     *         }
     *
     * @param previous
     * @param current
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Map myMap = new HashMap<K, V>();
        for (User user : previous) {
            myMap.put(user.getId(), user.getName());
        }
        for (User user : current) {
            int id = user.getId();
            String name = user.getName();
            if (myMap.containsKey(id) && !Objects.equals(name, myMap.get(id))) {
                info.setChanged(info.getChanged() + 1);
            }
            myMap.put(id, name);
        }
        int unchanged = previous.size() + current.size() - myMap.size();
        info.setDeleted(previous.size() - unchanged);
        info.setAdded(current.size() - unchanged);
        return info;
    }
}