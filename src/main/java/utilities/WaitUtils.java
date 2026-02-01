package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

//Implementing different wait methods

public class WaitUtils {

        private static WebDriver driver;
        private static WebDriverWait wait;

        public WaitUtils(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        public static WebElement waitForVisible(WebElement locator) {
            return wait.until(ExpectedConditions.visibilityOf(locator));
        }

        public static Boolean waitForInVisible(WebElement locator) {
        return wait.until(ExpectedConditions.invisibilityOf(locator));
        }

        public static WebElement waitForClickable(WebElement locator) {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        }

       public static WebElement waitForPresence(By locator) {
           return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
       }

       public static void dismissBottomPopupIfPresent(By popupLocator, By closeButtonLocator) {
        try {
            WebElement popup = driver.findElement(popupLocator);
            if (popup.isDisplayed()) {
                popup.findElement(closeButtonLocator).click();

                // Wait until the popup disappears
                wait.until(ExpectedConditions.invisibilityOf(popup));
            }
        } catch (NoSuchElementException e) {
            //System.out.println("Popup not present, safe to continue");
        }
       }

      public static void scrollIntoViewCenter(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
      }

      public void waitForTitle(String title) {
            wait.until(ExpectedConditions.titleContains(title));
        }
}
