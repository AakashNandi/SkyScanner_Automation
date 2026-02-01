package steps;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import utilities.JsonUtil;
import windows.ResultsPage;

public class ResultSteps {

    @Then("Compare flight results using test data {string}")
    public void flight_results(String tcId) {

        ResultsPage resultsPage = new ResultsPage();

        //Expected data from JSON
        String expectedFromCode = JsonUtil.getData(tcId, "code_f").toUpperCase();
        String expectedToCode   = JsonUtil.getData(tcId, "code_t").toUpperCase();

        // Actual data from UI
        String actualFromCode = resultsPage.getFromAirportCode();
        String actualToCode   = resultsPage.getToAirportCode();

        // Assertions
        Assert.assertEquals(
                actualFromCode,
                expectedFromCode,
                "Source airport code mismatch"
        );

        Assert.assertEquals(
                actualToCode,
                expectedToCode,
                "Destination airport code mismatch"
        );
    }
}