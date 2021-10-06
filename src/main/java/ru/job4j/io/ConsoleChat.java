package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private final List<String> logs = new ArrayList<>();
    public List<String> phrases;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        phrases = readPhrases();
    }

    public void run() {
        boolean botInProgress = true;
        Scanner scanner = new Scanner(System.in);

        String ba;
        System.out.println("Начните разговор с ботом");
        String question = scanner.nextLine();
        while (true) {
            switch (question) {
                case OUT:
                    logs.add(OUT);
                    saveLog(logs);
                    scanner.close();
                    return;
                case STOP:
                    botInProgress = false;
                    logs.add(question);
                    break;
                case CONTINUE:
                    logs.add(CONTINUE);
                    botInProgress = true;
                    ba = getBotAnswers();
                    System.out.println(ba);
                    logs.add(ba);
                    break;
                default:
                    logs.add(question);
                    if (botInProgress) {
                        ba = getBotAnswers();
                        System.out.println(ba);
                        logs.add(ba);
                    }
            }
        }

    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter bwr = new PrintWriter(new FileWriter(path, true))) {
            log.forEach(bwr::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getBotAnswers() {
        return phrases.get((int) (Math.random() * phrases.size()));
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("conversations.txt", "botAnswers.txt");
//        cc.phrases.forEach(System.out::println);
        cc.run();
    }
}