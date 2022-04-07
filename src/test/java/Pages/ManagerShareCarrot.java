package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
// this import for drop-down select selenium
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ManagerShareCarrot {
    WebDriver driver;

//    @FindBy(xpath = "(//h3[normalize-space()='Share Carrot']")
//    WebElement shareCarrotTab;
//    @FindBy(xpath = "(//button[contains(text(),'Share Carrot')])[1]")
//    WebElement shareCarrotBtn;
//    // Recipient drop-down
//    @FindBy(xpath = "//select[@class='form-select']")
//    WebElement recipient;
//    @FindBy(xpath = "//input[@name='CarrotAmount']")
//    WebElement carrotAmount;
//    @FindBy(xpath = "//textarea[@name='Description']")
//    WebElement description;
//    @FindBy(xpath = "//button[normalize-space()='Submit']")
//    WebElement submitBtn;

    // Locators
    By shareCarrotTab = By.xpath("//h3[normalize-space()='Share Carrot']");
    By shareCarrotBtn = By.xpath("(//button[contains(text(),'Share Carrot')])[1]");
    By recipient = By.xpath("//select[@class='form-select']");
    By carrotAmount = By.xpath("//input[@name='CarrotAmount']");
    By description = By.xpath("//textarea[@name='Description']");
    By submitBtn = By.xpath("//button[normalize-space()='Submit']");

    public ManagerShareCarrot(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    // click on Share Carrot tab
    public void clickShareCarrotTab(){
        WebDriverWait waitWeb = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitWeb.until(ExpectedConditions.visibilityOfElementLocated(shareCarrotTab));
        driver.findElement(shareCarrotTab).click();
    }

    // click on Share Carrot Button
    public void clickShareCarrotBtn(){

        driver.findElement(shareCarrotBtn).click();
    }

    // select recipient
    public void recipientDropDownList(int index){
        Select drop = new Select(driver.findElement(recipient));
        drop.selectByIndex(index);
    }

    // Set Carrot Amount
    public void setCarrotAmount(String carrot){

        driver.findElement(carrotAmount).sendKeys(carrot);
    }

    // Set Description
    public void setDescription(String desc){

        driver.findElement(description).sendKeys(desc);
        //description.sendKeys(desc);
    }

    // Click submit button
    public void clickSubmitButton(){
        driver.findElement(submitBtn).click();
        //submitBtn.click();
    }
}
