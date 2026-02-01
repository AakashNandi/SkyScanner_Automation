
package windows;

import base.BasePage;
import hooks.Hooks;
import org.openqa.selenium.*;
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

    // Homepage loaded
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
    @FindBy(xpath = "//a[@data-toggle='dropdown']")
    WebElement travellersBtn;

    @FindBy(xpath = "(//div[input[@id='fadults']])[1]//ancestor::div[contains(@class, 'qtyBtn')]//div[@class='qtyInc']")
    WebElement adultSectionElement;

    @FindBy(xpath = "(//div[input[@id='fchilds']])[1]//ancestor::div[contains(@class, 'qtyBtn')]//div[@class='qtyInc']")
    WebElement childSectionElement;

    @FindBy(xpath = "(//div[input[@id='finfant']])[1]//ancestor::div[contains(@class, 'qtyBtn')]//div[@class='qtyInc']")
    WebElement infantSectionElement;

    // Search button
    @FindBy(xpath = "//button[@id='flights-search']")
    WebElement searchBtn;

    // Verification.....................

    public void verifyHomeLoaded() {
        wait.waitForVisible(home);
        System.out.println("Flight Home Page Loaded Successfully");
    }

    // Action methods...................

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
        fromInput.clear();
        slowType(fromInput, from);

        By resultButtonLocator = By.xpath(String.format("//button[contains(text(),'%s')]", code_f));
        WebElement select = WaitUtils.waitForPresence(resultButtonLocator);
        WaitUtils.waitForVisible(select);
        WaitUtils.waitForClickable(select);
        WaitUtils.scrollIntoViewCenter(select);
        select.click();
    }

    public void enterTo(String to, String code_t) {
        toInput.clear();
        slowType(toInput, to);

        By resultButtonLocator = By.xpath(String.format("//button[contains(text(),'%s')]", code_t));
        WebElement select = WaitUtils.waitForPresence(resultButtonLocator);
        WaitUtils.waitForVisible(select);
        WaitUtils.waitForClickable(select);
        WaitUtils.scrollIntoViewCenter(select);
        select.click();
    }

    public void selectDepartureDate(String departureDate) {
        WaitUtils.waitForClickable(departureDateele);
        departureDateele.clear();
        slowType(departureDateele, departureDate);
    }

    public void selectDates(String departureDate, String returnDate) {
        // Select departure date
        selectDate(departureDateele, departureDate);

        // Select return date (for multi-way)
        selectDate(returnDateele, returnDate);
    }

    private void selectDate(WebElement dateElement, String date) {
        WaitUtils.waitForClickable(dateElement);
        dateElement.clear();
        slowType(dateElement, date);
    }

    private void slowType(WebElement inputElement, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));

        // Send one character at a time with an explicit wait in between
        for (char c : text.toCharArray()) {
            inputElement.sendKeys(String.valueOf(c));

            // Wait for the input field to update (waiting for it to change)
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    String currentText = inputElement.getAttribute("value");
                    return currentText.length() > 0 && currentText.length() == inputElement.getAttribute("value").length();
                }
            });
        }
    }

    public void selectTravellers(String adultCount, String childCount, String infantCount) {
        int aC = Integer.parseInt(adultCount);
        int cC = Integer.parseInt(childCount);
        int iC = Integer.parseInt(infantCount);

        WaitUtils.waitForClickable(travellersBtn);
        travellersBtn.click();

        // Adjust the adult count
        WaitUtils.waitForClickable(adultSectionElement);
        adjustCount(aC, adultSectionElement);

        WaitUtils.waitForClickable(childSectionElement);
        // Adjust the child count
        adjustCount(cC, childSectionElement);

        WaitUtils.waitForClickable(infantSectionElement);
        // Adjust the infant count
        adjustCount(iC, infantSectionElement);
    }

    private void adjustCount(int targetCount, WebElement sectionElement) {
        int defaultCount = 0; // Default for child and infant
        if (sectionElement.equals(adultSectionElement)) {
            defaultCount = 1; // Default for adult is 1
        }

        // Calculate the number of clicks needed
        int clicksRequired = targetCount - defaultCount;

        // If no clicks are needed, return early
        if (clicksRequired <= 0) return;

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        for (int i = 0; i < clicksRequired; i++) {
            WaitUtils.waitForClickable(sectionElement);
            jsExecutor.executeScript("arguments[0].click();", sectionElement);
            jsExecutor.executeScript("setTimeout(function(){}, 500);"); // Explicit wait for 500ms
            WaitUtils.waitForClickable(sectionElement); // Ensure the state of the element has changed before the next click
        }
    }

    public void clickSearch() {
        WaitUtils.waitForClickable(searchBtn);
        searchBtn.click();
    }
}
