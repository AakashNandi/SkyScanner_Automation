package hooks;

import base.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
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

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
