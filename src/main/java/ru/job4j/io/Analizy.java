package ru.job4j.io;

import java.io.*;
import java.util.Objects;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(target))) {
                StringBuilder temp = new StringBuilder();
                String[] prev = {"0", "0"};
                String next = br.readLine();
                while (next != null) {
                    String[] arr = next.split(" ");
                    if ((Objects.equals(arr[0], "400") || Objects.equals(arr[0], "500"))
                            && !(Objects.equals(prev[0], "400") || Objects.equals(prev[0], "500"))) {
                        temp.append(arr[1]).append(";");
                    }
                    if (!(Objects.equals(arr[0], "400") || Objects.equals(arr[0], "500"))
                            && (Objects.equals(prev[0], "400") || Objects.equals(prev[0], "500"))) {
                        temp.append(arr[1]).append(";").append(System.lineSeparator());
                    }
                    prev = arr;
                    next = br.readLine();
                }
                bw.write(temp.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
//        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
//            out.println("15:01:30;15:02:32");
//            out.println("15:10:30;23:12:32");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //Пример 1
        Analizy sample1 = new Analizy();
        try (PrintWriter out = new PrintWriter(new FileOutputStream("logs1.csv"))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }
        sample1.unavailable("logs1.csv", "unavailable1.cvs");

        try (BufferedReader br = new BufferedReader(new FileReader("unavailable1.cvs"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Пример 2
        try (PrintWriter out = new PrintWriter(new FileOutputStream("logs2.csv"))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        } catch (Exception e) {
            e.printStackTrace();
        }

        sample1.unavailable("logs2.csv", "unavailable2.cvs");

        try (BufferedReader br = new BufferedReader(new FileReader("unavailable2.cvs"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}