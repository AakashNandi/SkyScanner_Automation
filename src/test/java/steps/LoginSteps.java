//package steps;
//
//import io.cucumber.java.en.Given;
//import utilities.JsonUtil;
//import windows.LoginPage;
//
//public class LoginSteps {
//
//    @Given("user logs in using test data {String}")
//    public void user_logs_in(String tcId) {
//
//        LoginPage loginPage = new LoginPage();
//
//        String username = JsonUtil.getData(tcId,"username");
//        String password = JsonUtil.getData(tcId,"password");
//
//        loginPage.login(username, password);
//    }
//}

package steps;

import io.cucumber.java.en.Given;
import utilities.JsonUtil;
import utilities.ConfigReader;
import windows.LoginPage;
import base.DriverFactory;

public class LoginSteps {

        @Given("user logs in using test data {string}")
        public void user_logs_in_using_test_data(String tcId) {

            String username = JsonUtil.getData(tcId, "username");
            String password = JsonUtil.getData(tcId, "password");

            LoginPage loginPage = new LoginPage();
            loginPage.login(username, password);

        }
    }