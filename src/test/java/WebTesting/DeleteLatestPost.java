package WebTesting;

import factory.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DeleteLatestPost extends TestObject {
    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        return new Object[][]{
                {"dvelinov1","dvelinov1pass", "5627"},
        };
    }
    @Test(dataProvider = "getUser")
    public void deleteLatestPostTest (String username, String password, String userId){
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not the Login page.");

        loginPage.completeSignIn(username, password);

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page is not the profile page for " + userId + "user");

        profilePage.clickButtonHoverDelete();
        profilePage.clickButtonDelete();
        profilePage.clickButtonConfirmDelete();

        profilePage.checkDeleteMessage();
        Assert.assertTrue(loginPage.isLoginMessageDisplayed(), "Delete");

        String message = loginPage.BoxMessageAction.getText();
        System.out.println(message);
    }
}

