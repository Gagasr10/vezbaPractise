package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import org.apache.logging.log4j.core.config.Configurator;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

public class BaseTest {

    protected static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    protected ConfigReader config;
    protected WebDriverWait wait;

    @BeforeSuite
    public void configureLogging() {
        Configurator.initialize(null, "src/test/resources/log4j2.properties");
        System.out.println("Log4j2 configuration initialized.");
    }

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        config = new ConfigReader();

        WebDriver driver;

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        threadDriver.set(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void goToUrl(String urlKey) {
        String url = config.getProperty(urlKey);
        if (url != null && !url.isEmpty()) {
            getDriver().get(url);
        } else {
            throw new RuntimeException("URL is not found or empty for key: " + urlKey);
        }
    }

    public WebDriver getDriver() {
        return threadDriver.get();
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            threadDriver.remove();
        }
    }
}
