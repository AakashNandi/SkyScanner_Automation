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

//    // Travellers dropdown
//    @FindBy(xpath = "//div[contains(@class,'dropdown')]/div")
//    WebElement travellersdrop;

//    @FindBy(xpath = "//*[@id='fadults']")
//    WebElement adultCountTextLocator;

    @FindBy(xpath = "(//div[input[@id='fadults']])[1]//ancestor::div[contains(@class, 'qtyBtn')]//div[@class='qtyInc']")
    WebElement adultSectionElement;

//    @FindBy(xpath = "//*[@id='fchilds']")
//    WebElement childCountTextLocator;

    @FindBy(xpath = "(//div[input[@id='fchilds']])[1]//ancestor::div[contains(@class, 'qtyBtn')]//div[@class='qtyInc']")
    WebElement childSectionElement;

//    @FindBy(xpath = "//*[@id='finfant']")
//    WebElement infantCountTextLocator;

    @FindBy(xpath = "(//div[input[@id='finfant']])[1]//ancestor::div[contains(@class, 'qtyBtn')]//div[@class='qtyInc']")
    WebElement infantSectionElement;

    // Search button
    @FindBy(xpath = "//button[@id='flights-search']")
    WebElement searchBtn;

    // ========================= VERIFICATION ========================= //

    public void verifyHomeLoaded() {
        //wait.waitForVisible(By.xpath("//button[@role='tab']"));
        //WaitUtils.dismissBottomPopupIfPresent(By.id("cookie_disclaimer"), By.id("cookie_stop"));
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50)); // Use a reasonable wait time (e.g., 5 seconds)

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50)); // Reasonable wait time

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

