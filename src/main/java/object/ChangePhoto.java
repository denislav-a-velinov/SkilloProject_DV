package object;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class ChangePhoto {
    private final WebDriver webDriver;
    public ChangePhoto(WebDriver driver){
        this.webDriver = driver;
    }
    public void uploadProfilePhoto(File file){
        WebElement uploadFile = webDriver.findElement(By.xpath("//*[@id='upload-img']"));
        uploadFile.sendKeys(file.getAbsolutePath());
    }
}