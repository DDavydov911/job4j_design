package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private HashSet<FileProperty> set = new HashSet<>();
    private List<Path> paths = new ArrayList<>();

    public List<Path> getPaths() {
        System.out.println("Поиск дубликатов файлов...");
        if (paths.isEmpty()) {
            System.out.println("Дубликаты не найдены");
        } else {
            System.out.println("Найдены следующие дубликаты:");
        }
        return paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        long size = Files.size(file);
        String name = file.getFileName().toString();
        FileProperty next = new FileProperty(size, name);
        if (set.contains(next)) {
            paths.add(file);
        }
        set.add(next);
        return FileVisitResult.CONTINUE;
    }
}
