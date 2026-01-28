package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

import java.util.List;

public class FlightHomePage extends BasePage {

    private WaitUtils wait;

    public FlightHomePage(WebDriver driver) {
        super(driver);
        wait = new WaitUtils(driver);
    }

    // Trip type dropdown
    @FindBy(xpath = "//button[@title='Select trip type']")
    WebElement tripTypeDropdown;

    // Trip type options (li list)
    @FindBy(xpath = "//section[@role='dialog']/div/ul/li")
    List<WebElement> tripTypeOptions;

    // From input
    @FindBy(xpath = "//input[contains(@placeholder,'From')]")
    WebElement fromInput;

    // To input
    @FindBy(xpath = "//input[contains(@placeholder,'To')]")
    WebElement toInput;

    // Departure date button
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

    // ========================= ACTION METHODS ========================= //

    // Select trip type dynamically (One-way / Round-trip / Multi-city)
    public void selectTripType(String type) {
        wait.waitForClickable(
                By.xpath("//button[@title='Select trip type']")
        ).click();

        for (WebElement option : tripTypeOptions) {
            String text = option.getText().trim();

            if (text.equalsIgnoreCase(type)) {
                option.click();
                break;
            }
        }
    }

    // Enter FROM city dynamically
    public void enterFrom(String from) {
        wait.waitForVisible(
                By.xpath("//input[contains(@placeholder,'From')]")
        ).clear();

        fromInput.sendKeys(from);

        By suggestion = By.xpath("//span[contains(text(),'" + from + "')]");
        wait.waitForClickable(suggestion).click();
    }

    // Enter TO city dynamically
    public void enterTo(String to) {
        wait.waitForVisible(
                By.xpath("//input[contains(@placeholder,'To')]")
        ).clear();

        toInput.sendKeys(to);

        By suggestion = By.xpath("//span[contains(text(),'" + to + "')]");
        wait.waitForClickable(suggestion).click();
    }

    // Select departure date dynamically
    public void selectDepartureDate(String date) {
        wait.waitForClickable(
                By.xpath("//button[contains(@data-testid,'depart')]")
        ).click();

        By dateBtn = By.xpath("//button[@aria-label='" + date + "']");
        wait.waitForClickable(dateBtn).click();
    }

    // Select travellers dynamically (Adults)
    public void selectTravellers(String adults) {
        wait.waitForClickable(
                By.xpath("//button[contains(@data-testid,'passengers')]")
        ).click();

        int adultCount = Integer.parseInt(adults);

        By addAdultBtn = By.xpath("//button[@aria-label='Increase adults']");
        for (int i = 1; i < adultCount; i++) {
            wait.waitForClickable(addAdultBtn).click();
        }
    }

    // Select cabin class dynamically
    public void selectCabinClass(String cabinClass) {
        By cabinDropdown = By.xpath("//select");
        wait.waitForClickable(cabinDropdown).click();

        By option = By.xpath("//option[contains(text(),'" + cabinClass + "')]");
        wait.waitForClickable(option).click();
    }

    // Untick Add Hotel checkbox (always)
    public void untickAddHotel() {
        if (addHotelCheckbox.isSelected()) {
            addHotelCheckbox.click();
        }
    }

    // Click Search button
    public void clickSearch() {
        wait.waitForClickable(
                By.xpath("//button[contains(.,'Search')]")
        ).click();
    }
}