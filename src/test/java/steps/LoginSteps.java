package steps;

import io.cucumber.java.en.Given;
import utilities.ConfigReader;
import utilities.JsonUtil;
import windows.LoginPage;

public class LoginSteps {

    @Given("user logs in using test data {String}")
    public void user_logs_in(String tcId) {

        LoginPage loginPage = new LoginPage();

        String username = JsonUtil.getData(tcId,"username");
        String password = JsonUtil.getData(tcId,"password");

        loginPage.login(username, password);
    }
}