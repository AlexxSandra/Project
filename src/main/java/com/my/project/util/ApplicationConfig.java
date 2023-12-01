package com.my.project.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ApplicationConfig {
    private static final String PROPERTIES_FILE = "src/main/resources/properties/application.properties";
    private static final Properties PROPERTIES;
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfig.class);

    static {
        PROPERTIES = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE)) {
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            LOG.info("Wrong file path or file was renamed");
            throw new RuntimeException(e);
        }
    }

    public static int getElementIsDisplayed() {
       return Integer.parseInt(PROPERTIES.getProperty("element.is.displayed"));
    }

    public static String getUrl(String endpoint) {
        return PROPERTIES.getProperty("url.for." + endpoint);
    }
}
