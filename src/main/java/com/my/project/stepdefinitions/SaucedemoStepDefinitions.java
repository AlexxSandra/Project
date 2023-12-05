package com.my.project.stepdefinitions; // paket kotorye soderjit paket/ classy i pomoghaet ih sgrupperovati

import com.my.project.hooks.Hooks; //importiruet classy nahodeashieasea v drughom package/
import com.my.project.pages.*; //vcliuceaiut deistviea do posle scenariea i imeiut obiecty stranits
import com.my.project.util.ApplicationConfig;//predostavleaet dostup k nastroikam konfiguratsieam
import com.my.project.util.MyWebDriver;
import com.my.project.util.ScenarioContext;//hraniti obmenivaetsea dannymimejdu etapi
import io.cucumber.java.en.And; // import from external libriries//cucumber annotation
import io.cucumber.java.en.Given;//dlea opredelinie shagov v scenari
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;//junit predostavleaet razlicinye metody dlea utverjdeniea uslovii v testeh
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;//selenium webdriver eto interfeis dlea vzaimodeistviea s browserom
import org.openqa.selenium.support.ui.ExpectedConditions;//ispolizuetsea dlea uslovii ojidaniea
import org.openqa.selenium.support.ui.WebDriverWait;//ispolizuetsea dlea uslovii ojidaniea
import org.slf4j.Logger;//interfeis dlea logging messges
import org.slf4j.LoggerFactory; //polucenie eksemplearov logger

import java.time.Duration;// dlea predostavlenie intervalov vremeni

import static java.lang.String.format; //  staticeskie methody//pozvoleaet formatirovati stroki
import static org.hamcrest.MatcherAssert.assertThat;//dlea Api napisaniea utverjdenii v bolee citaemoi forme
import static org.hamcrest.Matchers.*;


public class SaucedemoStepDefinitions {//staticeskaea funktsiea// obiect classa vliuceaiushii SD for cucumber scenarious
    private final WebDriver webDriver;//zadeklarirovali private polea, exempleary drivera web-browser, ispolizuetsea dlea avtomatizatsii brauzera
    private final ScenarioContext scenarioContext;
    private final LoginPage loginPage;
    private final DashboardPage dashboardPage;
    private final CartPage cartPage;
    private final CheckOutYourInformation checkOutYourInformationPage;
    private final CheckoutOverview  checkoutOverviewPage;
    private final CheckoutCompletePage checkoutCompletePage;// *obieavili cito v etom klasse budem rabotati s etimi peremennami*
    // final means value cannot be change after initsialization * Global variables- v kajdom methode mogu ispolizovati obieavleaea , ne sozdavaea ih

    private static final Logger LOG = LoggerFactory.getLogger(SaucedemoStepDefinitions.class);// =to k cemu my priravnivaem peremennuiu
    //Logger ispolizuetsea dlea vyvedeniea oshibok==logger facotry dlea poluceniea ekzemplearov registratora
    //metod getLogger(SsD) vyzyvaetse v loggerFactory dlea poluceniea logov sceazannogo s klassom sausesd
    private static final String EXPECTED_ITEM_NAME = "Sauce Labs Backpack";//string
    private static final String EXPECTED_ITEM_DESCRIPTION =
            "carry.allTheThings() with the sleek, streamlined Sly Pack that " +
                    "melds uncompromising style with unequaled laptop and tablet protection.";//*dobavila svoistva peremennym

    private static final String EXPECTED_ITEM_PRICE = "$29.99";

    public SaucedemoStepDefinitions() {// konstruktor imeet takoe je nazvanie kak class
        this.webDriver = MyWebDriver.getDriver(); //initsializiruem v construktore.priravnivaem k kakoito funktsii kotoraea nahoditsea v hooks
        this.scenarioContext = ScenarioContext.getInstance();
        this.loginPage = Hooks.getLoginPage();// this.
        this.dashboardPage = Hooks.getDashboardPage();//hooks -class name
        this.cartPage = Hooks.getCartPage();
        this.checkOutYourInformationPage = Hooks.checkOutYourInformationPage();
        this.checkoutOverviewPage = Hooks.checkOutOverviewPage();
        this.checkoutCompletePage = Hooks.checkoutCompletePage();//*posle etogo sozdali konstruktor  kotoryi opredeleaet strukturu klassa
       //* prisvolili znacenie/povedenie peremenym
    }


