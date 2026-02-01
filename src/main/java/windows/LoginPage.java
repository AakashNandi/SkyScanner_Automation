package windows;


import base.BasePage;
import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;
import utilities.ConfigReader;
import utilities.WaitUtils;

public class LoginPage extends BasePage {

        private WaitUtils wait;

    public LoginPage() {
        // Calls the parent constructor to initialize WebDriver for common browser interactions in BasePage
        super(DriverFactory.getDriver());

        // Initializes WaitUtils to handle waiting logic for elements (visibility, clickability) with the same WebDriver
        wait = new WaitUtils(DriverFactory.getDriver());
    }


    // locators

        @FindBy(id = "email")
        WebElement emailInput;

        @FindBy(id = "password")
        WebElement passwordInput;

        @FindBy(xpath = "//button[@id = 'submitBTN']")
        WebElement loginButton;

        @FindBy(id = "cookie_disclaimer")
        WebElement popup;

        @FindBy(xpath = "//div[@class='vt-card error']")
        WebElement error;


        private void enterUsername(String username) {

            wait.waitForVisible(emailInput).clear();
            emailInput.sendKeys(username);
        }

        private void enterPassword(String password) {
            wait.waitForVisible(passwordInput).clear();
            passwordInput.sendKeys(password);
        }

        private void clickLogin() {
            WaitUtils.scrollIntoViewCenter(loginButton);
            WaitUtils.waitForClickable(loginButton).click();
        }

        public void login(String username, String password) {

            enterUsername(username);
            WaitUtils.dismissBottomPopupIfPresent(By.id("cookie_disclaimer"), By.id("cookie_stop"));
            WaitUtils.waitForInVisible(popup);
            enterPassword(password);
            clickLogin();

            WaitUtils.waitForVisible(error);

            //WaitUtils.dismissBottomPopupIfPresent(By.id("cookie_disclaimer"), By.id("cookie_stop"));
           // WaitUtils.waitForInVisible(popup);


            System.out.println("Credentials entered and login clicked");

            // site handling redirection
            DriverFactory.getDriver()
                    .get(ConfigReader.getProperty("url_f"));

            System.out.println("Redirected to Flights page");
        }
}

