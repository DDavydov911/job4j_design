package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(" ");
                if ("404".equals(arr[arr.length - 2])) {
                    result.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter pwr = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)))
        ) {
            for (String s : log) {
                pwr.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String s : log) {
            System.out.println(s);
        }
        save(log, "404.txt");
    }
}