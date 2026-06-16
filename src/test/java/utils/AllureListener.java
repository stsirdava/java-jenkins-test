package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Object testClass = result.getInstance();

        WebDriver driver = null;

        try {
            driver = (WebDriver) testClass.getClass().getDeclaredField("driver").get(testClass);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (driver != null) {
            ScreenshotUtils.capture(driver);
        }
    }
}
