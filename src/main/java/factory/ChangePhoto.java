package factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class ChangePhoto {
    private final WebDriver webDriver;
    @FindBy(xpath = "//*[@id='upload-img']")
    private WebElement uploadFile;

    public ChangePhoto(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }
    public void uploadProfilePhoto(File file){
        ////*[@class='form-group']//div/input[@type='file'] - hidden
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
//        uploadFile.click();
        uploadFile.sendKeys(file.getAbsolutePath());
    }
}