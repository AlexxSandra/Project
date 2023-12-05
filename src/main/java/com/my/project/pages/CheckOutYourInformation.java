package com.my.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutYourInformation {

    private final WebDriver driver;

    private final By firstName = By.xpath("//input[@id='first-name']");
    private final By lastName = By.xpath("//input[@id='last-name']");
    private final By zipPostalCode = By.xpath("//input[@id='postal-code']");
    private final By continueButton = By.xpath("//input[@id='continue']");


    public CheckOutYourInformation(WebDriver driver) { this.driver = driver; } //konstruktor parametr

    public void enterFirstName(String firstname) { driver.findElement(firstName).sendKeys(firstname); }

    public void enterLastName(String lastname) { driver.findElement(lastName).sendKeys(lastname); }

    public void enterZipPostalCode(String zipPostalCode) {driver.findElement(this.zipPostalCode).sendKeys(zipPostalCode);}

    public void clickContinueButton(){
        driver.findElement(continueButton).click();
    }


}
