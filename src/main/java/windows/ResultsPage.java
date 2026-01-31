package windows;

import base.BasePage;
import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.WaitUtils;

public class ResultsPage extends BasePage {

        private WaitUtils wait;

        public ResultsPage() {
            super(DriverFactory.getDriver());
            wait = new WaitUtils(DriverFactory.getDriver());
        }

        // ================= LOCATORS =================

        // Source airport code (example: DEL)
        @FindBy(xpath = "//span[contains(@class,'from') or contains(text(),'DEL')]")
        WebElement fromAirportCode;

        // Destination airport code (example: BOM)
        @FindBy(xpath = "//span[contains(@class,'to') or contains(text(),'BOM')]")
        WebElement toAirportCode;

        // ================= PAGE METHODS =================

        public String getFromAirportCode() {
            return wait.waitForVisible(fromAirportCode)
                    .getText()
                    .trim();
        }

        public String getToAirportCode() {
            return wait.waitForVisible(toAirportCode)
                    .getText()
                    .trim();
        }

        // Optional: sanity check for dummy site
        public boolean isResultsPageLoaded() {
            try {
                return fromAirportCode.isDisplayed() || toAirportCode.isDisplayed();
            } catch (Exception e) {
                return false;
            }
        }
}
