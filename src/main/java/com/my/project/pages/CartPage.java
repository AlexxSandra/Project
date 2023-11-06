package com.my.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private final WebDriver driver;

    private final By itemName = By.xpath("//div[@class='inventory_item_name']");
    private final By itemDescription = By.xpath("//div[@class='inventory_item_desc']");
    private final By itemPrice = By.xpath("//div[@class='inventory_item_price']");

    public CartPage (WebDriver driver) {
        this.driver = driver;
    }

    public String getItemName() {
        return driver.findElement(itemName).getText();
    }

    public String getItemDescription() {
        return driver.findElement(itemDescription).getText();
    }

    public String getItemPrice() {
        return driver.findElement(itemPrice).getText();
    }
}
