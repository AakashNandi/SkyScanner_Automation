package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

        private WebDriver driver;
        private WebDriverWait wait;

        public WaitUtils(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        public WebElement waitForVisible(WebElement locator) {
            return wait.until(ExpectedConditions.visibilityOf(locator));
        }

        public WebElement waitForClickable(WebElement locator) {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        }

        public void waitForTitle(String title) {
            wait.until(ExpectedConditions.titleContains(title));
        }
}
