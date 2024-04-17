package factory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    public static final String HOME_URL = "http://training.skillo-bg.com:4200/posts/all";
    private final WebDriver webDriver;

    @FindBy(xpath = "//*[@class='container']//app-post-detail[1]//*[@class='post-feed-img']/img")
    private WebElement firstPostPicture;
    @FindBy(xpath = "//div[@class='post-modal-container']//*[@class='like far fa-heart fa-2x']")
    private WebElement likeButton;

    public HomePage(WebDriver driver){
        this.webDriver = driver;
    }
    public void navigateTo(){
        this.webDriver.get(HOME_URL);
    }
    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlToBe(HOME_URL));
    }
}