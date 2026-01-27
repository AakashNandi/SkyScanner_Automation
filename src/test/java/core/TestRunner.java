package core;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

    @CucumberOptions(
            features = "src/test/resources/features",
            glue = {"steps", "core"},
            plugin = {"pretty"},
            monochrome = true
    )
    public class TestRunner extends AbstractTestNGCucumberTests {
}
