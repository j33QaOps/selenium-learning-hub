package com.learning.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // 1. Encapsulate Locators as private By objects
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    // 2. Constructor passing WebDriver instance
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // 3. Action Methods
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    // Convenient business workflow method
    public InventoryPage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new InventoryPage(driver); // Page Chaining pattern
    }

    public String getErrorMessageText() {
        return driver.findElement(errorMessage).getText();
    }
}