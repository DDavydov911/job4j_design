package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        String[] filters = argsName.get("filter").split(",");
        String delim = argsName.get("delimiter");
        String outValue = argsName.get("out");
        ArrayList<ArrayList<String>> allLines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")));
             PrintStream printStream = (outValue.equals("stdout")) ? new PrintStream(System.out)
                     : new PrintStream(new FileOutputStream(outValue))
        ) {
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                try (Scanner lineScanner = new Scanner(nextLine).useDelimiter(delim)) {
                    ArrayList<String> line = new ArrayList<>();
                    while (lineScanner.hasNext()) {
                        line.add(lineScanner.next());
                    }
                    allLines.add(line);
                }
            }
            for (ArrayList<String> line : allLines) {
                StringJoiner stringJoiner = new StringJoiner(delim);
                for (int j = 0; j < line.size(); j++) {
                    for (String filter : filters) {
                        if (filter.equals(allLines.get(0).get(j))) {
                            stringJoiner.add(line.get(j));
                        }
                    }
                }
                printStream.println(stringJoiner);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * В качестве параметров использовал эту строку для проверки работоспособности
     * java -jar target/csvReader.jar -path=file.csv -delimiter=";"  -out=stdout -filter=name,age
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ArgsName an = ArgsName.of(args);
        CSVReader.handle(an);
    }
}
