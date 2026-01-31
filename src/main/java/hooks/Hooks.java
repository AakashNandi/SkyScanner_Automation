package hooks;

import base.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.ConfigReader;

public class Hooks {

    public static WebDriver driver;

    @Before
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();

        String url = ConfigReader.getProperty("url");
        driver.get(url);
    }

    // Screenshot AFTER EVERY STEP (PASS + FAIL)
    @AfterStep
    public void takeScreenshot(Scenario scenario) {
        byte[] screenshot =
                ((TakesScreenshot) DriverFactory.getDriver())
                        .getScreenshotAs(OutputType.BYTES);

        scenario.attach(screenshot, "image/png", "Step Screenshot");
    }

    @After
    public void tearDown() {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("setTimeout(function(){}, 500);");
        DriverFactory.quitDriver();
    }
}