//    public void selectTravellers(String adultCount, String childCount, String infantCount) {
//
//        int aC = Integer.parseInt(adultCount);
//        int cC = Integer.parseInt(childCount);
//        int iC = Integer.parseInt(infantCount);
//
//        // Step 1: Click on the travelers section to expand the dropdown
//        WaitUtils.waitForClickable(travellersBtn);
//        travellersBtn.click();
//
//        WaitUtils.waitForVisible(travellersdrop);
//
//        // Step 2: Adjust the adult count
//        adjustCount(aC, adultSectionElement, adultCountTextLocator);  // Use WebElement for adult count text
//
//        // Step 3: Adjust the child count
//        adjustCount(cC, childSectionElement, childCountTextLocator);  // Use WebElement for child count text
//
//        // Step 4: Adjust the infant count
//        adjustCount(iC, infantSectionElement, infantCountTextLocator);  // Use WebElement for infant count text
//    }
//    private void adjustCount(int desiredCount, WebElement sectionElement, WebElement countTextElement) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        // Wait for the section element to be clickable
//        wait.until(ExpectedConditions.elementToBeClickable(sectionElement));
//
//        int currentCount = getCurrentCount(countTextElement);  // Get the initial displayed count
//        System.out.println("Initial Count: " + currentCount);  // Debugging
//
//        // If current count is less than the desired count, click "+" to increase
//        while (currentCount < desiredCount) {
//            try {
//                // Re-locate the increment button each time (in case the element becomes stale)
//                WebElement incrementButton = sectionElement.findElement(By.xpath("//div[@class = 'qtyInc']"));
//
//                // Ensure the increment button is clickable
//                wait.until(ExpectedConditions.elementToBeClickable(incrementButton));
//
//                // Click to increment count
//                incrementButton.click();
//
//                // Wait for the count text to be updated
//                int finalCurrentCount = currentCount;
//                wait.until(new ExpectedCondition<Boolean>() {
//                    public Boolean apply(WebDriver driver) {
//                        int updatedCount = getCurrentCount(countTextElement);
//                        return updatedCount > finalCurrentCount;  // Continue until the count has increased
//                    }
//                });
//
//                // After waiting for count to update, retrieve the updated count
//                currentCount = getCurrentCount(countTextElement);
//                System.out.println("Updated Count: " + currentCount);  // Debugging
//
//                // Ensure the updated count matches the desired count after the click
//                if (currentCount >= desiredCount) {
//                    break;
//                }
//
//            } catch (StaleElementReferenceException e) {
//                // If the element becomes stale, re-locate and retry
//                System.out.println("Stale element detected, re-locating...");
//                sectionElement = wait.until(ExpectedConditions.elementToBeClickable(sectionElement));  // Re-locate section element
//            } catch (Exception e) {
//                // Add generic exception handling to prevent infinite loop
//                System.out.println("An error occurred: " + e.getMessage());
//                break;  // Exit the loop on any other error
//            }
//        }
//
//        System.out.println("Final Count: " + currentCount);  // Debugging
//    }
//
//
//    // Helper method to extract current count from the displayed text
//    private int getCurrentCount(WebElement countTextElement) {
//        String countText = countTextElement.getText();
//        try {
//            return Integer.parseInt(countText.trim());  // Extract the count from text and parse it
//        } catch (NumberFormatException e) {
//            // Return 0 if the text isn't a valid number (fallback)
//            return 0;
//        }
//    }
public void selectTravellers(String adultCount, String childCount, String infantCount) {

    int aC = Integer.parseInt(adultCount);
    int cC = Integer.parseInt(childCount);
    int iC = Integer.parseInt(infantCount);

    // Step 1: Click on the travelers section to expand the dropdown
    WaitUtils.waitForClickable(travellersBtn);
    travellersBtn.click();

   // WaitUtils.waitForVisible(travellersdrop);

    // Step 2: Adjust the adult count
    WaitUtils.waitForClickable(adultSectionElement);
    adjustCount(aC, adultSectionElement);// Use WebElement for adult count text

    WaitUtils.waitForClickable(childSectionElement);
    // Step 3: Adjust the child count
    adjustCount(cC, childSectionElement);  // Use WebElement for child count text

    WaitUtils.waitForClickable(infantSectionElement);
    // Step 4: Adjust the infant count
    adjustCount(iC, infantSectionElement);  // Use WebElement for infant count text
}

    private void adjustCount(int targetCount, WebElement sectionElement) {
        // Default value based on the section element
        int defaultCount = 0; // Default for child and infant
        if (sectionElement.equals(adultSectionElement)) {
            defaultCount = 1; // Default for adult is 1
        }

        // Calculate the number of clicks needed
        int clicksRequired = targetCount - defaultCount;

        // If no clicks are needed, return early
        if (clicksRequired <= 0) return;


        // Wait until the increment button is clickable
        //WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(10)); // 10 seconds max wait
//        WebElement incrementButton = sectionElement.findElement(By.cssSelector(".increment-button")); // Adjust this selector

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        for (int i = 0; i < clicksRequired; i++) {
            // Wait for the increment button to become clickable before clicking
            //wait.until(ExpectedConditions.elementToBeClickable(incrementButton));
            WaitUtils.waitForClickable(sectionElement);
            // Click the increment button
          //  sectionElement.click();
            jsExecutor.executeScript("arguments[0].click();", sectionElement);
            //WaitUtils.waitForClickable(sectionElement);

            jsExecutor.executeScript("setTimeout(function(){}, 500);");
            // Explicit wait for a minimal delay (500ms)
            WaitUtils.waitForClickable(sectionElement); // Ensure the state of the element has changed before next click
        }
    }

    public void clickSearch() {
        WaitUtils.waitForClickable(searchBtn);
        searchBtn.click();
    }


