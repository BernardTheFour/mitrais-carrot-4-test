package Tab;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ShareCarrotTab implements IHompageTab {
    WebDriver driver;

    // Locators
    By shareCarrotTabPath = By.xpath("//h3[normalize-space()='Share Carrot']");
    By shareCarrotBtnPath = By.xpath("(//button[contains(text(),'Share Carrot')])[1]");
    By recipientPath = By.xpath("//select[@class='form-select']");
    By carrotAmountPath = By.xpath("//input[@name='CarrotAmount']");
    By descriptionPath = By.xpath("//textarea[@name='Description']");
    By submitBtnPath = By.xpath("//button[normalize-space()='Submit']");

    // Distribution table locator
    By lastTblItemRewardedToPath=By.xpath("(//div[@role='table'])[1]/div[2]/div[last()]/div[@data-column-id='1']/div");
    By lastTblItemQtyPath=By.xpath("(//div[@role='table'])[1]/div[2]/div[last()]/div[@data-column-id='2']/div");
    By lastTblItemDescPath=By.xpath("(//div[@role='table'])[1]/div[2]/div[last()]/div[@data-column-id='3']/div");
    By managerDistLastPageBtnPath = By.xpath("//button[@id='pagination-last-page']");



    public ShareCarrotTab(WebDriver driver){
        this.driver=driver;
    }

    @Override
    public void focus() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(shareCarrotTabPath));
        driver.findElement(shareCarrotTabPath).click();
    }

    // click on Share Carrot Button
    public void clickShareCarrotBtn(){
        driver.findElement(shareCarrotBtnPath).click();
    }

    // select recipient
    public void recipientDropDownList(String recipient){
        Select drop = new Select(driver.findElement(recipientPath));
        drop.selectByVisibleText(recipient);
    }

    // Set Carrot Amount
    public void setCarrotAmount(String carrot){
        driver.findElement(carrotAmountPath).sendKeys(carrot);
    }

    // Set Description
    public void setDescription(String desc){
        driver.findElement(descriptionPath).sendKeys(desc);
    }

    // Click submit button
    public void clickSubmitButton(){
        driver.findElement(submitBtnPath).click();
    }

    public void assertShareCarrotSuccess(String rewardedTo, String qty, String desc){
        WebElement managerTblLastPageBtnElement = driver.findElement(managerDistLastPageBtnPath);
        int tableRow =  driver.findElements(By.xpath("(//div[@role='table'])[1]/div[2]/*")).size();
        if(tableRow > 10){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", managerTblLastPageBtnElement);
            managerTblLastPageBtnElement.click();
        }

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(lastTblItemRewardedToPath));

        Assertions.assertEquals(rewardedTo,driver.findElement(lastTblItemRewardedToPath).getText());
        Assertions.assertEquals(qty, driver.findElement(lastTblItemQtyPath).getText());
        Assertions.assertEquals(desc, driver.findElement(lastTblItemDescPath).getText());
    }

    public void assertShareValue(String qty){
        WebElement managerTblLastPageBtnElement = driver.findElement(managerDistLastPageBtnPath);
        int tableRow =  driver.findElements(By.xpath("(//div[@role='table'])[1]/div[2]/*")).size();
        if(tableRow > 10){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", managerTblLastPageBtnElement);
            managerTblLastPageBtnElement.click();
        }

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(lastTblItemRewardedToPath));
        Assertions.assertNotEquals(qty, driver.findElement(lastTblItemQtyPath).getText());
    }

    public void assertShareNoReceiver(String rewardedTo){
        WebElement managerTblLastPageBtnElement = driver.findElement(managerDistLastPageBtnPath);
        int tableRow =  driver.findElements(By.xpath("(//div[@role='table'])[1]/div[2]/*")).size();
        if(tableRow > 10){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", managerTblLastPageBtnElement);
            managerTblLastPageBtnElement.click();
        }

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(lastTblItemRewardedToPath));
        Assertions.assertNotEquals(rewardedTo, driver.findElement(lastTblItemRewardedToPath).getText());
    }


}
