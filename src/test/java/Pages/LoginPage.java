package Pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;

    // Locators
    By usernamePath = By.xpath("//input[@id='floatingInput']");
    By passwordPath = By.xpath("//input[@id='floatingPassword']");
    By loginBtnPath = By.xpath("//button[normalize-space()='Sign in']");
    By failLoginPath = By.xpath("//div[@role='alert']");

    // Elements
    WebElement usernameElement, passwordElement, loginBtnElement, failLoginElement;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private void assignElement(){
        usernameElement = driver.findElement(usernamePath);
        passwordElement = driver.findElement(passwordPath);
        loginBtnElement = driver.findElement(loginBtnPath);      
        //failLoginElement = this.driver.findElement(failLoginPath);  
    }

    public void login(String username, String password){  
        driver.get(Global.WebURL);        
        assignElement(); 

        usernameElement.sendKeys(username);
        passwordElement.sendKeys(password);
        loginBtnElement.click();
    }

    public void assertErrorMessage(){
        WebDriverWait waitWeb = new WebDriverWait(driver, Duration.ofSeconds(10));

        waitWeb.until(ExpectedConditions.visibilityOfElementLocated(failLoginPath));
        failLoginElement = driver.findElement(failLoginPath); 

        Assert.assertEquals(failLoginElement.getText(), "Incorrect username or password!");
    }
}
