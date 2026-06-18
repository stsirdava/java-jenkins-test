package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Field;

public class AllureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Object testClass = result.getInstance();

        WebDriver driver = null;

        try {
            Field field = testClass.getClass().getDeclaredField("driver");
            field.setAccessible(true);
            driver = (WebDriver) field.get(testClass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (driver != null) {
            ScreenshotUtils.capture(driver);
        }
    }
}
