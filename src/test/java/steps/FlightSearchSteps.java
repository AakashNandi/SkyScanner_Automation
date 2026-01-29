package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import windows.FlightHomePage;
import utilities.JsonUtil;
import hooks.Hooks;

public class FlightSearchSteps {

    FlightHomePage homePage;

    String t_type;
    String from;
    String code_f;
    String to;
    String code_t;
    String departureDate;
    String returnDate;
    String adults;
    String childs;
    String infants;
    String cabinClass;

    @Given("user is on flights site page")
    public void openSkyscanner() {
        homePage = new FlightHomePage(); // Driver comes from Hooks
        homePage.verifyHomeLoaded();
    }

    @And("user loads flight test data {string}")
    public void loadTestData(String tcId) {
        t_type = JsonUtil.getData(tcId,"type");
        from = JsonUtil.getData(tcId, "from");
        code_f = JsonUtil.getData(tcId, "code_f");
        to = JsonUtil.getData(tcId, "to");
        code_t = JsonUtil.getData(tcId, "code_t");
        departureDate = JsonUtil.getData(tcId, "departureDate");
        returnDate = JsonUtil.getData(tcId, "returnDate");
        adults = JsonUtil.getData(tcId, "adults");
        cabinClass = JsonUtil.getData(tcId, "cabinClass");
    }

    @When("user selects trip-type and trip class")
    public void selectOneWay() {
        homePage.selectTripType(t_type);
        homePage.selectClassType(cabinClass);

    }

    @And("user enters From and To locations")
    public void enterRoute() {
        homePage.enterFrom(from,code_f);
        homePage.enterTo(to,code_t);
    }

    @And("user selects Dates")
    public void selectDate() {
        if ("One way".equalsIgnoreCase(t_type)) {
            // Select only the departure date for one-way trip
            homePage.selectDepartureDate(departureDate);
        } else if ("Return".equalsIgnoreCase(t_type)) {
            // Select both departure and return dates for multi-way trip
            homePage.selectDates(departureDate, returnDate);
        }
    }

    @And("user selects travellers")
    public void selectPassengers() {
            homePage.selectTravellers(adults, childs, infants);
    }

    @And("user clicks search")
    public void clickSearch() {
        homePage.clickSearch();
    }

    @Then("flight results should be displayed")
    public void verifyResults() {
        Assert.assertTrue(Hooks.driver.getTitle().toLowerCase().contains("flights"));
    }
}

