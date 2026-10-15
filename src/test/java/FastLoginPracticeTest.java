import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FastLoginPracticeTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // High-speed e-commerce automation testbed
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testSuccessfulLogin() {
        // 1. Enter Username (using fast CSS selector by ID)
        WebElement usernameField = driver.findElement(By.cssSelector("#user-name"));
        usernameField.sendKeys("standard_user");

        // 2. Enter Password (using ID locator)
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("secret_sauce");

        // 3. Click Login
        WebElement loginBtn = driver.findElement(By.cssSelector("#login-button"));
        loginBtn.click();

        // 4. Verification: Check page title header on products dashboard
        WebElement inventoryTitle = driver.findElement(By.cssSelector(".title"));
        Assert.assertEquals(inventoryTitle.getText(), "Products", "Login failed or inventory title mismatch!");
    }

    @Test
    public void testLockedOutUserLoginError() {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Verify the dynamic error message banner
        WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
        Assert.assertTrue(errorMessage.getText().contains("Sorry, this user has been locked out."),
                "Expected lockout error message was not displayed.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}