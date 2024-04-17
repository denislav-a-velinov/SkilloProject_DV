package WebTesting;

import factory.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

public class LikeLatestPost extends TestObject {
    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        return new Object[][]{
                {"dvelinov1","dvelinov1pass", "5627"},
        };
    }
    @Test(dataProvider = "getUser")
    public void likeLatestPostTest(String username, String password, String userId) throws InterruptedException {
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not the Login page.");

        loginPage.completeSignIn(username, password);

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page is not the profile page for " + userId + "user");

        Thread.sleep(3000);

        profilePage.clickButtonHoverLike();

        profilePage.clickLikeButton();
        Assert.assertTrue(profilePage.isPostLiked(),"The post is not Liked!");

        String message = loginPage.BoxMessageAction.getText();
        System.out.println(message);
    }
}
