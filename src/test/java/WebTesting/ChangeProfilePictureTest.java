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

public class ChangeProfilePictureTest extends TestObject {
    @DataProvider(name = "getUser")
    public Object[][] getUsers() {
        File postPic = new File("src//test//resources//upload//testimage.jpg");
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
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");

        loginPage.completeSignIn(username, password);

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page is not profile page" + userId + "user");

        changePhoto.uploadProfilePhoto(postPic);

        Thread.sleep(3000);

        WebElement changeProfilePictureBoxMessage = webDriver.findElement(By.xpath("//*[@id='toast-container']//*[@class='toast-message ng-star-inserted']"));
        Actions loginMessageBox = new Actions(webDriver);
        loginMessageBox.moveToElement(changeProfilePictureBoxMessage).perform();

        String actualLoginMessage = changeProfilePictureBoxMessage.getText();

        if (actualLoginMessage.equals("Profile picture updated")) {
            System.out.println(changeProfilePictureBoxMessage.getText());
        }
    }
}