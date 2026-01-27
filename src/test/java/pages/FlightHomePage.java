package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

public class FlightHomePage extends BasePage{

        private WaitUtils wait;

        public FlightHomePage(WebDriver driver) {
            super(driver);
            wait = new WaitUtils(driver);
        }

        // One-way trip
        @FindBy(xpath = "//button[contains(.,'One-way')]")   //text anywhere inside it contains One-way
        WebElement oneWayBtn;

        // From input
        @FindBy(xpath = "//input[contains(@placeholder,'From')]")
        WebElement fromInput;

        // To input
        @FindBy(xpath = "//input[contains(@placeholder,'To')]")
        WebElement toInput;

        // Departure date field
        @FindBy(xpath = "//button[contains(@data-testid,'depart')]")
        WebElement departureDateBtn;

        // Travellers button
        @FindBy(xpath = "//button[contains(@data-testid,'passengers')]")
        WebElement travellersBtn;

        // Add hotel checkbox
        @FindBy(xpath = "//input[@type='checkbox']")
        WebElement addHotelCheckbox;

        // Search button
        @FindBy(xpath = "//button[contains(.,'Search')]")
        WebElement searchBtn;

        public void selectOneWay() {
            oneWayBtn.click();
        }

        public void enterFrom(String from) {
            fromInput.clear();
            fromInput.sendKeys(from);

            By suggestion = By.xpath("//span[contains(text(),'" + from + "')]");
            wait.waitForClickable(suggestion).click();
        }

        public void enterTo(String to) {
            toInput.clear();
            toInput.sendKeys(to);

            By suggestion = By.xpath("//span[contains(text(),'" + to + "')]");
            wait.waitForClickable(suggestion).click();
        }

        public void selectDepartureDate(String date) {
            departureDateBtn.click();

            By dateBtn = By.xpath("//button[@aria-label='" + date + "']");
            wait.waitForClickable(dateBtn).click();
        }

        public void untickAddHotel() {
            if (addHotelCheckbox.isSelected()) {
                addHotelCheckbox.click();
            }
        }

        public void clickSearch() {
            searchBtn.click();
        }
}
