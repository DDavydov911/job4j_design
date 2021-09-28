package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("#") && line.contains("=")) {
                    String[] temp = line.split("=");
                    if (temp.length == 2) {
                        values.put(temp[0], temp[1]);
                    } else if (line.startsWith("=")) {
                        values.put(null, temp[0]);
                    } else {
                        values.put(temp[0], null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        String result = null;
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        try {
            result = values.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config test = new Config("src/main/java/ru/job4j/Data/app.properties");
        test.load();
        System.out.println("\n" + test.value("hibernate.connection.password"));
        System.out.println("\n" + test);
        System.out.println("\n" + new Config("src/main/java/ru/job4j/Data/pair_without_comment.properties"));
    }

}
