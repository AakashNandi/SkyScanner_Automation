package steps;

import base.DriverFactory;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import utilities.JsonUtil;
import windows.ResultsPage;

public class ResultSteps {

        @Then("Compare flight results using test data {String}")
        public void flight_results(String tcId) {

            ResultsPage resultsPage = new ResultsPage();

            String expectedFromCode = JsonUtil.getData(tcId, "Code_f");
            String expectedToCode = JsonUtil.getData(tcId, "Code_t");

            String actualFromCode = resultsPage.getFromAirportCode();
            String actualToCode = resultsPage.getToAirportCode();

            Assert.assertEquals(actualFromCode, expectedFromCode,
                    "Source airport code mismatch");

            Assert.assertEquals(actualToCode, expectedToCode,
                    "Destination airport code mismatch");
        }
}
