package com.my.project.hooks;

import com.my.project.pages.*;
import com.my.project.util.MyWebDriver;
import com.my.project.util.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class Hooks {
    private static WebDriver webDriver;
    private static ScenarioContext scenarioContext;
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static CartPage cartPage;
    private static CheckOutYourInformation checkOutYourInformationPage;
    private static CheckoutOverview checkoutOverviewPage;
    private static CheckoutCompletePage checkoutCompletePage;

    private Hooks() { //private citob ne initsializirovati ne sozdavati konstruktor
    }

    @Before  //vypolneaetsea kajdyi raz do togo kak vypolneaem kakoito step
    public static void setUp() {
        webDriver = MyWebDriver.getDriver();//kajdyi raz pered novym stepom my sozdaeom novyi web driver
        loginPage = new LoginPage(webDriver);//sozdali novyi obiect dlea
        dashboardPage = new DashboardPage(webDriver);
        cartPage = new CartPage(webDriver);
        checkOutYourInformationPage = new CheckOutYourInformation(webDriver);
        checkoutOverviewPage = new CheckoutOverview(webDriver);
        checkoutCompletePage = new CheckoutCompletePage(webDriver);
        scenarioContext = ScenarioContext.getInstance();

    }

    @After//zakryti webbrowser
    public static void tearDown() {
        webDriver.close();
        MyWebDriver.closeDriver();
        scenarioContext.clearContext();

    } //zakryti step kogda on zakoncilsea

    public static LoginPage getLoginPage() {
        return loginPage;
    }

    public static DashboardPage getDashboardPage() {
        return dashboardPage;
    }

    public static CartPage getCartPage() { return cartPage; }

    public static CheckOutYourInformation checkOutYourInformationPage() { return checkOutYourInformationPage; }

    public static CheckoutOverview checkOutOverviewPage () {return checkoutOverviewPage;}

    public static CheckoutCompletePage checkoutCompletePage () {return checkoutCompletePage;}


}
