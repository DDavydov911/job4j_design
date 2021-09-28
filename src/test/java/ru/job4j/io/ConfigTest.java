package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "src/main/java/ru/job4j/Data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"),is("Petr Arsentev"));
    }

    @Test
    public void whenKeyWithoutValue() {
        String path = "src/main/java/ru/job4j/Data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("mainname"), is(Matchers.nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void  WhenIllegalArgument(){
        String path = "src/main/java/ru/job4j/Data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("surname"));
    }
}