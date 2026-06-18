package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import steps.GoogleSteps;
import utils.DriverManager;

@Epic("Web UI")
@Feature("Google Search")
@Story("Open Google Homepage")
public class GoogleTest extends BaseTest {

    private WebDriver driver;
    private final GoogleSteps googleSteps =
            new GoogleSteps();

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        DriverManager.setDriver(driver);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(groups = "ui")
    public void verifyGoogleTitle() {

        googleSteps.openPage(driver, "https://google.com");
        googleSteps.verifyTitle(driver, "Google");
        Allure.step(
                "Additional validation",
                () -> Assert.assertTrue(
                        driver.getCurrentUrl()
                                .contains("google")
                )
        );
    }

    @Test(groups = "ui")
    public void failedTest() {

        Allure.step("Intentional failure");

        Assert.assertEquals(
                "Google",
                "Facebook"
        );
    }

    @Test(groups = "ui")
    public void brokenTest() {
        throw new WebDriverException(
                "Grid connection timeout"
        );
    }

    @Test(groups = "ui")
    public void skippedTest() {
        throw new SkipException(
                "Environment unavailable"
        );
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        DriverManager.unload();
    }
}