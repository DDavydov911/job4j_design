package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenServerUnavailableOnce() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        Analizy analysis = new Analizy();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        }
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(target))) {
            br.lines().forEach(sb::append);
        }
        assertThat(sb.toString(), is("10:57:01;11:02:02;"));
    }

    @Test
    public void whenServerUnavailableTwice() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        Analizy analysis = new Analizy();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        }
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(target))) {
            br.lines().forEach(sb::append);
        }
        assertThat(sb.toString(), is("10:57:01;10:59:01;11:01:02;11:02:02;"));
    }
}