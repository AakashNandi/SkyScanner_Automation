package windows;


import base.BasePage;
import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

        @FindBy(xpath = "//button[contains(text(),'Login')]")
        WebElement loginButton;

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

            enterUsername(username);
            enterPassword(password);
            clickLogin();

            System.out.println("✅ Credentials entered and login clicked");

            // site handling: force navigation
            DriverFactory.getDriver()
                    .get(ConfigReader.getProperty("url_f"));

            System.out.println("Redirected to Flights page");
        }
}