    @Given("user is accessing the login page")//*sozdali methody,instruktsiea k povedeniiu dlea vypolneniea test stepov na baze bdd cucumber
    public void accessTheWebPage() {//*method sinim
        final String url = ApplicationConfig.getUrl("saucedemo");//*sozdali novuiu peremennuiu nujnuiu toliko etomu methodu"local variable)
        checkPage(url);
        LOG.info(format("User has accessed the login page - {%s}", url));
    }

    @When("user enters correct credentials {string} {string}")
    public void enterCorrectCredentials(String username, String password) {//method body
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        LOG.info("User entered the credentials");
    }

    @Then("user is successfully logged in")
    public void checkSuccessfulLogin() {
        final String url = ApplicationConfig.getUrl("inventory");//vyshli na stranoiciku
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
    public void checkPurchasedItems() {// dlea assertov ea ispolizovala hamcrest
        assertThat(EXPECTED_ITEM_NAME, is(cartPage.getItemName()));//* JUnit methods assertThat cto check if a specific value match to an expected one. It primarily accepts 2 parameters. First one if the actual value and the second is a matcher object.
        assertThat(EXPECTED_ITEM_DESCRIPTION, is(cartPage.getItemDescription()));
        assertThat(EXPECTED_ITEM_PRICE, is(cartPage.getItemPrice()));
        LOG.info("The items from the cart match with items added by user!");//actual- expected
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

    @Then("purchased item should be removed")//validatsiea hamcrest. assert==cito actual result raven expected result
    public void purchasedItemShouldBeRemoved() {
        Assertions.assertThrows(NoSuchElementException.class, () -> cartPage.getItemName());//get ishet element, nenahodit vykidyvaet exeption
        Assertions.assertThrows(NoSuchElementException.class, () -> cartPage.getItemDescription());
        Assertions.assertThrows(NoSuchElementException.class, () -> cartPage.getItemPrice());
        LOG.info("The items from the cart was removed!");//poiskati v google kak proveriti cito element iscez so stranitsy. Nevidimmosti elementa
    } //cito za strelocika???? lambda

    @When("user clicks on checkout button")
    public void userClicksOnCheckoutButton() {
        cartPage.clickCheckOutButton();
        LOG.info("User clicks on checkout button");

    }

    @And("enters credentials {string} {string} {string}")
    public void entersCredentials(String firstname, String lastname, String zipPostalCode) {
        checkOutYourInformationPage.enterFirstName(firstname);
        checkOutYourInformationPage.enterLastName(lastname);
        checkOutYourInformationPage.enterZipPostalCode(zipPostalCode);
        LOG.info("The credentials added");

    }

    @And("user clicks continue button")
    public void userClicksContinueButton() {
        checkOutYourInformationPage.clickContinueButton();
        LOG.info("User clicks on continue button");

    }


    @And("user clicks finish button")
    public void userClicksFinishButton() {
        checkoutOverviewPage.clickFinishButton();
        LOG.info("User clicks on finish button");

    }

    @Then("order is completed with following success message: {string}")
    public void orderIsCompletedWithFollowingSuccessMessage(String successMessage) {
        assertThat(successMessage, is(checkoutCompletePage.getSuccessMessage()));//sravnivaem cito hotim poluciti// cito sovpadaey s ojidaemym message
    }

    private void checkPage(String page) {
        webDriver.get(page);//cito url zagrujen do kontsa
        final WebDriverWait wait =
                new WebDriverWait(webDriver, Duration.ofSeconds(ApplicationConfig.getElementIsDisplayed()));
        wait.until(ExpectedConditions.urlToBe(page));// ty assertishi cito etot url takoi kak ea dala
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
