package ru.job4j.searcher;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveLogs<T> {
    /**
     * Метод сохраняет элементы списка в файл. Может использоваться для целей логирования.
     * @param list Аргумент должен содержать перечень элементов, которые необходимо записать в файл
     * @param fileName Имя файла, в который будет сохранен список
     */
    public void save(List<T> list, String fileName) {
        try (BufferedWriter bwr = new BufferedWriter(new FileWriter(fileName))) {
            for (T el : list) {
                bwr.write(el.toString());
                bwr.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
