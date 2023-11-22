## QA Automation - Thiago Pivatto

This is a QA automation project for assessing web pages using Selenium, developed by Thiago Pivatto.

### Prerequisites

Before running the project, you need to have the following tools installed on your machine:

1. [VS Code](https://code.visualstudio.com/download)
2. [Git](https://git-scm.com/downloads)

### Getting Started

Follow these steps to run the project on your local machine:

1. Create a new folder on your computer to hold the project.

2. Install the following extensions in VS Code:

   - [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
   - [Test Runner for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-test-adapter)

3. Open the newly created folder using Visual Studio Code (VS Code).

4. Open a new terminal in VS Code (Terminal -> New Terminal).

5. Clone this repository using the following command:

   ```bash
   git clone https://github.com/thiagopivatto/QA_Automation_Thiago.git
   ```

6. After cloning, navigate to the `src/test/tests/TestRunner.java` file in the project.

7. Click the "Play" button in VS Code to execute the test or press F5.

8. Follow the instructions in the VS Code Terminal to input a product.

### Project Structure

The project consists of the following Java classes:

#### AmazonPage.java

This class represents the Amazon web page and contains methods for interacting with elements on the page, such as search box, search button, and product details.

#### Selenium_Initializer.java

This class initializes the Selenium WebDriver, loads configurations, and navigates to the specified URL.

#### Selenium_Methods.java

A utility class that provides methods for interacting with web elements, taking screenshots, and comparing product details.

#### Selenium_Tests.java

This class contains the main test logic, including searching for a product, interacting with search results, and comparing product details.

#### TestRunner.java

The entry point of the project, which allows you to run the automation test. It also handles configuration setup.

### Project Execution

The `Selenium_Tests.java` class contains the main test logic. It performs the following steps:

1. Search for a product on Amazon.
2. Click on the search button.
3. Validate the number of search results.
4. Select the "Best Seller" option from the dropdown.
5. Loop through two products and gather details (name, price, rating, description, and URL).
6. Take a screenshot of each product.
7. Compare the details of the two products.
8. Generate an HTML test report summarizing the execution status and product details.

### Test Report

A test report is generated as an HTML file named `TestReport.html`. It includes:

- Test status (Pass or Fail).
- A summary of issues found during the test.
- Product details, including name, price, rating, description, and URL.

### Custom Configuration

The `TestRunner.java` class allows you to set custom configurations, such as the ChromeDriver path, URL, and product to search for. You can input these configurations during setup.

## Author

- Thiago Pivatto