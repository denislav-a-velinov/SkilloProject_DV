package WebTesting;
import io.github.bonigarcia.wdm.WebDriverManager;
import object.*;
//import factory.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;


public class LikeDislikeLatestPost extends TestObject2{
    // Below we can choose which WebBrowser to use for the test (currently using Chrome):
    ChromeDriver webDriver;
    //FirefoxDriver webDriver;
    //EdgeDriver webDriver;
    //SafariDriver webDriver;
    private final boolean userReg = false;
    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        return new Object[][]{
                {"dvelinov1","dvelinov1pass", "5627"},
        };
    }
    @Test(dataProvider = "getUser")
    public void likeDislikeLatestPostTest(String username, String password, String userId){
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
        loginPage.clickSignInButton();
        //THE BELOW ONE IS FOR FACTORY:
//        loginPage.clickSignIn();

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is not the Profile page.");

        WebElement profilePageLink = webDriver.findElement(By.id("nav-link-profile"));
        profilePageLink.click();

        String profileURL = ProfilePage.PAGE_URL+userId;

        webDriver.get(profileURL);
        //Previously used locator: /html/body/app-root/div[2]/app-profile/div/div[2]/app-profile-posts-section/div/div[2]/div/app-post-list/div/div/app-post[1]
        WebElement latestPost = webDriver.findElement(By.xpath("//app-post[1]"));

        Actions actions = new Actions(webDriver);
        actions.moveToElement(latestPost).perform();

        //Previously used locator: /html/body/app-root/div[2]/app-profile/div/div[2]/app-profile-posts-section/div/div[2]/div/app-post-list/div/div/app-post[1]/div/div[2]/div[1]/i
        WebElement buttonHoverLike = webDriver.findElement(By.xpath("//app-post[1]/div/div[2]/div[1]/i"));
        buttonHoverLike.click();

        //Previously used locator: /html/body/ngb-modal-window/div/div/app-post-modal/div/div[2]/div[3]/div/div/div/div[1]/i[1]
        WebElement buttonLike = webDriver.findElement(By.xpath("//div[3]/div/div/div/div[1]/i[1]"));
        buttonLike.click();

        WebElement likeMessageBox = webDriver.findElement(By.xpath("//*[@id='toast-container']//*[@class='toast-message ng-star-inserted']"));
        Actions actionsForElements = new Actions(webDriver);
        actionsForElements.moveToElement(likeMessageBox).perform();

        String expectedLikeMessage = "Post liked";

        String expectedDislikeMessage = "Post disliked";
        String actualMessage = likeMessageBox.getText();

        if (expectedLikeMessage == "Post liked"){
            System.out.println(actualMessage);
        } else {
            System.out.println(expectedDislikeMessage);
        }

    }
    @AfterMethod(alwaysRun = true)
    public void afterTest(){
        if (webDriver != null) {
            webDriver.close();
        }
    }
}
