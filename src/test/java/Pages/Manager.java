package Pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
// this import for drop-down select selenium
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class Manager extends HomePage {
    // Locators
    By shareCarrotTabPath = By.xpath("//h3[normalize-space()='Share Carrot']");
    By shareCarrotBtnPath = By.xpath("(//button[contains(text(),'Share Carrot')])[1]");
    By recipientPath = By.xpath("//select[@class='form-select']");
    By carrotAmountPath = By.xpath("//input[@name='CarrotAmount']");
    By descriptionPath = By.xpath("//textarea[@name='Description']");
    By submitBtnPath = By.xpath("//button[normalize-space()='Submit']");
    By managerDistLastPagePath = By.xpath("//button[@id='pagination-last-page']");

    public Manager(WebDriver driver){
        super.driver=driver;
    }

    // click on Share Carrot tab
    public void clickShareCarrotTab(){
        WebDriverWait waitWeb = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitWeb.until(ExpectedConditions.visibilityOfElementLocated(shareCarrotTabPath));
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
        //description.sendKeys(desc);
    }

    // Click submit button
    public void clickSubmitButton(){
        driver.findElement(submitBtnPath).click();
        //submitBtn.click();
    }

    public void assertShareCarrotSuccess(String rewardedTo, String qty, String desc){
        WebDriverWait waitWeb = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitWeb.until(ExpectedConditions.visibilityOfElementLocated(shareCarrotTabPath));

        // check if last page button can be clicked
        WebElement distributionLastPageBtn = driver.findElement(managerDistLastPagePath);
        WebElement table = driver.findElement(By.xpath("//div[@role='table']"));
        //locate rows of table
        List<WebElement> rows_table = table.findElements(By.className("rdt_TableRow"));
        // check if row == 10
        if(rows_table.size() == 10){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", distributionLastPageBtn);
            distributionLastPageBtn.click();
        }
        int last_row = rows_table.size()-1;
        // get the column of the last row
        List<WebElement> column_row = rows_table.get(last_row).findElements(By.className("sc-hKMtZM"));
        // Assert rewarded to as expected
        Assertions.assertEquals(rewardedTo, column_row.get(0).getText());
        // Assert quantity to as expected
        Assertions.assertEquals(qty,column_row.get(1).getText());
        // Assert desc to as expected
        Assertions.assertEquals(desc, column_row.get(2).getText());
    }

    public void assertShareValue(String qty){
        WebDriverWait waitWeb = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitWeb.until(ExpectedConditions.visibilityOfElementLocated(shareCarrotTabPath));

        // check if last page button can be clicked
        WebElement distributionLastPageBtn = driver.findElement(managerDistLastPagePath);
        WebElement table = driver.findElement(By.xpath("//div[@role='table']"));
        //locate rows of table
        List<WebElement> rows_table = table.findElements(By.className("rdt_TableRow"));
        // check if row == 10
        if(rows_table.size() == 10){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", distributionLastPageBtn);
            distributionLastPageBtn.click();
        }
        int last_row = rows_table.size()-1;
        // get the column of the last row
        List<WebElement> column_row = rows_table.get(last_row).findElements(By.className("sc-hKMtZM"));
        // Assert quantity to as expected
        Assertions.assertNotEquals(qty,column_row.get(1).getText());
    }

    public void assertShareNoReceiver(String rewardedTo){
        WebDriverWait waitWeb = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitWeb.until(ExpectedConditions.visibilityOfElementLocated(shareCarrotTabPath));

        // check if last page button can be clicked
        WebElement distributionLastPageBtn = driver.findElement(managerDistLastPagePath);
        WebElement table = driver.findElement(By.xpath("//div[@role='table']"));
        //locate rows of table
        List<WebElement> rows_table = table.findElements(By.className("rdt_TableRow"));
        // check if row == 10
        if(rows_table.size() == 10){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", distributionLastPageBtn);
            distributionLastPageBtn.click();
        }
        int last_row = rows_table.size()-1;
        // get the column of the last row
        List<WebElement> column_row = rows_table.get(last_row).findElements(By.className("sc-hKMtZM"));
        // Assert rewarded to as expected
        Assertions.assertNotEquals(rewardedTo, column_row.get(0).getText());
    }
}
