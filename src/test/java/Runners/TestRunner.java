package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

    @CucumberOptions(
            features = "src/test/resources/features",
            glue = {"steps", "hooks"},  //used to specify the locations of step definition and hook files.
            plugin = {"pretty","html:target/testng-html-report.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
            monochrome = true
    )
    public class TestRunner extends AbstractTestNGCucumberTests {   //base class provided by Cucumber to run tests with the TestNG framework.
}

