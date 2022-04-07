package Pages;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;

    // Locators
    By usernamePath = By.xpath("//input[@id='floatingInput']");
    By passwordPath = By.xpath("//input[@id='floatingPassword']");
    By loginBtnPath = By.xpath("//button[normalize-space()='Sign in']");
    By failLoginPath = By.xpath("//div[@role='alert']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void login(String username, String password){  
        driver.get(Global.WebURL);    

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(Global.LoginURL));  

        driver.findElement(usernamePath).sendKeys(username);
        driver.findElement(passwordPath).sendKeys(password);
        driver.findElement(loginBtnPath).click();
    }

    public void assertErrorMessage(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.visibilityOfElementLocated(failLoginPath));
        
        WebElement failLoginElement = driver.findElement(failLoginPath); 
        Assertions.assertEquals(failLoginElement.getText(), "Incorrect username or password!");
    }
}
