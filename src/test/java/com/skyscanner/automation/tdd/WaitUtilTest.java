package com.skyscanner.automation.tdd;

import com.skyscanner.automation.basetest.BaseTest;
import com.skyscanner.automation.utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class WaitUtilTest extends BaseTest {
    @Test
    public void WaitForVisibility(){
        WebElement key = driver.findElement(By.xpath("//*[@id='originInput-input']"));
        WaitUtil.waitUntilVisible(driver,key).click();
    }
}
