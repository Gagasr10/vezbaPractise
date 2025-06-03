package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import qa.pageObjects.AutomationPracticePage;

public class AutomationPracticeTests extends BaseTest {
	
	Logger log = LogManager.getLogger(AutomationPracticeTests.class);
	AutomationPracticePage automationPracticePage ;
	
	
	@BeforeMethod
	public void openAutomationPractisePage(){
		goToUrl("automationPractiseUrl");
		automationPracticePage = new AutomationPracticePage(driver);
	}
	
	
	
	@Test
	public void TC01_selectFirstRadioButton() {
		automationPracticePage.selectFirstRadioButton();
		Assert.assertTrue(automationPracticePage.isRadioButton1Selected(), "Radio button 1 should be selected");
	}
	
	
	  @Test
	    public void TC02_selectSecondRadioButton() {
		  automationPracticePage.selectSecondRadioButton();
	        Assert.assertTrue(automationPracticePage.isRadioButton2Selected(), "Radio button 2 should be selected");
	    }

	    @Test
	    public void TC03_selectThirdRadioButton() {
	    	  automationPracticePage.selectThirdRadioButton();
		        Assert.assertTrue(automationPracticePage.isRadioButton3Selected(), "Radio button 3 should be selected");
	    }

	    @Test
	    public void TC04_onlyOneRadioButtonSelected() {
	        automationPracticePage.selectFirstRadioButton();
	        Assert.assertTrue(automationPracticePage.isRadioButton1Selected(), "Radio button 1 should be selected");

	        automationPracticePage.selectSecondRadioButton();
	        Assert.assertTrue(automationPracticePage.isRadioButton2Selected(), "Radio button 2 should be selected");
	        Assert.assertFalse(automationPracticePage.isRadioButton1Selected(), "Radio button 1 should not be selected");

	        automationPracticePage.selectThirdRadioButton();
	        Assert.assertTrue(automationPracticePage.isRadioButton3Selected(), "Radio button 3 should be selected");
	        Assert.assertFalse(automationPracticePage.isRadioButton2Selected(), "Radio button 2 should not be selected");
	    }

}
