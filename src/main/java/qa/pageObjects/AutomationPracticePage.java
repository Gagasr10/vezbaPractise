package qa.pageObjects

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationPracticePage {

	private WebDriver driver ;
	private WebDriverWait wait;
	Actions action ; 
	
	
	public AutomationPracticePage (WebDriver driver) {
		this.driver= driver;
		this.action = action;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);	
			
	}
	
	
	@FindBy(css="input[value='radio1']")
	private WebElement radioButton1;
	
	@FindBy(css = "input[value='radio2']")
	private WebElement radioButton2;
	
	@FindBy(css = "input[value='radio3']")
	private WebElement radioButton3;
	
	
	
	
	
	
	
	
	
	
	public void selectFirstRadioButton() {
	    radioButton1.click();
	}
	
	public boolean isRadioButton1Selected() {
		return radioButton1.isSelected();
	}
	
	public void selectSecondRadioButton() {
		radioButton2.click();
	}
	
	public void selectThirdRadioButton() {
		radioButton3.click();
	}
	
	
	
	
}
