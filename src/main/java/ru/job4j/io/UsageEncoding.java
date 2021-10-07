package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class UsageEncoding {
    public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.lines().forEach(builder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

//    Старый способ
    public void writeDataInFile(String path, String data) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true)
        )) {
            pw.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Новый способ без открытия и закрытия потока в цикле,
     *  а полное создание файла в памяти и потом полная запись в файл в одном открытом потоке
     *     public void writeDataInFile(String path, List<String> data) {
     *         try (PrintWriter pw = new PrintWriter(
     *                 new FileWriter(path, Charset.forName("WINDOWS-1251"), true)
     *         )) {
     *             data.forEach(pw::println);
     *         } catch (IOException e) {
     *             e.printStackTrace();
     *         }
     *     }
     *
     *     Поток открывается и закрывается на каждой итерации, что дорого
     * @param args
     */

    public static void main(String[] args) {
        String path = "./src/data/text.txt";
        UsageEncoding encoding = new UsageEncoding();
        List<String> strings = List.of(
                "Новая строка 1",
                "Новая строка 2",
                "Новая строка 3",
                "Новая строка 4",
                "Новая строка 5"
        );
        for (String str : strings) {
            encoding.writeDataInFile(path, str);
        }
        String s = encoding.readFile(path);
        System.out.println("Данные из файла: ");
        System.out.println(s);
    }
}