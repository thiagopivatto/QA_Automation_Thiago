package com.project_automation.tests;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.project_automation.config.ConfigReader;
import com.project_automation.tests.Pages.AmazonPage;

public class Selenium_Tests {
    
    // Initialize variables
    protected WebDriver driver;
    protected WebDriverWait wait;
    private Selenium_Methods seleniumMethods;

    // Constructor
    public Selenium_Tests(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        this.seleniumMethods = new Selenium_Methods(driver);
    }
    
    @Test
    public void validate_Amazon() throws Exception {

        // Set driver for AmazonPage
        AmazonPage.setDriver(driver);

        // Initialize variables
        String[] sName = new String[2];
        String[] sPrice = new String[2];
        String[] sRating = new String[2];
        String[] sDescription = new String[2];
        String[] sURL = new String[2];

        // Search for Product
        seleniumMethods.waitForElementPresentAndSendKeys(AmazonPage.searchBox(), ConfigReader.getProperty("PRODUCT"), 30);

        // Click on Search Button
        seleniumMethods.waitForElementAndClick(AmazonPage.searchButton(), 30);

        // Validate the number of search results
        int resultsNumberInt = Integer.parseInt(AmazonPage.results().getText().split(" ")[3].replace(",", ""));
        if (resultsNumberInt < 100) {
            System.out.println("There are not enough results for this product");
            Assert.fail("Not enough results for the product.");
        } else {
            System.out.println("There are enough results for this product");
        }

        // Select Best Seller Option from Dropdown
        seleniumMethods.waitForElementAndClick(AmazonPage.sortByDropdown(), 30);
        seleniumMethods.waitForElementAndClick(AmazonPage.bestSellerOption(), 30);

        // Loop through products
        for (int i = 0; i < 2; i++) {
            int iProduct = i + 1;

            // Click on Product
            seleniumMethods.waitForElementAndClick(AmazonPage.amazonProduct(i), 30);

            // Get Product Details
            sName[i] = AmazonPage.productName().getText();
            sRating[i] = AmazonPage.productRating().getAttribute("title");
            sPrice[i] = AmazonPage.fullPrice().getText();

            // Line Below No Longer Working
            //sPrice[i] = AmazonPage.priceSymbol().getText() + AmazonPage.priceWhole().getText() + "." + AmazonPage.priceFraction().getText();
            
            try {
                sDescription[i] = AmazonPage.productDescription().getText();
            } catch (Exception e) {
                sDescription[i] = "Not Available";
            }
            sURL[i] = driver.getCurrentUrl();

            // Take Screenshot
            String fileName = "Product_" + iProduct + ".png";
            seleniumMethods.takeScreenShot(fileName);
            System.out.println("Screenshot taken for Product " + iProduct + "!");

            // Go Back to Search Results
            driver.navigate().back();

            // Print a blank line for separation
            System.out.println("\n\n");
        }

        // Comparison of two products
        seleniumMethods.compareProductDetails(sName[0], sName[1], "Product Names");
        seleniumMethods.compareProductDetails(sPrice[0], sPrice[1], "Product Prices");
        seleniumMethods.compareProductDetails(sRating[0], sRating[1], "Product Ratings");
        seleniumMethods.compareProductDetails(sDescription[0], sDescription[1], "Product Descriptions");
        seleniumMethods.compareProductDetails(sURL[0], sURL[1], "Product URLs");

        // Create test report summarizing the execution status and extracted product details
        System.out.println("\n\nTest Report:");
        for (int i = 0; i < 2; i++) {
            System.out.println("Product " + (i + 1) + " Name: " + sName[i]);
            System.out.println("Product " + (i + 1) + " Price: " + sPrice[i]);
            System.out.println("Product " + (i + 1) + " Rating: " + sRating[i]);
            System.out.println("Product " + (i + 1) + " Description: " + sDescription[i]);
            System.out.println("Product " + (i + 1) + " URL: " + sURL[i]);
            System.out.println("\n");
        }

        // Summarize the report in an HTML file - Test Status - Issues Found - Product Details
        String htmlReport = "<html><head><title>Test Report</title>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; margin: 20px; }"
                + "h1 { background-color: #007BFF; color: #fff; padding: 10px; text-align: center; }"
                + "p { margin: 10px; }"
                + ".product-info { border: 1px solid #ddd; padding: 10px; margin: 10px; }"
                + ".product-info p { font-weight: bold; }"
                + "</style>"
                + "</head><body>"
                + "<h1>Test Report</h1>";

        // Test Status
        htmlReport += "<p>Test Status: ";
        if (sName[0].equals(sName[1]) || sPrice[0].equals(sPrice[1]) || sRating[0].equals(sRating[1]) || sDescription[0].equals(sDescription[1]) || sURL[0].equals(sURL[1])) {
            htmlReport += "Failed</p>";
        } else {
            htmlReport += "Passed</p>";
        }

        // Issues Found
        htmlReport += "<p>Issues Found: ";
        if (sName[0].equals(sName[1])) {
            htmlReport += "Product Names are the same. ";
        }
        if (sPrice[0].equals(sPrice[1])) {
            htmlReport += "Product Prices are the same. ";
        }
        if (sRating[0].equals(sRating[1])) {
            htmlReport += "Product Ratings are the same. ";
        }
        if (sDescription[0].equals(sDescription[1])) {
            htmlReport += "Product Descriptions are the same. ";
        }
        if (sURL[0].equals(sURL[1])) {
            htmlReport += "Product URLs are the same. ";
        }
        htmlReport += "</p>";

        // Product Details
        for (int i = 0; i < 2; i++) {
            htmlReport += "<div class='product-info'>";
            htmlReport += "<p>Product " + (i + 1) + " Name: " + sName[i] + "</p>";
            htmlReport += "<p>Product " + (i + 1) + " Price: " + sPrice[i] + "</p>";
            htmlReport += "<p>Product " + (i + 1) + " Rating: " + sRating[i] + "</p>";
            htmlReport += "<p>Product " + (i + 1) + " Description: " + sDescription[i] + "</p>";
            htmlReport += "<p>Product " + (i + 1) + " URL: " + sURL[i] + "</p>";
            htmlReport += "</div>";
        }

        htmlReport += "</body></html>";
        File file = new File("TestReport.html");
        FileUtils.writeStringToFile(file, htmlReport, "UTF-8");
    }

}