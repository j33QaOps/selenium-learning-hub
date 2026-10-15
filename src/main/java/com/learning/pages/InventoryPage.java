package com.learning.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private WebDriver driver;

    private By pageTitle = By.cssSelector(".title");
    private By inventoryItems = By.cssSelector(".inventory_item");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitleText() {
        return driver.findElement(pageTitle).getText();
    }

    public int getItemCount() {
        return driver.findElements(inventoryItems).size();
    }
}