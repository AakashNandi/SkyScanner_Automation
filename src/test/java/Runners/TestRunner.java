package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

    @CucumberOptions(
            features = "src/test/resources/features",
            glue = {"steps", "hooks"},
            plugin = {"pretty","html:target/testng-html-report.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
            monochrome = true
    )
    public class TestRunner extends AbstractTestNGCucumberTests {
}

//,"json:target/cucumber.json"