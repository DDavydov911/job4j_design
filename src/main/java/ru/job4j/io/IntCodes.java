package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class IntCodes {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("CharCodesOfIntegers.txt")) {
            for (int i = 47; i < 124; i++) {
                out.write((i + " = " + (char) i + "\t").getBytes());
                if (i % 8 == 0) {
                    out.write(System.lineSeparator().getBytes());
                }
            }
            out.write(System.lineSeparator().getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write(("10 = " + (char) 10).getBytes());
            out.write(("13 = " + (char) 13).getBytes());
            for (int i = 0; i < 73; i++) {
                out.write(((char) ('0' + i) + " = " + ('0' + i) + "\t\t").getBytes());
                if (i % 8 == 0) {
                    out.write(System.lineSeparator().getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
