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

    // locators

    // Combined text in the format: "del cgy 03-02-2026"
    @FindBy(xpath = "//span[contains(@class,'title__fetched-time')]/small/strong")
    private WebElement routeText;


    private String[] getRouteParts() {
        String text = wait.until(ExpectedConditions.visibilityOf(routeText))
                .getText()
                .trim();                            // "del cgy 03-02-2026" removing white spaces

        return text.split("\\s+");           // ["del", "cgy", "03-02-2026"]
    }

    public String getFromAirportCode() {
        return getRouteParts()[0].toUpperCase();   //index 0 to uppercase
    }

    public String getToAirportCode() {
        return getRouteParts()[1].toUpperCase();   //index 1 to uppercase
    }

}