//    private void adjustCount(int desiredCount, WebElement sectionElement, WebElement countTextElement) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
////
////        // Wait for the section element to be clickable
////        wait.until(ExpectedConditions.elementToBeClickable(sectionElement));
//        WaitUtils.waitForVisible(sectionElement);
//
//        int currentCount = getCurrentCount(countTextElement);  // Get the initial displayed count
//        System.out.println("Initial Count: " + currentCount);  // Debugging
//
//        // If current count is less than the desired count, click "+" to increase
//        while (currentCount < desiredCount) {
//            try {
//                // Re-locate the increment button each time (in case the element becomes stale)
//
//
//                // Ensure the increment button is clickable
//                //wait.until(ExpectedConditions.elementToBeClickable(sectionElement));
//                WaitUtils.waitForClickable(sectionElement);
//
//                // Click to increment count
//                sectionElement.click();
//
//                // Wait for the count text to be updated
//                int finalCurrentCount = currentCount;
//                wait.until(new ExpectedCondition<Boolean>() {
//                    public Boolean apply(WebDriver driver) {
//                        int updatedCount = getCurrentCount(countTextElement);
//                        return updatedCount > finalCurrentCount;  // Continue until the count has increased
//                    }
//                });
//
//                // After waiting for count to update, retrieve the updated count
//                currentCount = getCurrentCount(countTextElement);
//                System.out.println("Updated Count: " + currentCount);  // Debugging
//
//                // Ensure the updated count matches the desired count after the click
//                if (currentCount >= desiredCount) {
//                    break;
//                }
//
//            } catch (StaleElementReferenceException e) {
//                // If the element becomes stale, re-locate and retry
//                System.out.println("Stale element detected, re-locating...");
//                sectionElement = wait.until(ExpectedConditions.elementToBeClickable(sectionElement));  // Re-locate section element
//            } catch (Exception e) {
//                // Add generic exception handling to prevent infinite loop
//                System.out.println("An error occurred: " + e.getMessage());
//                break;  // Exit the loop on any other error
//            }
//        }
//
//        System.out.println("Final Count: " + currentCount);  // Debugging
//    }

//private void adjustCount(int desiredCount, WebElement sectionElement) {
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//
//    // Get the current count (assuming it's updated in the DOM after each click)
//    int currentCount = getCurrentCount(sectionElement);
//    System.out.println("Initial Count: " + currentCount);  // Debugging
//
//    // If the current count is already equal to the desired count, no need to click
//    if (currentCount == desiredCount) {
//        System.out.println("Current count is already " + currentCount + ". No clicks needed.");
//        return;
//    }
//
//    // We need to click the section element to increment the count
//    while (currentCount < desiredCount) {
//        try {
//            // Wait for the section element to be clickable (ensures that we're not missing clicks)
//            WaitUtils.waitForClickable(sectionElement);
//
//            // Click to increment count
//            sectionElement.click();
//
//            // Wait for the count to update (ensures the count is updated in the DOM after click)
//            int finalCurrentCount = currentCount;
//            WebElement finalSectionElement = sectionElement;
//            wait.until(new ExpectedCondition<Boolean>() {
//                public Boolean apply(WebDriver driver) {
//                    int updatedCount = getCurrentCount(finalSectionElement);
//                    return updatedCount > finalCurrentCount;  // Ensure the count has increased
//                }
//            });
//
//            // After the count has been updated, retrieve the new count
//            currentCount = getCurrentCount(sectionElement);
//            System.out.println("Updated Count: " + currentCount);  // Debugging
//
//            // If the count is now equal to or greater than the desired count, exit the loop
//            if (currentCount >= desiredCount) {
//                break;
//            }
//
//        } catch (StaleElementReferenceException e) {
//            // If the element becomes stale, re-locate and retry
//            System.out.println("Stale element detected, re-locating...");
//            sectionElement = wait.until(ExpectedConditions.elementToBeClickable(sectionElement));  // Re-locate section element
//        } catch (Exception e) {
//            // Add generic exception handling to prevent infinite loop
//            System.out.println("An error occurred: " + e.getMessage());
//            break;  // Exit the loop on any other error
//        }
//    }
//
//    System.out.println("Final Count: " + currentCount);  // Debugging
//}
//
//    // Helper method to extract current count from the displayed text
//    private int getCurrentCount(WebElement sectionElement) {
//        try {
//            // Fetch the count from the element's text (assuming it's updated after clicks)
//            String countText = sectionElement.getText();
//            System.out.println("Text from countTextElement: " + countText);  // Debugging output
//            return Integer.parseInt(countText.trim());  // Convert to integer
//        } catch (NumberFormatException e) {
//            // In case the value is not a valid number, return 0 as fallback
//            System.out.println("Failed to parse count from text: " + sectionElement.getText());
//            return 0;
//        }
//    }


