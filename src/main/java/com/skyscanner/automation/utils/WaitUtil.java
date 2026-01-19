package com.skyscanner.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {

    //This must exists
    private static WebDriverWait getWait(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(ConfigReader.get("explicit.wait"))));
    }
    //wait until element is visible
    public static WebElement waitUntilVisible(WebDriver driver, WebElement element){
        return getWait(driver).until(ExpectedConditions.visibilityOf(element));
    }
}
