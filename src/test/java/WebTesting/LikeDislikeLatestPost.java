package WebTesting;

//import object.*;
import factory.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

public class LikeDislikeLatestPost extends TestObject {
    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        return new Object[][]{
                {"dvelinov1","dvelinov1pass", "5627"},
        };
    }
    @Test(dataProvider = "getUser")
    public void likeDislikeLatestPostTest(String username, String password, String userId) throws InterruptedException {
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        HomePage homePage = new HomePage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");

        loginPage.completeSignIn(username, password);

        // The usage of the below wait is not a good practice:
        Thread.sleep(3000);

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page is not profile page" + userId + "user");

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
}
