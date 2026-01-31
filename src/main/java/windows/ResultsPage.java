package windows;

import base.BasePage;
import base.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ResultsPage extends BasePage {

    private WebDriverWait wait;

    public ResultsPage() {
        super(DriverFactory.getDriver());
        wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(20));
    }

    // ================= LOCATORS =================

    // Combined text: "del cgy 03-02-2026"
    @FindBy(xpath = "//span[contains(@class,'title__fetched-time')]/small/strong")
    private WebElement routeText;

    // ================= PAGE METHODS =================

    private String[] getRouteParts() {
        String text = wait.until(ExpectedConditions.visibilityOf(routeText))
                .getText()
                .trim();                 // "del cgy 03-02-2026"

        return text.split("\\s+");                 // ["del", "cgy", "03-02-2026"]
    }

    public String getFromAirportCode() {
        return getRouteParts()[0].toUpperCase();   // DEL
    }

    public String getToAirportCode() {
        return getRouteParts()[1].toUpperCase();   // CGY
    }

    public String getJourneyDate() {
        return getRouteParts().length > 2 ? getRouteParts()[2] : "";
    }

    // ================= SANITY CHECK =================

    public boolean isResultsPageLoaded() {
        try {
            return routeText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}