package com.my.project.stepdefinitions; // paket kotorye soderjit paket/ classy i pomoghaet ih sgrupperovati

import com.my.project.hooks.Hooks; //importiruet classy nahodeashieasea v drughom package//internal
import com.my.project.pages.*;
import com.my.project.util.ApplicationConfig;
import com.my.project.util.MyWebDriver;
import com.my.project.util.ScenarioContext;
import io.cucumber.java.en.And; // import from external libriries
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;//
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; //imported classy

import java.time.Duration;

import static java.lang.String.format; // imported staticeskie methody
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SaucedemoStepDefinitions {//  staticeskaea funktsiea// obiect classa
    private final WebDriver webDriver;//zadeklarirovali polea
    private final ScenarioContext scenarioContext;//zadeklarirovali polea
    private final LoginPage loginPage;
    private final DashboardPage dashboardPage;
    private final CartPage cartPage;
    private final CheckOutYourInformation checkOutYourInformationPage;
    private final CheckoutOverview  checkoutOverviewPage;
    private final CheckoutCompletePage checkoutCompletePage;

    private static final Logger LOG = LoggerFactory.getLogger(SaucedemoStepDefinitions.class);// =to k cemu my priravnivaem peremennuiu
    private static final String EXPECTED_ITEM_NAME = "Sauce Labs Backpack";//string
    private static final String EXPECTED_ITEM_DESCRIPTION =
            "carry.allTheThings() with the sleek, streamlined Sly Pack that " +
                    "melds uncompromising style with unequaled laptop and tablet protection.";

    private static final String EXPECTED_ITEM_PRICE = "$29.99";

//    private static SaucedemoStepDefinitions instance;
//
//    public static SaucedemoStepDefinitions getInstance() {
//        if (instance == null) {
//            instance = new SaucedemoStepDefinitions();
//        }
//        return instance;
//    }

    public SaucedemoStepDefinitions() { //konstruktor bez parametrov//
        this.webDriver = MyWebDriver.getDriver(); //prirvnivaem/initsializiruem v construktore.priravnivaem k kakoito funktsii kotoraea nahoditsea v hooks
        this.scenarioContext = ScenarioContext.getInstance();
        this.loginPage = Hooks.getLoginPage();// this.
        this.dashboardPage = Hooks.getDashboardPage();//hooks -class name
        this.cartPage = Hooks.getCartPage();
        this.checkOutYourInformationPage = Hooks.checkOutYourInformationPage();
        this.checkoutOverviewPage = Hooks.checkOutOverviewPage();
        this.checkoutCompletePage = Hooks.checkoutCompletePage();


    }


    @Given("user is accessing the login page")
    public void accessTheWebPage() {
        final String url = ApplicationConfig.getUrl("saucedemo");
        checkPage(url);
        LOG.info(format("User has accessed the login page - {%s}", url));
    }

    @When("user enters correct credentials {string} {string}")
    public void enterCorrectCredentials(String username, String password) {
        loginPage.enterUsername(username);//method body
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        LOG.info("User entered the credentials");
    }

    @Then("user is successfully logged in")
    public void checkSuccessfulLogin() {
        final String url = ApplicationConfig.getUrl("inventory");
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
        final String url = ApplicationConfig.getUrl("cart");
        dashboardPage.clickOnCartIcon();//esti obiect dash.. i vyzyvaiu method
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
                new WebDriverWait(webDriver, Duration.ofSeconds(ApplicationConfig.getElementIsDisplayed()));
        wait.until(ExpectedConditions.urlToBe(page));// ty assertishi cito etot url takoi kak ea dala
        assertThat(webDriver.getCurrentUrl(), containsString(page));
    }

    @When("user enters wrong credentials {string} {string}")
    public void userEntersWrongCredentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        LOG.info("User entered wrong credentials");
    }

    @Then("logged in is failed with following error message: {string}")
    public void loggedInIsFailedWithFollowingErrorMessage(String errorMessage) {
        assertThat(errorMessage, is(loginPage.getErrorMessage()));//sravnivaem cito hotim poluciti
    }

    @When("user clicks on remove button")
    public void userClicksOnRemoveButton() {
        cartPage.clickRemoveButton();
        LOG.info("User clicks remove button");//log info vvyvodit info na cansole
    }

    @Then("purchased item should be removed")
    public void purchasedItemShouldBeRemoved() {
        Assertions.assertThrows(NoSuchElementException.class, () -> cartPage.getItemName());//get ishet element, nenahodit vykidyvaet exeption
        Assertions.assertThrows(NoSuchElementException.class, () -> cartPage.getItemDescription());
        Assertions.assertThrows(NoSuchElementException.class, () -> cartPage.getItemPrice());
        LOG.info("The items from the cart was removed!");//poiskati v google kak proveriti cito element iscez so stranitsy. Nevidimmosti elementa
    }

    @When("user clicks on checkout button")
    public void userClicksOnCheckoutButton() {
        cartPage.clickCheckOutButton();
        LOG.info("User clicks on checkout button");

    }

    @And("enters credentials {string} {string} {string}")
    public void entersCredentials(String firstname, String lastname, String zipPostalCode) {
        checkOutYourInformationPage.enterFirstName(firstname);
        checkOutYourInformationPage.enterLastName(lastname);
        checkOutYourInformationPage.enterZipPostalCode();
        LOG.info("The credentials added");

    }

    @And("user clicks continue button")
    public void userClicksContinueButton() {
        checkOutYourInformationPage.clickContinueButton();
        LOG.info("User clicks on continue button");

    }
    

    @And("user clicks on Finish button")
    public void userClicksOnFinishButton() {
        checkoutOverviewPage.clickFinishButton();
        LOG.info("User clicks on finish button");
    }

    @Then("order is completed with following success message: {string}")
    public void orderIsCompletedWithFollowingSuccessMessage(String successMessage) {
       // assertThat(successMessage, is(loginPage.getSuccessMessage()));//sravnivaem cito hotim poluciti


    }
}

//1. Access modifier - modificator dostupa
//1.1 public
//1.2 private otnositsea toliko k classu k kotoromu ono otnositsea, nelizea modifitsirovati iz vne
// 1.3 protected
//1.4 default me pishitsea


//static - idicator rasprostanenie, dlea vseh obiektov classa public static int age = 15 -primer peremennoi
// public static int getAge () {method body} method
