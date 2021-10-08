package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Jhon Dow";
        byte age = 33;
        short smth = 32000;
        int count = 12345678;
        long pass = 1234567890987654321L;
        float high = 1.98F;
        double smthToo = 3.87654;
        char code = 'G';
        boolean approved = true;

        LOG.debug("\nUser info name : {},\n age : {},\n smth : {},\n count : {},\n"
                + " pass : {},\n high : {},\n smthToo : {},\n cpde : {},\n approved : {}\n",
                name, age, smth, count, pass, high, smthToo, code, approved);
    }
}