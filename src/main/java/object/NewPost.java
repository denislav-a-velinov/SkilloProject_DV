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
    private final By newPostTitleLocator = By.xpath("//h3[text()='Post a picture to share with your awesome followers']");
    private final By uploadPictureTextLocator = By.xpath("//input[@class='form-control input-lg'][@type='text']");
    private final By postCaptionLocator = By.name("caption");
    private final By createPostButtonLocator = By.id("create-post");
    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlContains(PAGE_URL));
    }

    public NewPost(WebDriver driver) {
        this.webDriver = driver;
    }
    public boolean isNewPostPageLoaded() {
        return webDriver.findElement(newPostTitleLocator).isDisplayed();
    }
    public boolean isImageUploaded(String fileName) {
        String actualText = webDriver.findElement(uploadPictureTextLocator).getAttribute("placeholder");
        return actualText.equals(fileName);
    }
    public void uploadPicture(File file) {
        WebElement uploadFile = webDriver.findElement(By.xpath("//*[@class='form-group']/input[@type='file']"));
        uploadFile.sendKeys(file.getAbsolutePath());
    }
    public String getUploadedImageText() {
        return webDriver.findElement(uploadPictureTextLocator).getAttribute("placeholder");
    }
    public void typePostCaption(String text) {
        webDriver.findElement(postCaptionLocator).sendKeys(text);
    }
    public void clickCreatePost() {
        webDriver.findElement(createPostButtonLocator).click();
    }
}
