package qa.pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage_Practise {
	
	private WebDriver driver ;
	private WebDriverWait wait;
	
	
	@FindBy(id = "username")
	private WebElement usernameInput;
	
	@FindBy(id = "password")
	private WebElement passwordInput ;
	
	@FindBy(css = "input[value='admin']")
	private WebElement adminRadioButton ;
	
	@FindBy(xpath = "//label[2]//span[2]")
	private WebElement userRadioButton;
	
	@FindBy(id = "terms")
	private WebElement checkboxAgree ;
	
	@FindBy(id = "signInBtn")
	private WebElement signInButton ;
	
	@FindBy(css = "select[class='form-control']")
	private WebElement userTypeDropdown ;
	
	@FindBy(css = "div.alert.alert-danger")
	private WebElement errorMessage;
	
	@FindBy(id = "okayBtn")
	private WebElement okButton ;
	
	
		
	public LoginPage_Practise(WebDriver driver, WebDriverWait wait ) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}
	
	
	public void selectUserType (String userType) {
		Select select = new Select(userTypeDropdown);
		select.selectByVisibleText(userType);
		
	}
	
	public void Login (String username, String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		signInButton.click();
		
		
	}
	
	public boolean isUsernameInputDisplayed() {
		return usernameInput.isDisplayed();
	}
	
	public boolean isPasswordInputDisplayed() {
		return passwordInput.isDisplayed();
	}
	
	public boolean isRadioAdminDisplayed() {
		return adminRadioButton.isDisplayed();
	}
	
	public boolean isRadioAdminSelected() {
		return adminRadioButton.isSelected();
	}
	
	
	public boolean isUserRadioDisplayed() {
	    try {
	        wait.until(ExpectedConditions.visibilityOf(userRadioButton));
	        return userRadioButton.isDisplayed();
	    } catch (Exception e) {
	        System.out.println("Greška prilikom čekanja na user radio button: " + e.getMessage());
	        return false;
	    }
	}
	
	public boolean isUserRadioSelected() {
	    return userRadioButton.isSelected();
	}
	
	public boolean selectUserRadioAndConfirm() {
	    userRadioButton.click();
	  
	    wait.until(ExpectedConditions.visibilityOf(okButton));
	    okButton.click();
	  
	    // Dodaj wait na element ili stanje ako je DOM promenjen
	    wait.until(ExpectedConditions.elementToBeClickable(userRadioButton));
	    return userRadioButton.isSelected();
	}
	
	public boolean isAgreeCheckboxDisplayed() {
	    return checkboxAgree.isDisplayed();
	}

	public void clickAgreeCheckbox() {
	    checkboxAgree.click();
	 
	}
	
	public boolean isAgreeCheckboxSelected() {
	    return checkboxAgree.isSelected();
	}

	
	public boolean isSignButtonDisplayed() {
		return signInButton.isDisplayed();
	}
	
	public List<String> getDropdownOptions() {
	    Select select = new Select(userTypeDropdown);
	    List<WebElement> options = select.getOptions();

	    List<String> actualOptions = new ArrayList<>();
	    for (WebElement option : options) {
	        actualOptions.add(option.getText().trim());
	    }
	    return actualOptions;
	}
	
	
	public String getSelectedUserType() {
	    Select select = new Select(userTypeDropdown);
	    return select.getFirstSelectedOption().getText();
	}
	
	public boolean isLoginErrorVisible(WebDriverWait wait) {
	    try {
	        wait.until(ExpectedConditions.visibilityOf(errorMessage));
	        return errorMessage.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	
	
	
}
