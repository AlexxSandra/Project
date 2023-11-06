package com.my.project.hooks;

import com.my.project.pages.CartPage;
import com.my.project.pages.DashboardPage;
import com.my.project.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {
    private static WebDriver webDriver;
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static CartPage cartPage;

    private Hooks() {
    }

    @Before
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        webDriver = new ChromeDriver(chromeOptions);
        loginPage = new LoginPage(webDriver);
        dashboardPage = new DashboardPage(webDriver);
        cartPage = new CartPage(webDriver);
    }

    @After
    public static void tearDown() {
        webDriver.quit();
    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public static LoginPage getLoginPage() {
        return loginPage;
    }

    public static DashboardPage getDashboardPage() {
        return dashboardPage;
    }

    public static CartPage getCartPage() {
        return cartPage;
    }
}
