package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import qa.pageObjects.LoginPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Listeners(tests.TestListener.class)
public class LoginTest extends BaseTest {

    Logger log = LogManager.getLogger(LoginTest.class);
    LoginPage loginPage;

    @BeforeMethod
    public void openLoginPage() {
        goToUrl("locatorsPageUrl");
        loginPage = new LoginPage(getDriver()); // ✅ ispravljeno
    }

    @Test(priority = 1)
    public void verifyLoginFormDisplayed() {
        log.info("Starting verifyLoginFormDisplayed");

        Assert.assertTrue(loginPage.isUsernameDisplayed(), "Username input is not displayed");
        Assert.assertTrue(loginPage.isPasswordDisplayed(), "Password input is not displayed");
        Assert.assertTrue(loginPage.isSignButtonDisplayed(), "SignInButton is not displayed");
    }

    @Test(priority = 2)
    public void loginEmptyFields() {
        log.info("Starting loginEmptyFields");
        loginPage.clickSignIn();
        Assert.assertEquals(loginPage.getErrorMessage(), "* Incorrect username or password");
    }

    @Test(priority = 3)
    public void loginWrongCredentials() {
        log.info("Starting loginWrongCredentials");
        loginPage.login("dragan", "serbia@99.gmail.com");
        loginPage.clickSignIn();
        Assert.assertEquals(loginPage.getErrorMessage(), "* Incorrect username or password");
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://rahulshettyacademy.com/locatorspractice/",
                "User is not on login page after failed login"); // ✅ ispravljeno
    }

    @Test(priority = 4)
    public void clickOnForgotPassword() {
        log.info("Starting clickOnForgotPassword");
        loginPage.forgotPasswordClick();

        Assert.assertTrue(loginPage.isNameFieldDispalayed(), "Name field is not displayed on the page");
        Assert.assertTrue(loginPage.isEmailFieldDisplayed(), "Email field is not displayed on the page");
        Assert.assertTrue(loginPage.isPhoneNumberDisplayed(), "Phone number field is not displayed on the page");
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://rahulshettyacademy.com/locatorspractice/",
                "User is not o login page after click on forgot password"); // ✅ ispravljeno
    }

    @Test(priority = 5)
    public void resetPassword() {
        log.info("Starting resetPassword");
        loginPage.forgotPasswordClick();
        loginPage.resetLoginClick();
        loginPage.isResetMessageDisplayed();

        Assert.assertTrue(loginPage.isResetMessageDisplayed(), "reset message is not displayed on the page");
        Assert.assertEquals(loginPage.getResetMessage(),
                "Please use temporary password 'rahulshettyacademy' to Login.", "Message is not the same");
    }

    @Test(priority = 6)
    public void checkboxIsPresent() {
        log.info("Start tc checkboxIsPresent");
        Assert.assertTrue(loginPage.isCheckboxRememberMePresent(), "checkbox Remeber me is not on the page");
        Assert.assertTrue(loginPage.isCheckboxAgreePresent(), "checkbox Agree Me is not on the page");
    }

    @Test(priority = 7)
    public void checkboxRememberMeSelected() {
        log.info("Start tc --> checkboxRememberMeSelected");

        Assert.assertTrue(loginPage.isCheckboxRememberMeSelected(), "checkbox 'Remember Me' is present");
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://rahulshettyacademy.com/locatorspractice/",
                "User is not o login page after click on forgot password"); // ✅ ispravljeno
    }

    @Test(priority = 8)
    public void checkboxAgreeSelected() {
        log.info("Start tc --> checkboxAgreeSelected");
        Assert.assertTrue(loginPage.isCheckboxAgreeSelected(), " checkbox 'Agree' is selected");
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://rahulshettyacademy.com/locatorspractice/",
                "User is not o login page after click on forgot password"); // ✅ ispravljeno
    }

    @Test(priority = 9)
    public void checkboxAllSelected() {
        log.info("Start tc --> checkboxAllSelected");
        Assert.assertTrue(loginPage.isCheckboxRememberMeSelected(), "checkbox 'Remember Me' is present");
        Assert.assertTrue(loginPage.isCheckboxAgreeSelected(), " checkbox 'Agree' is selected");
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://rahulshettyacademy.com/locatorspractice/",
                "User is not o login page after click on forgot password"); // ✅ ispravljeno
    }

    @Test(priority = 10)
    public void successfullLogin() {
        String username = "Miller442";
        String password = "rahulshettyacademy";

        loginPage.login(username, password);
        loginPage.isSuccessfullMessageDisplayed();

        Assert.assertEquals(loginPage.getSuccessfullMessage(), "You are successfully logged in.",
                "succesfull message is not the same");

        String actualWelcomeText = loginPage.getWelcomeMessageText();
        String expectedWelcomeText = "Hello " + username + ",";

        log.info("Validating welcome message: " + actualWelcomeText);
        Assert.assertEquals(actualWelcomeText, expectedWelcomeText, "Welcome message with username is incorrect");

        log.info("End tc --> successfullLogin");
    }

    @Test(priority = 11)
    public void logoutFunctionality() {
        log.info("Start tc --> logoutFunctionality");
        loginPage.login("mike330", "rahulshettyacademy");
        loginPage.logoutProcess();

        Assert.assertTrue(loginPage.isUsernameDisplayed(),
                "We are not back to login page, because there is no username field displayed");
        Assert.assertTrue(loginPage.isPasswordDisplayed(),
                "We are not back to login page, because there is no PASSWORD field displayed");
    }
}
