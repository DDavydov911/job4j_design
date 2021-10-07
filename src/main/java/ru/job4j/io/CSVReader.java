package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String[] filters = argsName.get("filter").split(",");
        String delim = argsName.get("delimiter");

        Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")));
        ArrayList<ArrayList<String>> allLines = new ArrayList<>();
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            Scanner lineScanner = new Scanner(nextLine).useDelimiter(delim);
            ArrayList<String> line = new ArrayList<>();
            while (lineScanner.hasNext()) {
                line.add(lineScanner.next());
            }
            allLines.add(line);
            lineScanner.close();
        }
        scanner.close();

        try (PrintStream printStream = new PrintStream(new FileOutputStream(argsName.get("out")))) {
            for (ArrayList<String> allLine : allLines) {
                StringJoiner sj = new StringJoiner(delim);
                for (int j = 0; j < allLine.size(); j++) {
                    for (String filter : filters) {
                        if (filter.equals(allLines.get(0).get(j))) {
                            sj.add(allLine.get(j));
                        }
                    }
                }
                printStream.println(sj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * В качестве параметров использовал эту строку для проверки работоспособности
     * java -jar target/csvReader.jar -path=file.csv -delimiter=";"  -out=stdout -filter=name,age
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ArgsName an = ArgsName.of(args);
        CSVReader.handle(an);
    }
}
