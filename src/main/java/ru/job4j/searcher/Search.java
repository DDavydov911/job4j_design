package ru.job4j.searcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFileVisitor fileVisitor = new SearchFileVisitor(condition);
        Files.walkFileTree(root, fileVisitor);
        return fileVisitor.getPaths();
    }
}
