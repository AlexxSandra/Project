package com.my.project.stepdefinitions; // paket kotorye soderjit paket/ classy i pomoghaet ih sgrupperovati

import com.my.project.hooks.Hooks; //importiruet classy nahodeashieasea v drughom package//internal
import com.my.project.pages.CartPage;
import com.my.project.pages.DashboardPage;
import com.my.project.pages.LoginPage;
import com.my.project.util.WaitersConfig;
import io.cucumber.java.en.And; // import from external libriries
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; //imported classy

import java.time.Duration;

import static java.lang.String.format; // imported staticeskie methody
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;


public class SaucedemoStepDefinitions {
    private final WebDriver webDriver;
    private final LoginPage loginPage;
    private final DashboardPage dashboardPage;
    private final CartPage cartPage;

    private static final Logger LOG = LoggerFactory.getLogger(SaucedemoStepDefinitions.class);
    private static final String EXPECTED_ITEM_NAME = "Sauce Labs Backpack";
    private static final String EXPECTED_ITEM_DESCRIPTION =
            "carry.allTheThings() with the sleek, streamlined Sly Pack that " +
                    "melds uncompromising style with unequaled laptop and tablet protection.";

    private static final String EXPECTED_ITEM_PRICE = "$29.99";

    public SaucedemoStepDefinitions() {
        this.webDriver = Hooks.getWebDriver();
        this.loginPage = Hooks.getLoginPage();
        this.dashboardPage = Hooks.getDashboardPage();
        this.cartPage = Hooks.getCartPage();
    }

    @Given("user is accessing the login page")
    public void accessTheWebPage() {
        final String url = "https://www.saucedemo.com/";
        checkPage(url);
        LOG.info(format("User has accessed the login page - {%s}", url));
    }

    @When("user enters correct credentials {string} {string}")
    public void enterCorrectCredentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        LOG.info("User entered the credentials");
    }

    @Then("user is successfully logged in")
    public void checkSuccessfulLogin() {
        final String url = "https://www.saucedemo.com/inventory.html";
        checkPage(url);
        LOG.info(format("User has accessed the login page - {%s}", url));
    }

    @When("user adds to cart an item")
    public void addAnItemToCart() {
        dashboardPage.clickOnAddToCartButton();
        LOG.info("User added an element to cart");
    }

    @And("user clicks on cart icon")
    public void clickCartButton() {
        final String url = "https://www.saucedemo.com/cart.html";
        dashboardPage.clickOnCartIcon();
        checkPage(url);
        LOG.info("User navigates through his cart!");
    }

    @Then("user should see the purchased items")
    public void checkPurchasedItems() {
        assertThat(EXPECTED_ITEM_NAME, is(cartPage.getItemName()));
        assertThat(EXPECTED_ITEM_DESCRIPTION, is(cartPage.getItemDescription()));
        assertThat(EXPECTED_ITEM_PRICE, is(cartPage.getItemPrice()));
        LOG.info("The items from the cart match with items added by user!");
    }

    private void checkPage(String page) {
        webDriver.get(page);
        final WebDriverWait wait =
                new WebDriverWait(webDriver, Duration.ofSeconds(WaitersConfig.getElementIsDisplayed()));
        wait.until(ExpectedConditions.urlToBe(page));
        assertThat(webDriver.getCurrentUrl(), containsString(page));
    }
}


//1. Access modifier - modificator dostupa
//1.1 public
//1.2 private otnositsea toliko k classu k kotoromu ono otnositsea, nelizea modifitsirovati iz vne
// 1.3 protected
//1.4 default me pishitsea


//static - idicator rasprostanenie, dlea vseh obiektov classa public static int age = 15 -primer peremennoi
// public static int getAge () {method body} method