package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
// this import for drop-down select selenium
import org.openqa.selenium.support.ui.Select;

public class ManagerShareCarrot {
    WebDriver driver;

    @FindBy(xpath = "(//button[@id='react-aria7282893369-8-tab-share'])[1]")
    WebElement shareCarrotTab;

    @FindBy(xpath = "(//button[contains(text(),'Share Carrot')])[1]")
    WebElement shareCarrotBtn;

    // Recipient drop-down
    @FindBy(xpath = "//select[@class='form-select']")
    WebElement recipient;


    @FindBy(xpath = "//input[@name='CarrotAmount']")
    WebElement carrotAmount;

    @FindBy(xpath = "//textarea[@name='Description']")
    WebElement description;

    @FindBy(xpath = "//button[normalize-space()='Submit']")
    WebElement submitBtn;


    public ManagerShareCarrot(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    // select recipient
    public void recipientDropDownList(int index){
        Select drop = new Select(recipient);
        drop.selectByIndex(index);
    }

    // Set Carrot Amount
    public void setCarrotAmount(String carrot){
        carrotAmount.sendKeys(carrot);
    }

    // Set Description
    public void setDescription(String desc){
        description.sendKeys(desc);
    }

    // Click submit button
    public void clickSubmitButton(){
        submitBtn.click();
    }
}
