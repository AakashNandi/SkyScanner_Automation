package windows;

import base.BasePage;
import hooks.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.WaitUtils;

import java.util.List;

public class FlightHomePage extends BasePage {

    private WaitUtils wait;

    public FlightHomePage() {
        super(Hooks.driver);
        wait = new WaitUtils(Hooks.driver);
    }

    // Trip type dropdown
    @FindBy(xpath = "//select[contains(@class,'flight_way')]")
    WebElement tripTypeDropdown;

    // Trip type options
    @FindBy(xpath = "//select[contains(@class,'flight_way')]/option")
    List<WebElement> tripTypeOptions;

    // class type dropdown
    @FindBy(xpath = "//select[@id='flight_type']")
    WebElement classtypeDropdown;

    // class type options
    @FindBy(xpath = "//select[@id='flight_type']/options")
    List<WebElement> classTypeOptions;


    // From input
    @FindBy(xpath = "//input[@name='from']")
    WebElement fromInput;

    // To input
    @FindBy(xpath = "//input[@name='to']]")
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

    // ========================= VERIFICATION ========================= //

    public void verifyHomeLoaded() {
        wait.waitForVisible(By.xpath("//button[@role='tab']"));
        System.out.println("✅ Flight Home Page Loaded Successfully");
    }

    // ========================= ACTION METHODS ========================= //

    public void selectTripType(String type) {
        wait.waitForClickable(By.xpath("//select[contains(@class,'flight_way')]")).click();

        for (WebElement option : tripTypeOptions) {
            String text = option.getText().trim();
            if (text.equalsIgnoreCase(type)) {
                option.click();
                break;
            }
        }
    }


    public void selectClassType(String cabinClass) {
        wait.waitForClickable(By.xpath("//select[@id='flight_type']")).click();

        for (WebElement option : classTypeOptions) {
            String text = option.getText().trim();
            if (text.equalsIgnoreCase(cabinClass)) {
                option.click();
                break;
            }
        }
    }

    public void enterFrom(String from) {
        wait.waitForVisible(By.xpath("//input[@name='from']")).clear();
        fromInput.sendKeys(from);

        By suggestion = By.xpath("//span[contains(text(),'" + from + "')]");
        wait.waitForClickable(suggestion).click();
    }


    public void enterTo(String to) {
        wait.waitForVisible(By.xpath("//input[@name='to']")).clear();
        toInput.sendKeys(to);

        By suggestion = By.xpath("//span[contains(text(),'" + to + "')]");
        wait.waitForClickable(suggestion).click();
    }

    public void selectDepartureDate(String date) {
        wait.waitForClickable(By.xpath("//button[contains(@data-testid,'depart')]")).click();

        By dateBtn = By.xpath("//button[@aria-label='" + date + "']");
        wait.waitForClickable(dateBtn).click();
    }

    public void selectTravellers(String adults) {
        wait.waitForClickable(By.xpath("//button[contains(@data-testid,'passengers')]")).click();

        int adultCount = Integer.parseInt(adults);
        By addAdultBtn = By.xpath("//button[@aria-label='Increase adults']");

        for (int i = 1; i < adultCount; i++) {
            wait.waitForClickable(addAdultBtn).click();
        }
    }

    public void selectCabinClass(String cabinClass) {
        By cabinDropdown = By.xpath("//select");
        wait.waitForClickable(cabinDropdown).click();

        By option = By.xpath("//option[contains(text(),'" + cabinClass + "')]");
        wait.waitForClickable(option).click();
    }

    public void untickAddHotel() {
        if (addHotelCheckbox.isSelected()) {
            addHotelCheckbox.click();
        }
    }

    public void clickSearch() {
        wait.waitForClickable(By.xpath("//button[contains(.,'Search')]")).click();
    }
}