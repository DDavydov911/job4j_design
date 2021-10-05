package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.job4j.io.Search.*;

public class Zip {
//java -jar pack.jar -d=c:\project\job4j\ -e=xml -o=project.zip
//java -jar pack.jar -d=C:\Users\Lenovo\IdeaProjects\job4j_design\src\main\java\ru\job4j\ -e=class -o=project.zip

    public static void main(String[] args) throws IOException {
        ArgsName an = ArgsName.of(args);
        List<Path> list = search(Path.of(an.get("d")), p -> !p.toFile().getName().endsWith(an.get("e")));
        packFiles(list, Path.of(an.get("o")));
    }

    public static void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(String.valueOf(target))))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(String.valueOf(source)));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(String.valueOf(source)))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(String.valueOf(target))))) {
            zip.putNextEntry(new ZipEntry(String.valueOf(source)));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(String.valueOf(source)))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}