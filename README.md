# Test Automation Assessment

This test automation framework built using **Java**, **Maven**, **Selenium WebDriver**, **Selenium Grid**, **TestNG**, and **ExtentReports**.  
It includes various advanced test automation techniques such as parallel testing, cross-browser testing, **Page Object Model (POM)**, and externalized configuration and test data. The framework is containerized using Docker to ensure consistency across environments.

## Project Concepts and Features

### 1. Page Object Model (POM)
The project follows the **Page Object Model** design pattern to promote maintainability and reusability.

### 2. Parallel Testing
Using **TestNG's parallel execution feature**, tests are run in parallel across different threads.

### 3. Cross-Browser Testing
The framework supports **cross-browser testing** across Chrome and Firefox browsers using **Selenium Grid**.  
Browsers are spun up in containers to ensure isolation and consistency during execution.

### 4. Common Web Page Injection Methods
The framework includes utilities for common web actions like clicking elements, getting text, handling mousehover, interacting with iframes, etc. These methods are centralized in a utility class to simplify the codebase.

### 5. Externalized Test Configuration and Test Data
Configuration settings such as browser types, base URL,are externalized in a `config.properties` file.  
Test data is externalized in JSON files under the `test-data` folder, making it easy to update test data without changing the codebase.

### 6. Commonly Used Test Utility Classes
The framework provides several utility classes for frequently performed tasks like capturing screenshots, reading properties files, managing drivers, and handling waits.  
This helps keep the test scripts clean and readable.
### 7. Screenshots and Reporting
The framework automatically captures screenshots for each test step,and also captures screenshots for failed tests, and includes them in **ExtentReports** for better visibility.  
Reports are generated in HTML format and include test execution details like status,and screenshots.

### 8. Tools and Libraries
- **Java**: The programming language used for writing the test scripts.
- **Maven**: Used as the build automation tool and dependency manager.
- **Selenium WebDriver**: Used for browser automation.
- **Selenium WebDriverListener**: Used for to intercept WebDriver events (like clicks, navigation, etc.) for taking screenshots.
- **Selenium Grid**: Used for executing tests on multiple machines in parallel across different browsers.
- **TestNG**: The testing framework used for structuring and executing the tests.
- **ExtentReports**: Used for generating detailed HTML reports with screenshots and logs.
- **Docker**: Used for containerizing the project to run Selenium Grid and browsers in isolated environments.

## Framework Setup

### 1. Clone the Repository
```bash
cd test-automation-assessment
git clone https://github.com/your-repo/test-automation-assessment.git
```
### 2. Install Dependencies
Ensure you have Maven installed. Run the following command to install the required dependencies:

```bash
mvn clean install
```

## Framework Structure
 
The following is an overview of the folder structure of the project:

- **scripts/**: Contains shell scripts for building Docker images, setting up Selenium Grid, and other services to run tests in Docker.
  - `setup.sh`: Script to build a Docker image for the project.
  - `start-grid.sh`: Script to start Selenium Grid using Docker.
  - `run-tests.sh`: Script to Starts the test execution using Docker Compose and ensures that tests run in the containerized environment.
  - `runner.sh`:  Dockerfile entrypoint script for executing test runs.
  

- **src/main/java/**: Contains all the Java source code for the project.
  - **com.testautomation/**: The main package for the test automation framework.
    - **listeners/**: Contains listeners for handling test events.
      - `TestListener`: A listener for handling general test events like starting and finishing tests.
      - `TestScreenshotListener`: Implements WebDriverListener for capturing screenshots for every WebDriver events during the execution of a test.
    - **pages/**: Stores Page Object classes representing the web pages.
    - **reports/**: Handles the generation and storage of test reports.
    - **tests/**: Contains the TestNG test classes for the test cases.
    - **utility/**: Contains utility classes for common tasks such as driver management, selenuim web element interactions, waits, and screenshot handling.


- **src/main/resources/**: Contains external configuration files and test data.
  - **config/**: Stores configuration files like `config.properties`.
  - **test-data/**: Contains JSON files for external test data.
  - `logback.xml`: Configuration file for logging with Logback.


- **.env**: Environment variable configuration file for Docker.
- **Dockerfile**: The Dockerfile used to containerize the project.
- **grid.yaml**: YAML configuration for setting up Selenium Grid with Docker.
- **test-suites.yaml**: A Docker Compose configuration file used to define and execute the test suites.
- **test-suite.xml**: TestNG suite configuration file for running the test cases.
- **target/**: The default output directory for compiled code and test reports generated by Maven.

## Running Tests
### 1.External Configuration
#### 1. to control Run Tests in Browser Mode locally
The `config.properties` file contains the following key settings:

- **browser**: The browser to run tests on (e.g., Chrome, Firefox).
- **selenium.grid.enabled**: to control run tests in browser mode `false` or headless mode `false`.
#### 2. to control Run Tests in docker environment  
The `.env` file contains the following key settings:

- **BROWSER**: The browser to run tests on (e.g., Chrome, Firefox).
- **THREAD_COUNT**: set count of threads to run tests in parallel.

### 1. Run Tests in Browser Mode locally
To run tests in the browser (non-headless mode), use the following Maven command:

```bash
mvn test -DsuiteXmlFile=test-suite.xml
```
### 2. Run Tests in Headless Mode (docker environment)
To run tests in headless mode (without a visible browser window), follow these steps:

1-Start the Docker service:
```bash
sudo service docker start
```

2- Build the Maven project and Docker image: Run the setup.sh script to build the Maven project and Docker image:
```bash
./scripts/setup.sh
```
3- Start Selenium Grid: To make Selenium Grid up and running, use the start-grid.sh script:
```bash
./scripts/start-grid.sh
```
4- Execute the tests: Finally, to execute the tests, run the run-tests.sh script:
```bash
./scripts/run-tests.sh
```
## Reporting
#### 1.  The test result report will be available in the `target/TestReport` directory.
#### 2.  Screenshots for each test step will be available in the `target/TestCase_screenshots` directory.