package WebTesting;

//import object.*;
import factory.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;

public class NewPostTest extends TestObject {
    @DataProvider(name = "getUser")
    public Object[][] getUsers() {
        File postPic = new File("src//test//resources//upload//testimage.jpg");
        String caption = "This is the new test image to test the submit post option.";
        return new Object[][]{

                {"dvelinov1", "dvelinov1pass", "5627", postPic, caption}
        };
    }
    @Test(dataProvider = "getUser")
    public void testCreatePost(String username, String password, String userId, File postPic, String caption) throws InterruptedException {
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        HomePage homePage = new HomePage(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        NewPost newPost = new NewPost(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");

        loginPage.completeSignIn(username, password);

        // The usage of the below wait is not a good practice:
        Thread.sleep(3000);

        header.clickNewPost();

        newPost.uploadPicture(postPic);
        String actualImageText = newPost.getUploadedImageText();
        Assert.assertTrue(newPost.isImageUploaded("testimage.jpg"),"Image is not uploaded successfully.");
        Assert.assertEquals(actualImageText, "testimage.jpg", "Incorrect image is uploaded.");

        newPost.typePostCaption(caption);
        newPost.clickCreatePost();

        WebElement createNewPostMessageBox = webDriver.findElement(By.xpath("//*[@id='toast-container']//*[@class='toast-message ng-star-inserted']"));
        Actions newPostMessageBox = new Actions(webDriver);
        newPostMessageBox.moveToElement(createNewPostMessageBox).perform();

        String actualDeleteMessage = createNewPostMessageBox.getText();

        if (actualDeleteMessage.equals("Post created!")) {
            System.out.println(createNewPostMessageBox.getText());
        }
    }
}