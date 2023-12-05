package com.my.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private final WebDriver driver;

    private final By itemName = By.xpath("//div[@class='inventory_item_name']");
    private final By itemDescription = By.xpath("//div[@class='inventory_item_desc']");
    private final By itemPrice = By.xpath("//div[@class='inventory_item_price']");
    private final By removeButton = By.xpath("//button[@id='remove-sauce-labs-backpack']");
    private final By checkOutButton = By.xpath("//button[@id='checkout']");

    //TODO id selectors     private final By removeButton = By.id("remove-sauce-labs-backpack");
    public CartPage (WebDriver driver) {
        this.driver = driver;
    }

    public String getItemName() { return driver.findElement(itemName).getText();}

    public String getItemDescription() {
        return driver.findElement(itemDescription).getText();
    }//izvlekaem text

    public String getItemPrice() {
        return driver.findElement(itemPrice).getText();
    }

    public void clickRemoveButton(){
        driver.findElement(removeButton).click();
    }

    public void clickCheckOutButton() {driver.findElement(checkOutButton).click();}
}
