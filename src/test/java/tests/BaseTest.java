package tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import utils.CategoriesWriter;
import utils.DriverManager;
import utils.EnvironmentWriter;
import utils.ScreenshotUtils;

public class BaseTest {

    private final EnvironmentWriter env = new EnvironmentWriter();
    private final CategoriesWriter cat = new CategoriesWriter();

    @BeforeSuite(alwaysRun = true)
    public void setupAllureMetadata() throws Exception {
        env.createEnvironmentFile();
        cat.createCategories();
    }

    @AfterMethod(alwaysRun = true)
    public void captureScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtils.capture(DriverManager.getDriver());
        }
    }
}