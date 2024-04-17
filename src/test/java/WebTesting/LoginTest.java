package WebTesting;

import factory.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;

public class LoginTest extends TestObject {
    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        return new Object[][]{
                {"dvelinov1", "dvelinov1pass", "5627"},
        };
    }
    @Test(dataProvider = "getUser")
    public void loginTest(String username, String password, String userId){
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
        Assert.assertTrue(loginPage.isCheckedRememberMe(), "The Remember me checkbox is checked.");

        loginPage.clickSignInButton();
        Assert.assertTrue(homePage.isUrlLoaded(),"The Home page is not loaded.");

        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page in not profile page for " + userId + " user");

        String message = loginPage.BoxMessageAction.getText();
        System.out.println(message);
    }
}