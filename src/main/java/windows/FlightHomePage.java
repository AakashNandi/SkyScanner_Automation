package windows;

import base.BasePage;
import hooks.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.WaitUtils;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Thread.sleep;
import static org.openqa.selenium.By.xpath;

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

    }

    public void enterFrom(String from, String code_f ) {

        WaitUtils.dismissBottomPopupIfPresent(By.id("cookie_disclaimer"), By.id("cookie_stop"));

        fromInput.clear();
        fromInput.sendKeys(from);

       // By resultButtonLocator = By.xpath("//button[contains(normalize-space(),'" + code_f + "')]");
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

    public void enterTo(String to, String code_t ) {

            //WaitUtils.dismissBottomPopupIfPresent(By.id("cookie_disclaimer"), By.id("cookie_stop"));
            toInput.clear();
            toInput.sendKeys(to);

//            // wait until at least one result is visible
//            wait.waitForVisible(airportResultsContainer);
//            String dynamicXpath = String.format(".//button[normalize-space()='%s']", code_t);
//            WebElement select = airportResultsContainer.findElement(By.xpath(dynamicXpath));
//            wait.waitForClickable(select).click();
             By resultButtonLocator = By.xpath("//button[contains(normalize-space(),'" + code_t + "')]");
             WebElement select = WaitUtils.waitForPresence(resultButtonLocator);

            // Step 3: Wait for the button to be visible
            WaitUtils.waitForVisible(select);

            // Step 4: Wait for the button to be clickable
            WaitUtils.waitForClickable(select);

            // Step 5: Scroll the button into view to avoid being hidden by any popup/banner
            WaitUtils.scrollIntoViewCenter(select);

            // Step 6: Click the button safely
            select.click();

    }

//    private void dismissCookieBannerIfPresent() {
//        try {
//            WebElement cookieBanner = driver.findElement(By.id("cookie_disclaimer"));
//            if (cookieBanner.isDisplayed()) {
//                cookieBanner.findElement(By.tagName("button")).click();
//                // optional: wait until banner disappears
//                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//                wait.until(ExpectedConditions.invisibilityOf(cookieBanner));
//            }
//        } catch (NoSuchElementException ignored) {
//            // banner not present, safe to continue
//        }
//    }

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
       // wait.waitForClickable(departureDateBtn).click();

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