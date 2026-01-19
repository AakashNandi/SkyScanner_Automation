package com.skyscanner.automation.tdd;

import com.skyscanner.automation.utils.ConfigReader;
import org.testng.annotations.Test;

public class ConfigurationTest {
    @Test
    public void VerifyConfigIsLoaded(){
        System.out.println("URL = " + ConfigReader.get("url"));
        System.out.println("Browser = " + ConfigReader.get("browser"));
        System.out.println("Explicit Wait = " + ConfigReader.get("explicit.wait"));
    }
}
