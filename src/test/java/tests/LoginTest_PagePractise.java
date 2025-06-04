package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import qa.pageObjects.LoginPage_Practise;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Listeners(tests.TestListener.class)
public class LoginTest_PagePractise extends BaseTest {

    Logger log = LogManager.getLogger(LoginTest_PagePractise.class);
    LoginPage_Practise loginPage_Practise;

    @BeforeMethod
    public void openLoginPage_Practise() {
        goToUrl("loginPageUrl");
        loginPage_Practise = new LoginPage_Practise(getDriver(), wait);
    }

    @Test(priority = 1)
    public void loginSuccessfully() {
        log.info("Starting login test with valid credentials...");

        loginPage_Practise.Login("rahulshettyacademy", "learning");

        String expectedUrl = "https://rahulshettyacademy.com/angularpractice/shop";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        String actualUrl = getDriver().getCurrentUrl(); // 
        log.info("Current URL after login: " + actualUrl);
        Assert.assertEquals(actualUrl, expectedUrl, "URL is not the expected after login.");
    }

    @Test(priority = 2)
    public void loginInvalidPassword() {
        log.info("Starting loginInvalidPassword");
        loginPage_Practise.Login("rahulshettyacademy", "dnnjd");

        boolean isErrorVisible = loginPage_Practise.isLoginErrorVisible(wait);
        log.info("Is error message displayed: " + isErrorVisible);
        Assert.assertTrue(isErrorVisible, "Expected error message for invalid login was not displayed.");

        String expectedUrl = "https://rahulshettyacademy.com/loginpagePractise/";
        String actualUrl = getDriver().getCurrentUrl(); // ✅ ispravljeno
        log.info("URL after failed login: " + actualUrl);
        Assert.assertEquals(actualUrl, expectedUrl, "URL changed after invalid login – expected to stay on login page.");
    }

    @Test(priority = 3)
    public void loginEmptyFields() {
        log.info("Starting loginEmptyFields..");
        loginPage_Practise.Login("", "");

        boolean isErrorVisible = loginPage_Practise.isLoginErrorVisible(wait);
        log.info("Is error message displayed: " + isErrorVisible);
        Assert.assertTrue(isErrorVisible, "Expected error message for invalid login was not displayed.");
    }

    @Test(priority = 4)
    public void loginWithOnlyUsername() {
        log.info("Starting loginWithOnlyUsername");
        loginPage_Practise.Login("rahulshettyacademy", "");

        boolean isErrorVisible = loginPage_Practise.isLoginErrorVisible(wait);
        log.info("Is error message displayed: " + isErrorVisible);
        Assert.assertTrue(isErrorVisible, "Expected error message for invalid login was not displayed.");
    }

    @Test(priority = 5)
    public void loginOnlyWithPassword() {
        log.info("Starting loginOnlyWithPassword");
        loginPage_Practise.Login(" ", "learning");

        boolean isErrorVisible = loginPage_Practise.isLoginErrorVisible(wait);
        log.info("Is error message displayed: " + isErrorVisible);
        Assert.assertTrue(isErrorVisible, "Expected error message for invalid login was not displayed.");
    }

    @Test(priority = 6)
    public void usernameFieldIsDisplayed() {
        log.info("Starting usernameField is on the page");
        Assert.assertTrue(loginPage_Practise.isUsernameInputDisplayed(), "Username input is not displayed on page");
    }

    @Test(priority = 7)
    public void passwordFieldIsDisplayed() {
        log.info("Starting password field is on the page");
        Assert.assertTrue(loginPage_Practise.isPasswordInputDisplayed(), "Password input field is not on the page");
    }

    @Test(priority = 8)
    public void signButtonIsOnThePage() {
        log.info("Starting signButton --> IsOnThePage");
        Assert.assertTrue(loginPage_Practise.isSignButtonDisplayed(), "Sign button is not on the page");
    }

    @Test(priority = 9)
    public void isAdminRadioButtonDisplayed() {
        log.info("Starting isAdminRadioButtonDisplayed");
        Assert.assertTrue(loginPage_Practise.isRadioAdminDisplayed(), "Admin radio button is not displayed on the page");
    }

    @Test(priority = 10)
    public void isUserRadioButtonDisplayed() {
        log.info("Starting isUserRadioButtonDisplayed");
        Assert.assertTrue(loginPage_Practise.isUserRadioDisplayed(), "User radio button is not displayed on the page");
    }

    @Test(priority = 11)
    public void verifyDropdownOptionsAndSelection() {
        log.info("Starting verifyDropdownOptionsAndSelection");

        List<String> actualOptions = loginPage_Practise.getDropdownOptions();

        Assert.assertTrue(actualOptions.contains("Student"), "'Student' not found in dropdown options");
        Assert.assertTrue(actualOptions.contains("Teacher"), "'Teacher' not found in dropdown options");

        loginPage_Practise.selectUserType("Student");
        Assert.assertEquals(loginPage_Practise.getSelectedUserType(), "Student");

        loginPage_Practise.selectUserType("Teacher");
        Assert.assertEquals(loginPage_Practise.getSelectedUserType(), "Teacher");

        log.info("verifyDropdownOptionsAndSelection passed");
    }

    @Test(priority = 12)
    public void isAgreeTermsOnPage() {
        log.info("Starting isAgreeTermsOnPage");
        Assert.assertTrue(loginPage_Practise.isAgreeCheckboxDisplayed(), "Agree checkbox is not displayed on the page");
    }

    @Test(priority = 13)
    public void isAgreeTermsIsSelected() {
        log.info("Starting isAgreeTermsIsSelected");
        loginPage_Practise.clickAgreeCheckbox();
        Assert.assertTrue(loginPage_Practise.isAgreeCheckboxSelected(), "Agree checkbox is not selected");
    }

    @Test(priority = 14)
    public void testUserRadioSelection() {
        log.info("Starting testUserRadioSelection");
        boolean isSelected = loginPage_Practise.selectUserRadioAndConfirm();
        Assert.assertTrue(isSelected, "User radio button should be selected after confirmation");
    }
}
