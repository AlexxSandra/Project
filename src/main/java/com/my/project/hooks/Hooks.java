package com.my.project.hooks;

import com.my.project.pages.*;
import com.my.project.util.MyWebDriver;
import com.my.project.util.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends HooksInstances {//nasledovanie(polimarfizm)

    private Hooks() { //private citob ne initsializirovati ne sozdavati konstruktor
    }

    @Before  //deistviea vypolneaetsea kajdyi raz do togo kak vypolneaem kakoito step
    //TODO dlea cego nujen hook before
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

    @After//deistviea posle scenariea - zakryti webbrowser
    public static void tearDown() {
        webDriver.close();
        MyWebDriver.closeDriver();
        scenarioContext.clearContext();
    } //zakryti step kogda on zakoncilsea

}
