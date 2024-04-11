package WebTesting;
//import object.*;
import factory.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;

public class ChangeProfilePictureTest extends TestObject2 {

    @DataProvider(name = "getUser")
    public Object[][] getUsers() {
        File postPic = new File("src//test//resources//upload//testimage.jpg");
        String caption = "Testing upload file";
        return new Object[][]{
                {"dvelinov1","dvelinov1pass", "5627", postPic, caption}
        };
    }

    @Test(dataProvider = "getUser")
    public void testChangeProfilePhoto(String username, String password, String userId, File postPic, String caption) {
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        ChangePhoto changePhoto = new ChangePhoto(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");

        loginPage.completeSingIn(username, password);

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page is not profile page" + userId + "user");
        //Assert.assertTrue(false);

        changePhoto.uploadProfilePhoto(postPic);

        try {
            Thread.sleep(3000); // 5000 milliseconds = 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement changeProfilePicture = webDriver.findElement(By.xpath("//*[@id='toast-container']//*[@class='toast-message ng-star-inserted']"));
        Actions actionsForElements = new Actions(webDriver);
        actionsForElements.moveToElement(changeProfilePicture).perform();

        String expectedProfilepictureText = "Profile picture updated";

        if (expectedProfilepictureText.equals("Profile picture updated")) {
            System.out.println(changeProfilePicture.getText());
        }
    }
}