package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class GoogleSteps {

    @Step("Open url: {url}")
    public void openPage(WebDriver driver, String url) {
        driver.get(url);
    }

    @Step("Verify title contains: {expected}")
    public void verifyTitle(WebDriver driver, String expected) {
        Assert.assertTrue(driver.getTitle().contains(expected));
    }
}
