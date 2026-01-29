package windows;

import base.BasePage;
import hooks.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.WaitUtils;

import java.time.Duration;
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

  //  @FindBy(xpath = "//div[@id='onereturn']")
    @FindBy(xpath = "//div[@id='onereturn']/div[1]/div/div")
    WebElement airportResultsContainer;


    // To input
    @FindBy(xpath = "//input[@name='to']")
    WebElement toInput;

    // Departure date button
    @FindBy(id = "departure")
    WebElement departureDateele;

    // Return date button
    @FindBy(id = "return_date")
    WebElement returnDateele;

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

    }

    public void enterFrom(String from, String code_f) {
                WaitUtils.dismissBottomPopupIfPresent(By.id("cookie_disclaimer"), By.id("cookie_stop"));

                fromInput.clear();

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500)); // Set explicit wait time
                // Slow typing: Send each character with an explicit wait for each character typed
                for (char c : from.toCharArray()) {
                    fromInput.sendKeys(String.valueOf(c));  // Send one character at a time

                    // Wait for the input field value to update (waiting for it to change)
                    wait.until(new ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver driver) {
                            // Check if the value of the input field has changed (indicating a new character was typed)
                            String currentText = fromInput.getAttribute("value");
                            return currentText.length() > 0 && currentText.length() == fromInput.getAttribute("value").length();
                        }
                    });
                }

                By resultButtonLocator = By.xpath(String.format("//button[contains(text(),'%s')]", code_f));

                // Wait until button exists in DOM (presence)
                WebElement select = WaitUtils.waitForPresence(resultButtonLocator);

                // Wait until button is visible
                WaitUtils.waitForVisible(select);

                // Wait until button is clickable
                WaitUtils.waitForClickable(select);

                WaitUtils.scrollIntoViewCenter(select);

                select.click();

             }


    public void enterTo(String to, String code_t) {
        //WaitUtils.dismissBottomPopupIfPresent(By.id("cookie_disclaimer"), By.id("cookie_stop"));

        toInput.clear();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500)); // Set explicit wait time
        // Slow typing: Send each character with an explicit wait for each character typed
        for (char c : to.toCharArray()) {
            toInput.sendKeys(String.valueOf(c));  // Send one character at a time

            // Wait for the input field value to update (waiting for it to change)
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    // Check if the value of the input field has changed (indicating a new character was typed)
                    String currentText = toInput.getAttribute("value");
                    return currentText.length() > 0 && currentText.length() == toInput.getAttribute("value").length();
                }
            });
        }

        By resultButtonLocator = By.xpath(String.format("//button[contains(text(),'%s')]", code_t));

        // Wait until button exists in DOM (presence)
        WebElement select = WaitUtils.waitForPresence(resultButtonLocator);

        // Wait until button is visible
        WaitUtils.waitForVisible(select);

        // Wait until button is clickable
        WaitUtils.waitForClickable(select);

        WaitUtils.scrollIntoViewCenter(select);

        select.click();

    }
    public void selectDepartureDate(String departureDate) {
        WaitUtils.waitForClickable(departureDateele);
        departureDateele.clear();
        //departureDateele.sendKeys(departureDate);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500)); // Set explicit wait time
        // Slow typing: Send each character with an explicit wait for each character typed
        for (char c : departureDate.toCharArray()) {
            departureDateele.sendKeys(String.valueOf(c));  // Send one character at a time

            // Wait for the input field value to update (waiting for it to change)
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    // Check if the value of the input field has changed (indicating a new character was typed)
                    String currentText = departureDateele.getAttribute("value");
                    return currentText.length() > 0 && currentText.length() == departureDateele.getAttribute("value").length();
                }
            });
        }
    }

    public void selectDates(String departureDate, String returnDate) {
        // Select departure date
        WaitUtils.waitForClickable(departureDateele);
        departureDateele.clear();
        departureDateele.sendKeys(departureDate);

        // Select return date (for multi-way)
        WaitUtils.waitForClickable(returnDateele);
        returnDateele.clear();
        returnDateele.sendKeys(returnDate);

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