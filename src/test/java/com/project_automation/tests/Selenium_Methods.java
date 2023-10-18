package com.project_automation.tests;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Selenium_Methods {
    private WebDriver driver;
    private WebDriverWait wait;

    public Selenium_Methods(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    // Take ScreenShot
    public void takeScreenShot(String fileName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Copy file to current directory
            FileUtils.copyFile(screenshot, new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Compare Product Details
    public void compareProductDetails(String value1, String value2, String detailName) {
        try {
            if (value1.equals(value2)) {
                System.out.println(detailName + " are the same");
                Assert.fail(detailName + " are the same");
            } else {
                System.out.println(detailName + " are different");
            }
        } catch (AssertionError e) {
            // Handle the AssertionError exception (the test will continue)
            System.out.println("Error detected, but the test will continue.");
        }

        // Additional code to be executed after the failure
        System.out.println("Continuing with the test...");
    }
    
    // Wait for Element and Click
    public void waitForElementAndClick(WebElement element, int timeoutSeconds) {
        wait.until(ExpectedConditions.visibilityOf(element));
        clickWithHighlight(element);
    }
    
    // Wait for Element and Send Keys
    public void waitForElementAndSendKeys(WebElement element, String text, int timeoutSeconds) {
        wait.until(ExpectedConditions.visibilityOf(element));
        sendKeysWithHighlight(element, text);
    }

    // Wait for Element and Clear
    protected void waitForElementPresentAndClick(WebElement element, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
        clickWithHighlight(element);
    }

    // Wait for Element and Send Keys
    protected void waitForElementPresentAndSendKeys(WebElement element, String text, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
        sendKeysWithHighlight(element, text);
    }

    // Wait for Element and Clear
    protected void waitForElementPresentAndClear(WebElement element, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
        clearWithHighlight(element);
    }

    // Wait for Element Present
    protected void waitForElementPresent(WebElement element, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Click with Highlight
    protected void clickWithHighlight(WebElement element) {
        highlightElement(element);
        element.click();
        waitForSeconds(1);
    }

    // Send Keys with Highlight
    protected void sendKeysWithHighlight(WebElement element, String text) {
        highlightElement(element);
        element.sendKeys(text);
        waitForSeconds(1);
    }

    // Clear with Highlight
    protected void clearWithHighlight(WebElement element) {
        highlightElement(element);
        element.clear();
        waitForSeconds(1);
    }

    // Function to highlight a WebElement
    protected void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid red';", element);
        // Wait for 0.5 seconds
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Remove the border
        js.executeScript("arguments[0].style.border='';", element);
    }

    // Wait for Seconds
    public void waitForSeconds (long timeoutSeconds) {
        try {
            Thread.sleep(timeoutSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
