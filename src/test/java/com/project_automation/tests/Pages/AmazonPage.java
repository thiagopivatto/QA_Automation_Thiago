package com.project_automation.tests.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AmazonPage {
    private static WebDriver driver;

    // Set Driver
    public static void setDriver(WebDriver driver) {
        AmazonPage.driver = driver;
    }

    // Search Box
    public static WebElement searchBox() {
        return driver.findElement(By.id("twotabsearchtextbox"));
    }

    // Search Button
    public static WebElement searchButton() {
        return driver.findElement(By.id("nav-search-submit-button"));
    }

    // First Result
    public static WebElement results() {
        return driver.findElement(By.xpath("//*[@id='search']/span[2]/div/h1/div/div[1]/div/div/span[1]"));
    }

    // Dropdown
    public static WebElement sortByDropdown() {
        return driver.findElement(By.xpath("//*[@id='a-autoid-0-announce']"));
    }
    
    // Best Seller Option
    public static WebElement bestSellerOption() {
        return driver.findElement(By.id("s-result-sort-select_5"));
    }

    // Amazon Product
    public static WebElement amazonProduct(int i) {
        return driver.findElement(By.xpath("//*[@id='search']/div[1]/div[1]/div/span[1]/div[1]/div[" + (i + 2) + "]/div/div/div/div/div/div[2]/div/div/div[1]/h2/a/span"));
    }

    // Product Name
    public static WebElement productName() {
        return driver.findElement(By.id("productTitle"));
    }

    // Price Symbol
    public static WebElement priceSymbol() {
        return driver.findElement(By.xpath("//*[@id='corePriceDisplay_desktop_feature_div']/div[1]/span[2]/span[2]/span[1]"));
    }

    // Price Whole
    public static WebElement priceWhole() {
        return driver.findElement(By.xpath("//*[@id='corePriceDisplay_desktop_feature_div']/div[1]/span[1]/span[2]/span[2]/text()"));
    }

    // Price Fraction
    public static WebElement priceFraction() {
        return driver.findElement(By.xpath("//*[@id='corePriceDisplay_desktop_feature_div']/div[1]/span[2]/span[2]/span[3]"));
    }

    // Full Price
    public static WebElement fullPrice() {
        return driver.findElement(By.xpath("//span[@class='a-offscreen']"));
    }

    // Product Rating
    public static WebElement productRating() {
        return driver.findElement(By.id("acrPopover"));
    }

    // Product Description
    public static WebElement productDescription() {
        return driver.findElement(By.id("feature-bullets"));
    }

    // Product URL
    public static WebElement productURL() {
        return driver.findElement(By.id("feature-bullets"));
    }

}
