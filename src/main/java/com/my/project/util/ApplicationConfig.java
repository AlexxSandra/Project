package com.my.project.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ApplicationConfig {//peremenaea tipa class
    private static final String propertiesFile = "src/main/resources/properties/application.properties";
    private static final Properties properties;// dobavili class propertiessozdati svoi haracteristiki
    private static final Logger log = LoggerFactory.getLogger(ApplicationConfig.class);

    static {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(propertiesFile)) {
            properties.load(fileInputStream);//citaet svoistva iz file/ fileiNPUTsTReam instrument dlea citenie file
        } catch (IOException e) {//v sluceae oshibki
            log.info("Wrong file path or file was renamed");
            throw new RuntimeException(e);// vyzvati class opisyvaiushii osibku (podhod ErrorHandling)
        }
    }

    public static int getElementIsDisplayed() {
       return Integer.parseInt(properties.getProperty("element.is.displayed"));//dostati propreties
    }

    public static String getUrl(String endpoint) {
        return properties.getProperty("url.for." + endpoint);// is url delaet string
    }
}
