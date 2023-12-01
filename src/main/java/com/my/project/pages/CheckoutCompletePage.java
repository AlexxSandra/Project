package com.my.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    private final WebDriver driver;

    private final By tittle = By.xpath("//span[@class='title']");
    private final By successMessage = By.xpath("//h2[@class='complete-header']");


    public CheckoutCompletePage (WebDriver driver) { this.driver = driver; }


    public String getTittle() { return driver.findElement(tittle).getText();}

    public String getSuccessMessage() { return driver.findElement(successMessage).getText(); }

}
