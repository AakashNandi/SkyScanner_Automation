package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

// Initializes WebDriver and uses PageFactory to initialize web elements for the page object

public class BasePage {
        protected WebDriver driver;

        public BasePage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }
}
