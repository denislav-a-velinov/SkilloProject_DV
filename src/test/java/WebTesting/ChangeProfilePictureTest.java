package WebTesting;

import factory.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;

public class ChangeProfilePictureTest extends TestObject {
    @DataProvider(name = "getUser")
    public Object[][] getUsers() {
        File postPic = new File("src//test//resources//upload//profileimage.jpg");
        String caption = "Testing upload file";
        return new Object[][]{
                {"dvelinov1","dvelinov1pass", "5627", postPic, caption}
        };
    }
    @Test(dataProvider = "getUser")
    public void testChangeProfilePhoto(String username, String password, String userId, File postPic, String caption) throws InterruptedException {
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        ChangePhoto changePhoto = new ChangePhoto(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not the Login page.");

        loginPage.completeSignIn(username, password);

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page is not profile page" + userId + "user");

        changePhoto.uploadProfilePhoto(postPic);
        changePhoto.typeProfileCaption(caption);
        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is not the Profile page");

        Thread.sleep(3000);

        String message = loginPage.BoxMessageAction.getText();
        System.out.println(message);
    }
}