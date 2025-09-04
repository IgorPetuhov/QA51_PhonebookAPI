package com.phonebook.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    private static final String PROF_FILE = "/data.properties";

    public PropertiesLoader() {
    }

    public static String loadProperties(String name) {
        Properties properties = new Properties();
        try {
            properties.load(PropertiesLoader.class.getResourceAsStream(PROF_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String value = "";
        if (name != null) {
            value = properties.getProperty(name);
        }
        return value;
    }

}
