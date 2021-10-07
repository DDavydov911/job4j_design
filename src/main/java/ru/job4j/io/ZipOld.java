package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipOld {

    public static void packFiles(List<File> sources, File target) {
        for (File source : sources) {
            packSingleFile(source, target);
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File out = new File("./project.zip");
        packSingleFile(
                new File("./input.txt"),
                out
        );
        packSingleFile(
                new File("src/main/java/ru/job4j/question/Analize.java"),
                out
        );
        packSingleFile(
                new File("src/main/java/ru/job4j/Trigger.java"),
                new File("./project.zip")
        );
    }
}