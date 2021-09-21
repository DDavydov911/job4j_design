package ru.job4j.collection.map;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenDoublePutThenTrueAndFalse() {
        Map map = new SimpleMap();
        assertTrue(map.put("Key1", "Value1"));
        assertFalse(map.put("Key1", "Value1"));
    }

    @Test
    public void whenGetValueByTrueKeyThanTrueValue() {
        Map map = new SimpleMap();
        map.put("Key1", "Value1");
        assertThat(map.get("Key1"), is("Value1"));
    }

    @Test
    public void whenGetNullByFalseKeyThanNull() {
        Map map = new SimpleMap();
        map.put("Key1", "Value1");
        assertNull(map.get("Key2"));
    }

    @Test
    public void whenRemoveTrueEntry() {
        Map map = new SimpleMap();
        map.put("Key1", "Value1");
        assertTrue(map.remove("Key1"));
    }

    @Test
    public void whenRemoveFalseEntry() {
        Map map = new SimpleMap();
        map.put("Key1", "Value1");
        assertFalse(map.remove("Key2"));
    }
}