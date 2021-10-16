package ru.job4j.searcher;

import java.io.IOException;
import java.nio.file.*;
import java.util.function.Predicate;

public class Testing {
    public static void main(String[] args) throws IOException {
        System.out.println("Предикат с маской:");
        Predicate<Path> predicate1 = p -> {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + "**.txt");
            return matcher.matches(p);
        };
        Path start1 = Paths.get("C:\\Users\\Lenovo\\IdeaProjects\\job4j_design");
        Search.search(start1, predicate1).forEach(System.out::println);

        System.out.println("Предикат с REGEX:");
        Predicate<Path> predicate2 = p -> {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("regex:" + ".*\\.txt$");
            return matcher.matches(p);
        };
        Path start2 = Paths.get("C:\\Users\\Lenovo\\IdeaProjects\\job4j_design");
        Search.search(start2, predicate2).forEach(System.out::println);
    }
}
