package WebTesting;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.N;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
//Below we can choose if we want to use POM or PageFactory options (Currently using PageFactory):
import object.*;
//import factory.*;
import org.openqa.selenium.WebDriver;

public class NewPostTest extends TestObject2 {
    ChromeDriver webDriver;
    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @DataProvider(name = "getUser")
    public Object[][] getUsers() {
        File postPic = new File("src//test//resources//upload//testimage.jpg");
        String caption = "This is the new test image to test the submit post option.";
        return new Object[][]{

                {"dvelinov1", "dvelinov1pass", "5627", postPic, caption}
        };
    }
    @Test(dataProvider = "getUser")
    public void testCreatePost(String username, String password, String userId, File postPic, String caption) {
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        HomePage homePage = new HomePage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        NewPost newPost = new NewPost(webDriver);
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
        loginPage.clickSignInButton();
        //THE BELOW ONE IS FOR FACTORY:
//        loginPage.clickSignIn();

        WebElement loginMessageBox = webDriver.findElement(By.xpath("//*[@id='toast-container']//*[@class='toast-message ng-star-inserted']"));
        Actions actionsForElements = new Actions(webDriver);
        actionsForElements.moveToElement(loginMessageBox).perform();

        String expectedMessage = "Successful login!";
        String actualMessage = loginMessageBox.getText();
        Assert.assertEquals(actualMessage, expectedMessage, "The actual message is not matching the expected one!");

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is not the Profile page.");

        header.clickNewPost();
//        Assert.assertTrue(newPost.isUrlLoaded(), "Current page is not the Profile page.");

        newPost.uploadPicture(postPic);
        String actualImageText = newPost.getUploadedImageText();
        Assert.assertTrue(newPost.isImageUploaded("testimage.jpg"),"Image is not uploaded successfully.");
        Assert.assertEquals(actualImageText, "testimage.jpg", "Incorrect image is uploaded.");

        newPost.typePostCaption(caption);
        newPost.clickCreatePost();

        Assert.assertTrue(profilePage.isUrlLoaded(userId),"Current page is not the profile page for" + userId + "user.");
    }
    @AfterMethod(alwaysRun = true)
    public void afterTest(){
        if (webDriver != null) {
            webDriver.close();
        }
}}