//private void adjustCount(int desiredCount, WebElement sectionElement, WebElement countTextElement) {
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//
//    // Wait for the section element to be visible
//    WaitUtils.waitForVisible(sectionElement);
//
//    int currentCount = getCurrentCount(countTextElement);  // Get the initial displayed count
//    System.out.println("Initial Count: " + currentCount);  // Debugging
//
//    // If current count is already equal to the desired count, no need to click
//    if (currentCount == desiredCount) {
//        System.out.println("Current count is already " + currentCount + ", no need to increment.");
//        return;  // Exit early if no changes are needed
//    }
//
//    // If current count is less than the desired count, click to increment
//    while (currentCount < desiredCount) {
//        try {
//            // Wait for the section element to be clickable
//            WaitUtils.waitForClickable(sectionElement);
//
//            // Click the section element (this is the element that increments the count)
//            sectionElement.click();
//
//            // Wait for the count text to be updated
//            int finalCurrentCount = currentCount;
//            wait.until(new ExpectedCondition<Boolean>() {
//                public Boolean apply(WebDriver driver) {
//                    int updatedCount = getCurrentCount(countTextElement);
//                    return updatedCount > finalCurrentCount;  // Wait until the count increases
//                }
//            });
//
//            // After waiting for the count to be updated, retrieve the updated count
//            currentCount = getCurrentCount(countTextElement);
//            System.out.println("Updated Count: " + currentCount);  // Debugging
//
//            // If the count is now equal to or greater than the desired count, exit the loop
//            if (currentCount >= desiredCount) {
//                break;
//            }
//
//        } catch (StaleElementReferenceException e) {
//            // If the element becomes stale, re-locate and retry
//            System.out.println("Stale element detected, re-locating...");
//            sectionElement = wait.until(ExpectedConditions.elementToBeClickable(sectionElement));  // Re-locate section element
//        } catch (Exception e) {
//            // Add generic exception handling to prevent infinite loop
//            System.out.println("An error occurred: " + e.getMessage());
//            break;  // Exit the loop on any other error
//        }
//    }
//
//    System.out.println("Final Count: " + currentCount);  // Debugging
//}


    // Helper method to extract current count from the displayed text
//    private int getCurrentCount(WebElement countTextElement) {
//        String countText = countTextElement.getText();
//        try {
//            return Integer.parseInt(countText.trim());  // Extract the count from text and parse it
//        } catch (NumberFormatException e) {
//            // Return 0 if the text isn't a valid number (fallback)
//            return 0;
//        }
//    }

//    private int getCurrentCount(WebElement countTextElement) {
//        try {
//            // Get the style attribute of the element
//            String style = countTextElement.getAttribute("style");
//            System.out.println("Style attribute: " + style);  // Debugging output
//
//            // Extract the number from the style attribute using a regex or string manipulation
//            if (style != null && style.contains("count:")) {
//                // Extract the count value using regex
//                String countString = style.split("count:")[1].trim().replace(";", "");
//                return Integer.parseInt(countString);
//            }
//        } catch (Exception e) {
//            // Catch any exceptions and log for debugging
//            System.out.println("Error extracting count from style: " + e.getMessage());
//        }
//
//        // If no valid count is found, return 0
//        return 0;
//    }


}