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
            super(DriverFactory.getDriver());
            wait = new WaitUtils(DriverFactory.getDriver());
        }

        // ================= LOCATORS =================

        @FindBy(id = "email")
        WebElement emailInput;

        @FindBy(id = "password")
        WebElement passwordInput;

        @FindBy(xpath = "//button[@id = 'submitBTN']")
        WebElement loginButton;

        @FindBy(id = "cookie_disclaimer")
        WebElement popup;

        // ================= LOW-LEVEL ACTIONS =================


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
        // ================= BUSINESS / REAL LOGIC =================

        /**
         * REAL login flow for dummy site:
         * 1. Enter credentials
         * 2. Click login
         * 3. Force redirect to flights page
         */
        public void login(String username, String password) {

            WaitUtils.waitForVisible(popup);
            WaitUtils.dismissBottomPopupIfPresent(By.id("cookie_disclaimer"), By.id("cookie_stop"));

            enterUsername(username);
            enterPassword(password);
            clickLogin();


            System.out.println("Credentials entered and login clicked");

            // site handling: force navigation
            DriverFactory.getDriver()
                    .get(ConfigReader.getProperty("url_f"));

            System.out.println("Redirected to Flights page");
        }
}

