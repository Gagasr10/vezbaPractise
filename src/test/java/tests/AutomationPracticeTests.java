package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import qa.pageObjects.AutomationPracticePage;

public class AutomationPracticeTests extends BaseTest {

    Logger log = LogManager.getLogger(AutomationPracticeTests.class);
    AutomationPracticePage automationPracticePage;

    @BeforeMethod
    public void openAutomationPractisePage() {
        goToUrl("automationPractiseUrl");
        automationPracticePage = new AutomationPracticePage(getDriver()); // ispravljeno
    }

    @Test
    public void TC01_selectFirstRadioButton() {
    	 log.info("Start tc01 selectedFirstRadioButton");
    	 
        automationPracticePage.selectFirstRadioButton();
        Assert.assertTrue(automationPracticePage.isRadioButton1Selected(), "Radio button 1 should be selected");
    }

    @Test
    public void TC02_selectSecondRadioButton() {
    	log.info("Start tc02_selectSecondRadioButton");
        automationPracticePage.selectSecondRadioButton();
        Assert.assertTrue(automationPracticePage.isRadioButton2Selected(), "Radio button 2 should be selected");
    }

   
   
    @Test
    public void TC03_selectThirdRadioButton() {
    	log.info("Start tc03_selectedThirdRadioButton");
        automationPracticePage.selectThirdRadioButton();
      
        Assert.assertTrue(automationPracticePage.isRadioButton3Selected(), "Radio button 3 should be selected");
    }

    @Test
    public void TC04_onlyOneRadioButtonSelected() {
        log.info("Start tc04_onlyOneRadioButtonSelected");

        // Selektuj prvi radio button i proveri da je samo on selektovan
        automationPracticePage.selectFirstRadioButton();
        Assert.assertTrue(automationPracticePage.isRadioButton1Selected(), "Radio button 1 should be selected");
        Assert.assertFalse(automationPracticePage.isRadioButton2Selected(), "Radio button 2 should NOT be selected");
        Assert.assertFalse(automationPracticePage.isRadioButton3Selected(), "Radio button 3 should NOT be selected");

        // Selektuj drugi radio button i proveri
        automationPracticePage.selectSecondRadioButton();
        Assert.assertFalse(automationPracticePage.isRadioButton1Selected(), "Radio button 1 should NOT be selected");
        Assert.assertTrue(automationPracticePage.isRadioButton2Selected(), "Radio button 2 should be selected");
        Assert.assertFalse(automationPracticePage.isRadioButton3Selected(), "Radio button 3 should NOT be selected");

        // Selektuj treći radio button i proveri
        automationPracticePage.selectThirdRadioButton();
        Assert.assertFalse(automationPracticePage.isRadioButton1Selected(), "Radio button 1 should NOT be selected");
        Assert.assertFalse(automationPracticePage.isRadioButton2Selected(), "Radio button 2 should NOT be selected");
        Assert.assertTrue(automationPracticePage.isRadioButton3Selected(), "Radio button 3 should be selected");

        log.info("End tc04_onlyOneRadioButtonSelected");
    }

    
}
