package object;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;

public class NewPost {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4200/posts/create";
    private final WebDriver webDriver;

    private WebElement uploadFile;
    private WebElement uploadPictureText;
    private WebElement postCaption;
    private WebElement createPostButton;

    public NewPost(WebDriver driver) {
        this.webDriver = driver;
    }
    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlContains(PAGE_URL));
    }
    public void uploadPicture(File file) {
        uploadFile = webDriver.findElement(By.xpath("//*[@class='form-group']/input[@type='file']"));
        uploadFile.sendKeys(file.getAbsolutePath());
    }
    public boolean isNewPostLoaded() {
        return webDriver.findElement(By.xpath("//h3[contains(text(),'New Post')]")).isDisplayed();
    }
    public boolean isImageUploaded(String fileName) {
        String actualText = uploadPictureText.getAttribute("placeholder");
        return actualText.equals(fileName);
    }
    public String uploadedImageText() {
        return uploadPictureText.getAttribute("placeholder");
    }
    public void typePostCaption(String text) {
        postCaption = webDriver.findElement(By.id("post-caption"));
        postCaption.sendKeys(text);
    }
    public void clickCreatePost() {
        createPostButton = webDriver.findElement(By.id("create-post-button"));
        createPostButton.click();
    }
}