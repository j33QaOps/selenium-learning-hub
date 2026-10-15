import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LocatorsPracticeTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // The Internet Herokuapp is a standard testbed for automation
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @Test
    public void testLoginLocators() {
        // 1. CSS Selector using ID
        WebElement usernameField = driver.findElement(By.cssSelector("#username"));
        usernameField.sendKeys("tomsmith");

        // 2. Relative XPath using attribute
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
        passwordField.sendKeys("SuperSecretPassword!");

        // 3. CSS Selector combining tag and class
        WebElement loginButton = driver.findElement(By.cssSelector("button.radius"));
        loginButton.click();

        // 4. XPath using contains() and class
        WebElement flashMessage = driver.findElement(By.xpath("//div[contains(@class, 'flash success')]"));

        Assert.assertTrue(flashMessage.getText().contains("You logged into a secure area!"),
                "Verification failed: success banner was not displayed.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}