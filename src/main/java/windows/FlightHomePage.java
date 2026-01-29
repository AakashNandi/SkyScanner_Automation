package windows;

import base.BasePage;
import hooks.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.WaitUtils;

import java.util.List;

public class FlightHomePage extends BasePage {

    private WaitUtils wait;

    public FlightHomePage() {
        super(Hooks.driver);
        wait = new WaitUtils(Hooks.driver);
    }

    //Homepage loaded
    @FindBy(xpath = "//button[@role='tab']")
    WebElement home;

    // Trip type dropdown
    @FindBy(xpath = "//select[contains(@class,'flight_way')]")
    WebElement tripTypeDropdown;

    // Trip type options
    @FindBy(xpath = "//select[contains(@class,'flight_way')]/option")
    List<WebElement> tripTypeOptions;

    // class type dropdown
    @FindBy(xpath = "//select[@id='flight_type']")
    WebElement classTypeDropdown;

    // class type options
    @FindBy(xpath = "//select[@id='flight_type']/option")
    List<WebElement> classTypeOptions;


    // From input
    @FindBy(xpath = "//input[@name='from']")
    WebElement fromInput;

    @FindBy(xpath = "//div[@id='onereturn']")
    WebElement airportResultsContainer;

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
        //wait.waitForVisible(By.xpath("//button[@role='tab']"));
        wait.waitForVisible(home);
        System.out.println("✅ Flight Home Page Loaded Successfully");
    }

    // ========================= ACTION METHODS ========================= //

    public void selectTripType(String type) {
        wait.waitForClickable(tripTypeDropdown).click();

        for (WebElement option : tripTypeOptions) {
            String text = option.getText().trim();
            if (text.equalsIgnoreCase(type)) {
                option.click();
                break;
            }
        }
    }


    public void selectClassType(String cabinClass) {
        wait.waitForClickable(classTypeDropdown).click();

        for (WebElement option : classTypeOptions) {
            String text = option.getText().trim();
            if (text.equalsIgnoreCase(cabinClass)) {
                option.click();
                break;
            }
        }

        //System.out.println("Executed");
    }

    public void enterFrom(String from, String code_f ) {
        fromInput.clear();
        fromInput.sendKeys(from);

        // wait until at least one result is visible
        wait.waitForVisible(airportResultsContainer);
        String dynamicXpath = String.format(".//button[normalize-space()='%s']",code_f);
        WebElement select = airportResultsContainer.findElement(By.xpath(dynamicXpath));
        wait.waitForClickable(select).click();

    }

    public void enterTo(String to, String code_t ) {
        toInput.clear();
        toInput.sendKeys(to);

        // wait until at least one result is visible
        wait.waitForVisible(airportResultsContainer);
        String dynamicXpath = String.format(".//button[normalize-space()='%s']",code_t);
        WebElement select = airportResultsContainer.findElement(By.xpath(dynamicXpath));
        wait.waitForClickable(select).click();

    }

//    public void enterFrom(String from) {
//        wait.waitForVisible(By.xpath("//input[@name='from']"));
//        fromInput.sendKeys(from);
//
//        By suggestion = By.xpath("//div[contains(@data-airport,'Airport')]");
//        wait.waitForVisible(suggestion);
//        fromInput.sendKeys(Keys.ENTER);
//    }
//
//
//    public void enterTo(String to, String code_t) {
//        wait.waitForVisible(By.xpath("//input[@name='to']")).clear();
//        toInput.sendKeys(to);
//        toInput.sendKeys(Keys.ENTER);
//
//        By suggestion = By.xpath("//div[contains(@class,'result-option')]");
//        wait.waitForVisible(suggestion);
//    }

    public void selectDepartureDate(String date) {
        wait.waitForClickable(departureDateBtn).click();

    }

    public void selectTravellers(String adults) {
       // wait.waitForClickable(travellersBtn).click();
//
//        int adultCount = Integer.parseInt(adults);
//        By addAdultBtn = By.xpath("//button[@aria-label='Increase adults']");

//        for (int i = 1; i < adultCount; i++) {
//            wait.waitForClickable(addAdultBtn).click();
//        }
        System.out.println("Hey");
    }

    public void selectCabinClass(String cabinClass) {
//        By cabinDropdown = By.xpath("//select");
//        wait.waitForClickable(cabinDropdown).click();
//
//        By option = By.xpath("//option[contains(text(),'" + cabinClass + "')]");
//        wait.waitForClickable(option).click();
        System.out.println("Hey");
    }

    public void untickAddHotel() {
        if (addHotelCheckbox.isSelected()) {
            addHotelCheckbox.click();
        }
    }

    public void clickSearch() {
       // wait.waitForClickable(By.xpath("//button[contains(.,'Search')]")).click();
        System.out.println("Hey");

    }
}