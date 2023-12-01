package com.my.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {

    private final WebDriver driver;

    private final By usernameInput = By.xpath("//input[@id='user-name']");//vzeati addres na stranitsu//locator
    private final By passwordInput = By.xpath("//input[@id='password']");
    private final By loginButton = By.xpath("//input[@value='Login']");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");//tag/atribute/nazvanie atributa/ errorMessage-peremennaea ctoby rabotalo nujno sozdati method

    public LoginPage (WebDriver driver) {
        this.driver = driver;
    }
    //konstrukto toje nazvanie kak u klasa i net return type

    public void enterUsername(String username) { driver.findElement(usernameInput).sendKeys(username); }//findel po etomu adresu

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() { return driver.findElement(errorMessage).getText(); }
}


