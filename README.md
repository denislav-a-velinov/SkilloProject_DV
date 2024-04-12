# Skillo Automation Project

This is the Skillo Automation project developed as part of the Skillo Automation with Java and WebDriver course.

## Overview

This project consists of automated test scenarios for the iSkillo online platform. It utilizes TestNG and Selenium WebDriver, following the Page Object Model (POM) design pattern with PageFactory. The tests are currently designed to run on Google Chrome browser and include a testng.xml configuration file for execution using Maven. Additionally, the project includes functionality to capture a screenshot whenever a test fails.

## Test Scenarios

The following test scenarios are included in this project:

1. **Login**: Verifies the login functionality of the iSkillo platform.
2. **Add New Post**: Tests the ability to add a new post on the iSkillo platform.
3. **Like/Dislike New Post**: Validates the functionality to like or dislike a new post.
4. **Delete Existing Post**: Tests the deletion of an existing post from the platform.
5. **Change Profile Picture**: Verifies the ability to change the profile picture on the iSkillo platform.
6. **Logout**: Tests the logout functionality to ensure users can successfully log out of their accounts.

## Usage

To run the tests, ensure you have the following prerequisites installed:

- Java Development Kit (JDK)
- Maven
- Google Chrome browser

Follow these steps to execute the tests:

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Open a terminal or command prompt.
4. Execute the tests by running the Maven command `mvn clean test`.

This command will execute the tests using the configurations specified in the `testng.xml` file.

## Screenshot Capture

Whenever a test fails, a screenshot will be captured and saved in the `screenshots` directory within the project.
