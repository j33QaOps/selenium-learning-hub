package com.learning.tests;

import com.learning.pages.InventoryPage;
import com.learning.pages.LoginPage;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--disable-extensions", "--no-sandbox");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testValidLogin() {
        // Uses page chaining: logging in returns the next page object
        InventoryPage inventoryPage = loginPage.loginAs("standard_user", "secret_sauce");

        Assert.assertEquals(inventoryPage.getTitleText(), "Products");
        Assert.assertTrue(inventoryPage.getItemCount() > 0, "No inventory items displayed!");
    }

    @Test
    public void testLockedOutUser() {
        loginPage.loginAs("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.getErrorMessageText().contains("Sorry, this user has been locked out."));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}