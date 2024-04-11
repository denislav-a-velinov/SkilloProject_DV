package WebTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
//import object.*;
import factory.*;
import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.profiler.model.Profile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;


public class LoginTest extends TestObject2{
    // Below we can choose which WebBrowser to use for the test (currently using Chrome):
    //ChromeDriver webDriver;
    //FirefoxDriver webDriver;
    //EdgeDriver webDriver;
    //SafariDriver webDriver;
    //private final boolean userReg = false;
    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        return new Object[][]{
                //To pass the first data object the userId needs to be changed to 5508
                {"dvelinov1","dvelinov1pass", "5627"},
        };
    }
    @Test(dataProvider = "getUser")
    public void loginTestString(String username, String password, String userId){
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        HomePage homePage = new HomePage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);

        homePage.navigateTo();
        Assert.assertTrue(homePage.isUrlLoaded(), "Current page is not the Home page.");

        header.clickLogin();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not the Login page.");

        loginPage.fillInUserName(username);
        loginPage.fillInPassword(password);

        loginPage.checkRememberMe();
        Assert.assertTrue(loginPage.isCheckedRememberMe(), "Remember me checkbox is checked.");

            // THE BELOW IS FOR POM:
//        loginPage.clickSignInButton();
            //THE BELOW ONE IS FOR FACTORY:
        loginPage.clickSignIn();

        WebElement loginMessageBox = webDriver.findElement(By.xpath("//*[@id='toast-container']//*[@class='toast-message ng-star-inserted']"));
        Actions actionsForElements = new Actions(webDriver);
        actionsForElements.moveToElement(loginMessageBox).perform();

        String expectedMessage = "Successful login!";
        String actualMessage = loginMessageBox.getText();
        Assert.assertEquals(actualMessage, expectedMessage, "The actual message is not matching the expected one!");

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is not the Profile page.");

        WebElement profilePageLink = webDriver.findElement(By.id("nav-link-profile"));
        profilePageLink.click();
    }
}
