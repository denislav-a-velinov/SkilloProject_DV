package factory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProfilePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4200/users/";
    private final WebDriver webDriver;
    @FindBy(xpath = "//app-post[2]//*[@class='post-img']")
    private WebElement buttonHoverDelete;
    @FindBy(xpath = "//app-post[2]//*[@class='post-img']")
    private WebElement buttonHoverLike;
    @FindBy(xpath = "//*[@class='delete-ask']//*[text()='Delete post']")
    private WebElement buttonDelete;
    @FindBy(xpath = "//*[@class='delete-confirm']//button[text()='Yes']")
    private WebElement buttonConfirmDelete;
    @FindBy(xpath = "//*[@id='toast-container']//*[@class='toast-message ng-star-inserted']")
    private WebElement deleteMessageBox;
    @FindBy(xpath = "//*[@id='toast-container']//*[@class='toast-message ng-star-inserted']")
    private WebElement deleteBoxMessage;
    @FindBy(xpath = "//div[@class='post-modal-container']//*[@class='like far fa-heart fa-2x']")
    private WebElement likeButton;
    @FindBy(xpath = "//div[@class='post-modal-container']//*[@class='like far fa-heart fa-2x']")
    private WebElement likedButton;

    public ProfilePage(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlContains(PAGE_URL));
    }
    public boolean isUrlLoaded(String userId) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlToBe(PAGE_URL + userId));
    }
    public void clickButtonHoverDelete() {
        buttonHoverDelete.click();
    }
    public void clickButtonHoverLike() {
        buttonHoverLike.click();
    }
    public void clickButtonDelete() {
        buttonDelete.click();
    }
    public void clickButtonLike() {
        likeButton.click();
    }
    public void clickButtonConfirmDelete() {
        buttonConfirmDelete.click();
    }
    public WebElement getDeleteMessageBox() {
        return deleteMessageBox;
    }
    public String checkDeleteMessage() {
        Actions loginMessageBox = new Actions(webDriver);
        loginMessageBox.moveToElement(deleteBoxMessage).perform();
        return deleteBoxMessage.getText();
    }
    public void clickLikeButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(likeButton));
        likeButton.click();
    }
    public boolean isPostLiked() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(likedButton));
        return true;
    }
}