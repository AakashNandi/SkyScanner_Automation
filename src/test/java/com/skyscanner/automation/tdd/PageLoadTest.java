package com.skyscanner.automation.tdd;

import com.skyscanner.automation.basetest.BaseTest;
import org.testng.annotations.Test;

public class PageLoadTest extends BaseTest {
    @Test
    public  void VerifyBrowserLaunch(){
        System.out.println("Page Title = " + driver.getTitle());
    }
}

