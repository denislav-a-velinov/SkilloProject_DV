package WebTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class TestObject {
    public static final String TEST_RESOURCES_DIR = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    public static final String SCREENSHOTS_DIR = TEST_RESOURCES_DIR.concat("screenshots" + File.separator);
    public static final String REPORTS_DIR = TEST_RESOURCES_DIR.concat("reports" + File.separator);
    private WebDriver webDriver;

    @BeforeSuite
    protected final void setupTestSuite() throws IOException {
        cleanDirectory(SCREENSHOTS_DIR);
        cleanDirectory(REPORTS_DIR);
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    protected final void setUpTest() {
        this.webDriver = new ChromeDriver(configChromeOptions());
        this.webDriver.manage().window().maximize();
        this.webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20L));
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));
    }
    @AfterMethod
    protected final void tearDownTest(ITestResult testResult) {
        takeScreenshot(testResult);
        quitDriver();
    }
    @AfterSuite
    public void deleteDownloadFiles() throws IOException {
    }
    private void quitDriver() {
        if (this.webDriver != null) {
            this.webDriver.quit();
        }
    }
    protected WebDriver getWebDriver() {
        return webDriver;
    }
    private ChromeOptions configChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-popup-blocking");
        return chromeOptions;
    }
    private void cleanDirectory(String directoryPath) throws IOException {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        FileUtils.cleanDirectory(directory);
        String[] fileList = directory.list();
        if (fileList != null && fileList.length == 0) {
            System.out.printf("All file are deleted in Directory: %s%n", directoryPath);
        } else {
            System.out.printf("Unable to delete the files in Directory: %s%n", directoryPath);
        }
    }
    private void takeScreenshot(ITestResult testResult) {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            try {
                WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(50)); // Adjust timeout as needed
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

                TakesScreenshot takesScreenshot = (TakesScreenshot) webDriver;
                File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
                String testName = testResult.getName();
                FileUtils.copyFile(screenshot, new File(SCREENSHOTS_DIR.concat(testName).concat(".jpg")));
            } catch (IOException | org.openqa.selenium.TimeoutException e) {
                System.out.println("Unable to create a screenshot file: " + e.getMessage());
            }
        }
    }
}