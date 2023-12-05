package com.my.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverview {

    private final WebDriver driver;

    private final By itemName = By.xpath("//div[@class='inventory_item_name']");
    private final By itemDescription = By.xpath("//div[@class='inventory_item_desc']");
    private final By itemPrice = By.xpath("//div[@class='inventory_item_price']");
    private final By finishButton = By.xpath("//button[@id='finish']");



    public CheckoutOverview (WebDriver driver) { this.driver = driver; } //konstruktor parametr

    public String getItemName() {
        return driver.findElement(itemName).getText();
    }

    public String getItemDescription() {
        return driver.findElement(itemDescription).getText();
    }//izvlekaem text

    public String getItemPrice() { return driver.findElement(itemPrice).getText(); }

    public void clickFinishButton(){
        driver.findElement(finishButton).click();
    }

}
