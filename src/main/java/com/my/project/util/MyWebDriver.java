package com.my.project.util;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MyWebDriver {
    private static org.openqa.selenium.WebDriver driver;

    private MyWebDriver() {
    }

    public static org.openqa.selenium.WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();// add some proprieties if needed
            driver = new ChromeDriver(chromeOptions);
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}