package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;

    // Locators
    By usernamePath = By.xpath("//input[@id='floatingInput']");
    By passwordPath = By.xpath("//input[@id='floatingPassword']");
    By loginBtnPath = By.xpath("//button[normalize-space()='Sign in']");
    //By failLoginPath = By.xpath("//div[@role='alert']");

    // Elements
    WebElement usernameElement, passwordElement, loginBtnElement, failLoginElement;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private void assignElement(){
        usernameElement = this.driver.findElement(usernamePath);
        passwordElement = this.driver.findElement(passwordPath);
        loginBtnElement = this.driver.findElement(loginBtnPath);
        //failLoginElement = this.driver.findElement(failLoginPath);
    }

    public void login(String username, String password){  
        driver.get(Global.WebURL);        
        assignElement(); 

        usernameElement.sendKeys(username);
        passwordElement.sendKeys(password);

        loginBtnElement.click();
    }

    public void assertErrorMessage(String expectedMessage){
        Assert.assertEquals(failLoginElement.getText(), expectedMessage);
    }
}
