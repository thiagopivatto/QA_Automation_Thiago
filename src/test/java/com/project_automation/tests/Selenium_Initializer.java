package com.project_automation.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.project_automation.config.ConfigReader;

public class Selenium_Initializer {
    private WebDriver driver;
    private WebDriverWait wait;

    public Selenium_Initializer(String chromeDriverPath) {
        ConfigReader.loadConfig();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");

        // Set ChromeDriver path and initialize the driver
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 30);

        // Navigate to the specified URL
        driver.get(ConfigReader.getProperty("URL"));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void validateAmazonProducts() {
        try {
            Selenium_Tests seleniumTests = new Selenium_Tests(driver);
            seleniumTests.validate_Amazon();
        } catch (Exception e) {
            // Kill All Chrome Processes
            try {
                Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
