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
    String adults;
    String cabinClass;

    @Given("user is on Skyscanner flights page")
    public void openSkyscanner() {
        homePage = new FlightHomePage(); // Driver comes from Hooks
        homePage.verifyHomeLoaded();
    }

    @And("user loads flight test data {string}")
    public void loadTestData(String tcId) {
        t_type = JsonUtil.getData(tcId,"type");
        from = JsonUtil.getData(tcId, "from");
        to = JsonUtil.getData(tcId, "to");
        departureDate = JsonUtil.getData(tcId, "departureDate");
        adults = JsonUtil.getData(tcId, "adults");
        cabinClass = JsonUtil.getData(tcId, "cabinClass");
    }

    @When("user selects trip-type and class")
    public void selectOneWay() {
        homePage.selectTripType(t_type);
        homePage.selectClassType(cabinClass);

    }

    @And("user enters From and To locations")
    public void enterRoute() {
        homePage.enterFrom(from,code_f);
        homePage.enterTo(to,code_t);
    }

    @And("user selects departure date")
    public void selectDate() {
        homePage.selectDepartureDate(departureDate);
    }

    @And("user selects travellers and cabin class")
    public void selectPassengers() {
        System.out.println("Adults: " + adults + ", Cabin: " + cabinClass);
    }

    @And("user unticks Add hotel option")
    public void untickHotel() {
        homePage.untickAddHotel();
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

