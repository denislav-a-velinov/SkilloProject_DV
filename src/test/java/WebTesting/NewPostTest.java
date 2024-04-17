package WebTesting;

import factory.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;

public class NewPostTest extends TestObject {
    @DataProvider(name = "getUser")
    public Object[][] getUsers() {
        File postPic = new File("src//test//resources//upload//testimage.jpg");
        String caption = "This is a test post with a test file.";
        return new Object[][]{
                {"dvelinov1", "dvelinov1pass", "5627", postPic, caption}
        };
    }
    @Test(dataProvider = "getUser")
    public void testCreatePost(String username, String password, String userId, File postPic, String caption){
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        NewPost newPost = new NewPost(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not the Login page.");

        loginPage.completeSignIn(username, password);

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId),"The current page is not the profile page for "+ userId + "user.");

        header.clickNewPost();
        Assert.assertTrue(newPost.isNewPostLoaded(),"The New Post form is not loaded.");

        newPost.uploadPicture(postPic);
        String actualImageText = newPost.getUploadedImageText();
        Assert.assertTrue(newPost.isImageUploaded("testimage.jpg"),"The image is not uploaded successfully.");
        Assert.assertEquals(actualImageText, "testimage.jpg", "The image uploaded is incorrect.");

        newPost.typePostCaption(caption);
        newPost.clickCreatePost();
        Assert.assertTrue(profilePage.isUrlLoaded(), "The current page is not the profile page.");

        loginPage.checkLoginMessage();
        Assert.assertTrue(loginPage.isLoginMessageDisplayed(), "The New Post message is not displayed.");

        String message = loginPage.BoxMessageAction.getText();
        System.out.println(message);
        }
    }