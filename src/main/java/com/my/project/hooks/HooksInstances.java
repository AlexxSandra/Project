package com.my.project.hooks;

import com.my.project.pages.*;
import com.my.project.util.ScenarioContext;
import org.openqa.selenium.WebDriver;

public class HooksInstances {

    protected static WebDriver webDriver;
    protected static ScenarioContext scenarioContext;
    protected static LoginPage loginPage;
    protected static DashboardPage dashboardPage;
    protected static CartPage cartPage;
    protected static CheckOutYourInformation checkOutYourInformationPage;
    protected static CheckoutOverview checkoutOverviewPage;
    protected static CheckoutCompletePage checkoutCompletePage;

    public static LoginPage getLoginPage() {
        return loginPage;
    }

    public static DashboardPage getDashboardPage() {
        return dashboardPage;
    }

    public static CartPage getCartPage() {
        return cartPage;
    }

    public static CheckOutYourInformation checkOutYourInformationPage() {
        return checkOutYourInformationPage;
    }

    public static CheckoutOverview checkOutOverviewPage() {
        return checkoutOverviewPage;
    }

    public static CheckoutCompletePage checkoutCompletePage() {
        return checkoutCompletePage;
    }
}
