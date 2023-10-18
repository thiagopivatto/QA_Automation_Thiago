package com.project_automation.tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class TestRunner {

    public static void main(String[] args) {
        Properties properties = new Properties();
        FileInputStream fis = null;
        Scanner scanner = new Scanner(System.in);
        try {
            fis = new FileInputStream("config.properties");
            properties.load(fis);
            System.out.println("Setup file found! What would you like to do?");
            System.out.println("1. Use existing configurations");
            System.out.println("2. Create new configurations");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                System.out.println("Using existing configurations..");
            } else if (choice == 2) {
                createNewConfig(properties, scanner);
            } else {
                System.out.println("Invalid option. Using existing configurations..");
                createNewConfig(properties, scanner);
            }
        } catch (IOException e) {
            createNewConfig(properties, scanner);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            scanner.close();
            Selenium_Initializer seleniumTests = new Selenium_Initializer(properties.getProperty("webdriver.chrome.driver"));
            seleniumTests.validateAmazonProducts();
        }  
    }

    private static void createNewConfig (Properties properties, Scanner scanner) {
        // Setup Inital Properties
        properties.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver.exe");
        properties.setProperty("URL", "https://www.amazon.com");
        // Ask for user input
        System.out.println("Please enter a product you would like to search for:");
        properties.setProperty("PRODUCT", scanner.nextLine());
        // If empty, use default
        if (properties.getProperty("PRODUCT").isEmpty()) {
            properties.setProperty("PRODUCT", "iPhone 12");
        }
        System.out.println("Welcome to setup for Ontop Assessment:");
        try {
            FileOutputStream fos = new FileOutputStream("config.properties");
            properties.store(fos, "Automation Test Configurations");
            fos.close();
            System.out.println("Setup Complete!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
