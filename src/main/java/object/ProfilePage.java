package object;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProfilePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4200/users/";
    private final WebDriver webDriver;
    public ProfilePage(WebDriver driver){
        this.webDriver = driver;
    }

    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlContains(PAGE_URL));
    }
    public boolean isUrlLoaded(String userId){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlToBe(PAGE_URL+userId));
    }
    public void clickButtonHoverLike() {
        WebElement buttonHoverLike = webDriver.findElement(By.xpath("//app-post[2]//*[@class='post-img']"));
        buttonHoverLike.click();
    }
    public void clickButtonHoverDelete() {
        WebElement buttonHoverLike = webDriver.findElement(By.xpath("//app-post[2]//*[@class='post-img']"));
        buttonHoverLike.click();
    }
    public void clickButtonDelete() {
        WebElement buttonDelete = webDriver.findElement(By.xpath("//*[@class='delete-ask']//*[text()='Delete post']"));
        buttonDelete.click();
    }
    public void clickButtonLike() {
        WebElement likeButton = webDriver.findElement(By.xpath("//div[@class='post-modal-container']//*[@class='like far fa-heart fa-2x']"));
        likeButton.click();
    }
    public void clickButtonConfirmDelete() {
        WebElement buttonConfirmDelete = webDriver.findElement(By.xpath("//*[@class='delete-confirm']//button[text()='Yes']"));
        buttonConfirmDelete.click();
    }
    public WebElement getDeleteMessageBox() {
        return webDriver.findElement(By.xpath("//*[@id='toast-container']//*[@class='toast-message ng-star-inserted']"));
    }
    public boolean isPostLiked() {
        WebElement likedButton = webDriver.findElement(By.xpath("//div[@class='post-modal-container']//*[@class='like far fa-heart fa-2x liked']"));
        return likedButton.isDisplayed();
    }
    public boolean checkDeleteMessage() {
        WebElement loginMessageBox = webDriver.findElement(By.xpath("//*[@id='toast-container']//*[@class='toast-message ng-star-inserted']"));
        return loginMessageBox.isDisplayed();
    }
}
