package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import org.apache.logging.log4j.core.config.Configurator;

import utils.ConfigReader;

public class BaseTest {

    protected WebDriver driver;
    protected ConfigReader config;
    protected WebDriverWait wait;


    @BeforeSuite
    public void configureLogging() {
        // Ručna inicijalizacija Log4j2 konfiguracije
        Configurator.initialize(null, "src/test/resources/log4j2.properties");
        System.out.println("Log4j2 configuration initialized.");
    }

    @BeforeMethod
    public void setUp() {
        config = new ConfigReader();
        String browser = config.getProperty("browser").trim();

        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }

        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:\\BrowserDrivers\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "C:\\BrowserDrivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "C:\\BrowserDrivers\\msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void goToUrl(String urlKey) {
        String url = config.getProperty(urlKey);
        if (url != null && !url.isEmpty()) {
            driver.get(url);
        } else {
            throw new RuntimeException("URL is not found or empty for key: " + urlKey);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
