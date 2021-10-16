package ru.job4j.searcher;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Пример вызова
 * java -jar find.jar -d=c:/ -n=**.txt -t=mask -o=log.txt
 */

public class Find {

    public static void checkArgs(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Вызов файла должен иметь 4 аргумента:\n"
                    + "-d - директория, c которой начинать поиск.\n"
                    + "-n - имя файла, маска, либо регулярное выражение.\n"
                    + "-t - тип поиска: mask искать по маске, name по полному "
                    + "совпадение имени, regex по регулярному выражению.\n"
                    + "-o - имя файла, в который записать результат."
                    + "Пример: java -jar find.jar -d=c:/ -n=**.txt -t=mask -o=log.txt");
        }
    }

    public static Predicate<Path> getPredicate(ArgsName an) {
        Predicate<Path> chosenPredicate;
        String mask = an.get("t");
        String maskValue = an.get("n");
        switch (mask) {
            case "name" :
                chosenPredicate = p -> Objects.equals(p.getFileName().toString(), an.get("n"));
                break;
            case "mask":
                chosenPredicate = p -> {
                    PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:" + maskValue);
                    return matcher.matches(p);
                };
                break;
            case "regex":
                chosenPredicate = p -> {
                    PathMatcher matcher = FileSystems.getDefault().getPathMatcher("regex:" + maskValue);
                    return matcher.matches(p);
                };
                break;
            default:
                throw new IllegalArgumentException("Неправильно указан  тип поиска \"-t\"");
        }
        return chosenPredicate;
    }

    public static void main(String[] args) throws IOException {
        checkArgs(args);
        ArgsName an = ArgsName.of(args);
        Path start = Paths.get(an.get("d"));
        Predicate<Path> pathPredicate = getPredicate(an);
        List<Path> paths = Search.search(start, pathPredicate);
        SaveLogs<Path> sl = new SaveLogs<>();
        sl.save(paths, an.get("o"));
    }
}
