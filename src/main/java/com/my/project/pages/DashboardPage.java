package com.my.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    private final WebDriver driver;

    private final By addToCartButton = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']");
    private final By cartIcon = By.xpath("//a[@class='shopping_cart_link']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnAddToCartButton() {
        driver.findElement(addToCartButton).click();
    }

    public void clickOnCartIcon () {
        driver.findElement(cartIcon).click();
    }
}
