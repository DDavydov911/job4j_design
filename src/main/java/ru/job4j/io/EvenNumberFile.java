package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            Scanner scanner = new Scanner(in);
            while (scanner.hasNext()) {
                System.out.println(scanner.nextInt() % 2 == 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
