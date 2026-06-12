import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleTest {

    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
    }

    @Test(groups = "ui")
    public void verifyGoogleTitle() {
        Allure.step("Open google page");
        driver.get("https://www.google.com");

        Assert.assertTrue(
                driver.getTitle().contains("Google")
        );
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}