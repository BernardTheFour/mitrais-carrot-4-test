package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Dto.BarnItem;

public class Farmer extends HomePage {

    // Locator for create barn
    By barnTabPath = By.xpath("//button[@id='react-aria9740901346-40-tab-barn']");
    By addBarnPath = By.xpath("//button[normalize-space()='Add Barn']");
    By submitBtnPath = By.xpath("//button[normalize-space()='Submit']");

    By itemNamePath = By.xpath("//input[@name='BarnName']");
    By initCarrotPath = By.xpath("//input[@name='CarrotAmount']");
    By birthCarrotPath = By.xpath("//input[@name='BirthdayCarrot']");
    By startDatePath = By.xpath("//input[@name='ItemStartDate']");
    By endDatePath = By.xpath("//input[@name='ItemEndDate']");

    public Farmer(WebDriver driver) {
        super.setDriver(driver);
    } 
    
    public void goToBarnTab(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.visibilityOfElementLocated(barnTabPath));

        super.driver.findElement(barnTabPath).click();
    }

    public void createBarn(BarnItem item){
        super.driver.findElement(addBarnPath).click();
        fillCreateForm(item);
        super.driver.findElement(submitBtnPath);
    }

    private void fillCreateForm(BarnItem item){
        super.driver.findElement(itemNamePath).sendKeys(item.getName());
        super.driver.findElement(initCarrotPath).sendKeys(item.getInitialCarrot());
        super.driver.findElement(birthCarrotPath).sendKeys(item.getBirthdayCarrot());
        super.driver.findElement(startDatePath).sendKeys(item.getStartDate());
        super.driver.findElement(endDatePath).sendKeys(item.getEndDate());
    }
}
