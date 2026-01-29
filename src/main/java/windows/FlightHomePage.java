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
    @FindBy(xpath = "//a[@data-toggle='dropdown']")
    WebElement travellersBtn;

    // Travellers dropdown
    @FindBy(xpath = "//div[contains(@class,'dropdown-menu')]/div")
    WebElement travellersdrop;

    @FindBy(xpath = "//div[contains(@class,'dropdown-menu')]/div[1]")
    WebElement adultSectionElement;

    @FindBy(xpath = "//div[contains(@class,'dropdown-menu')]/div[2]")
    WebElement childSectionElement;

    @FindBy(xpath = "//div[contains(@class,'dropdown-menu')]/div[3]")
    WebElement infantSectionElement;


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
        // Wait until the departure date field is clickable
        WaitUtils.waitForClickable(departureDateele);

        // Clear any existing value in the field
        departureDateele.clear();

        // Create an explicit wait for overall interaction (adjust wait time as needed)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Use a reasonable wait time (e.g., 5 seconds)

        // Slow typing: Send each character one by one
        for (char c : departureDate.toCharArray()) {
            // Send one character at a time
            departureDateele.sendKeys(String.valueOf(c));

            // Wait until the input field value has updated
            String finalDepartureDate = departureDate;
            wait.until(driver -> {
                // Get the current value in the input field
                String updatedValue = departureDateele.getAttribute("value");

                // Ensure the updated value is different from the previous value
                return updatedValue.length() > 0 && !updatedValue.equals(finalDepartureDate);
            });

            // Update the departureDate to the latest value after each keypress
            departureDate = departureDateele.getAttribute("value");
        }
    }



    public void selectDates(String departureDate, String returnDate) {
        // Select departure date
        selectDate(departureDateele, departureDate);

        // Select return date (for multi-way)
        selectDate(returnDateele, returnDate);
    }

    private void selectDate(WebElement dateElement, String date) {
        // Wait until the date field is clickable
        WaitUtils.waitForClickable(dateElement);

        // Clear any existing value in the field
        dateElement.clear();

        // Create an explicit wait for overall interaction (adjust wait time as needed)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Reasonable wait time

        // Slow typing: Send each character one by one
        for (char c : date.toCharArray()) {
            // Send one character at a time
            dateElement.sendKeys(String.valueOf(c));

            // Wait until the input field value has updated (field should have changed after typing a character)
            String finalDate = date;
            wait.until(driver -> {
                String updatedValue = dateElement.getAttribute("value");
                // Check if the updated value is different from the value we expect after each character is typed
                return !updatedValue.equals(finalDate);
            });

            // Update the date to the latest value after each keypress
            date = dateElement.getAttribute("value");
        }
    }



//    public void selectTravellers(String adults) {
//       // wait.waitForClickable(travellersBtn).click();
////
////        int adultCount = Integer.parseInt(adults);
////        By addAdultBtn = By.xpath("//button[@aria-label='Increase adults']");
//
////        for (int i = 1; i < adultCount; i++) {
////            wait.waitForClickable(addAdultBtn).click();
////        }
//        System.out.println("Hey");
//    }

public void selectTravellers(String adultCount, String childCount, String infantCount) {

    int aC = Integer.parseInt(adultCount);
    int cC = Integer.parseInt(childCount);
    int iC= Integer.parseInt(infantCount);
    // Step 1: Click on the travelers section to expand the dropdown
    WaitUtils.waitForClickable(travellersBtn);
    travellersBtn.click();

    WaitUtils.waitForVisible(travellersdrop);
    // Step 2: Adjust the adult count
    adjustCount(aC, adultSectionElement);  // You will need the WebElement for the adult section

    // Step 3: Adjust the child count
    adjustCount(cC, childSectionElement);  // You will need the WebElement for the child section

    // Step 4: Adjust the infant count
    adjustCount(iC, infantSectionElement);  // You will need the WebElement for the infant section
}

    private void adjustCount(int desiredCount, WebElement sectionElement) {
        // Wait for the section element to be clickable (if not already clicked)
        WaitUtils.waitForClickable(sectionElement);

        // Get the current count displayed for the section
        int currentCount = Integer.parseInt(sectionElement.getText());  // Adjust if the count is displayed in another way

        // If current count is less than the desired count, click "+" to increase
        while (currentCount < desiredCount) {
            WebElement incrementButton = sectionElement.findElement(By.xpath("//div[@class = 'qtyInc']"));  // Adjust for actual button selector
            incrementButton.click();
            currentCount++; // Increment the count after clicking
        }

//        // If current count is more than desired count, click "-" to decrease (optional, if needed)
//        while (currentCount > desiredCount) {
//            WebElement decrementButton = sectionElement.findElement(By.xpath("//*[contains(@class, 'decreaseButton')]"));  // Adjust for actual button selector
//            decrementButton.click();
//            currentCount--; // Decrement the count after clicking
//        }
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