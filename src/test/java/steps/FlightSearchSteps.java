package steps;

import core.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.FlightHomePage;
import core.JsonUtil;

public class FlightSearchSteps {

        WebDriver driver;
        FlightHomePage homePage;

        String from;
        String to;
        String departureDate;
        String adults;
        String cabinClass;

        @Given("user is on Skyscanner flights page")
        public void openSkyscanner() {
            driver = DriverFactory.getDriver();
            driver.get("https://www.skyscanner.co.in");
            homePage = new FlightHomePage(driver);
        }

        @And("user loads flight test data {string}")
        public void loadTestData(String tcId) {
            from = JsonUtil.getData(tcId, "from");
            to = JsonUtil.getData(tcId, "to");
            departureDate = JsonUtil.getData(tcId, "departureDate");
            adults = JsonUtil.getData(tcId, "adults");
            cabinClass = JsonUtil.getData(tcId, "cabinClass");
        }

        @When("user selects One-way trip")
        public void selectOneWay() {
            homePage.selectOneWay();
        }

        @And("user enters From and To locations")
        public void enterRoute() {
            homePage.enterFrom(from);
            homePage.enterTo(to);
        }

        @And("user selects departure date")
        public void selectDate() {
            homePage.selectDepartureDate(departureDate);
        }

        @And("user selects travellers and cabin class")
        public void selectPassengers() {
            // We'll enhance this later — keeping step stable for now
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
            Assert.assertTrue(driver.getTitle().toLowerCase().contains("flights"));
        }
}
