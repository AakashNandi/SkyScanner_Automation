package com.skyscanner.automation.basetest;

import com.skyscanner.automation.DriverFactory.DriverFactory;
import com.skyscanner.automation.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = DriverFactory.InitDriver();
        driver.get(ConfigReader.get("url"));
    }

    @AfterMethod
    public void tearDown(){
        DriverFactory.quitDriver(driver);
    }
}
