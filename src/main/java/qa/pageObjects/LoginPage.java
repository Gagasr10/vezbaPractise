package qa.pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	WebDriver driver ;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	
	@FindBy(id = "inputUsername")
	private WebElement usernameInput;
	
	@FindBy(css = "input[type='password']")
	private WebElement passwordInput ;
	
	@FindBy(className = "signInBtn")
	private WebElement signInButton;

	
	@FindBy(css = "p.error")
	private WebElement errorMessage ;
	
//	@FindBy(className = "logout-btn")
//	private WebElement logoutButton;
	
	@FindBy(id = "chkboxOne")
	private WebElement checkboxRememberMe ; 
	
	@FindBy(id = "chkboxTwo")
	private WebElement checkboxAgreeTerms ;
	
	@FindBy(xpath = "//a[contains(text(),'Forgot your')]")
	private WebElement forgotPassword ;
	
	@FindBy(css = "input[placeholder='Name']")
	private WebElement nameField;
	
	@FindBy(css = "input[placeholder='Email']")
	private WebElement emailField;
	
	@FindBy(css = "input[placeholder='Phone Number']")
	private WebElement phoneNumberField ;
	
	@FindBy(css = "button[class='reset-pwd-btn']")
	private WebElement resetLogin ;
	
	@FindBy(css = "p[class='infoMsg']")
	private WebElement resetMessage;
	
	@FindBy(xpath =  "//p[contains(text(), 'You are ')]")
	private WebElement succescfullLoginMessage;
	
	@FindBy(tagName = "h2")  // ili preciznije ako ima više <h2> tagova
	private WebElement welcomeMessage;
	
	@FindBy(css = "button[class='logout-btn']")
	private WebElement logoutButton;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public void enterUserName(String username) {
		usernameInput.clear();
		usernameInput.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		passwordInput.clear();
		passwordInput.sendKeys(password);
	
	}
	
	public void clickSignIn() {
		signInButton.click();
	}
	
	public String getErrorMessage() {
		return errorMessage.getText();
	}
	
	public boolean isSignButtonDisplayed () {
		return signInButton.isDisplayed();
	}
	
	public void login (String username, String password) {
		enterUserName(username);
		enterPassword(password);
		clickSignIn();
	}
	
	public boolean isUsernameDisplayed () {
		return usernameInput.isDisplayed();
	}
	
	public boolean isPasswordDisplayed() {
		return passwordInput.isDisplayed();
	}
	
	public boolean isNameFieldDispalayed() {
		return nameField.isDisplayed();
	}
	
	public boolean isEmailFieldDisplayed() {
		return emailField.isDisplayed();
	}
	
	public boolean isPhoneNumberDisplayed() {
		return phoneNumberField.isDisplayed();
	}
	
	public void forgotPasswordClick() {
		forgotPassword.click();
		wait.until(ExpectedConditions.visibilityOf(nameField));
		
		
	}
	
	public String getResetMessage() {
		wait.until(ExpectedConditions.visibilityOf(resetMessage));
		return resetMessage.getText();
	}
	
	public void resetLoginClick() {
		resetLogin.click();
				
	}
	
	public boolean isResetMessageDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(resetMessage));
		return resetMessage.isDisplayed();
	}
	
	public boolean isCheckboxRememberMePresent() {
		return checkboxRememberMe.isDisplayed();
	}
	
	public boolean isCheckboxRememberMeSelected() {
		checkboxRememberMe.click();
		return checkboxRememberMe.isSelected();
	}
		
	public boolean isCheckboxAgreePresent() {
		return checkboxAgreeTerms.isDisplayed();
	}
	
	public boolean isCheckboxAgreeSelected() {
		checkboxAgreeTerms.click();
		return checkboxAgreeTerms.isSelected();
		
	}
	
	public boolean isSuccessfullMessageDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(succescfullLoginMessage));
		return succescfullLoginMessage.isDisplayed();
	}
	
	public String getSuccessfullMessage() {
		return succescfullLoginMessage.getText();
	}
	
	public String getWelcomeMessageText() {
	    return welcomeMessage.getText();
	}
	
	public void logoutProcess() {
		wait.until(ExpectedConditions.visibilityOf(logoutButton));
		logoutButton.click();
	}
	
}

