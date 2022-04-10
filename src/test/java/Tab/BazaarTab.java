package Tab;

import Pages.Global;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BazaarTab implements IHompageTab {
    WebDriver driver;

    // Locators
    By bazaarTabPath = By.xpath("//h3[normalize-space()='Bazaar']");
    By carrotHistoryPath = By.xpath("//button[normalize-space()='Detail']");
    By earnHistoryPath = By.xpath("//button[normalize-space()='EARN']");
    By shareHistoryPath = By.xpath("//button[normalize-space()='SHARE']");
    By purchaseHistoryPath = By.xpath("//button[normalize-space()='PURCHASE']");
    By lastPageBtnPath = By.xpath("//button[@id='pagination-last-page']//*[name()='svg']");
    By itemNamePath = By.xpath("//div[@class='modal-title h4']");

    // Buying item
    String itemPath = "//button";
    By amountFieldPath = By.xpath("//input[@placeholder='Amount']"); // need to clear field first by using find ele().clear()
    By payBtnPath = By.xpath("//div[@class='modal-footer']//button[@type='button']");
    By closePayBtnPath = By.xpath("//button[@aria-label='Close']");

    // Carrot history table locator
    By purchaseColPath = By.xpath("(//div[@role='table'])[1]/div[2]/div[last()]/div[@data-column-id='2']/div");
    By qtyColPath = By.xpath("(//div[@role='table'])[1]/div[2]/div[last()]/div[@data-column-id='3']/div");

    public BazaarTab(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void focus() {
        new WebDriverWait(driver, Global.Timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(bazaarTabPath));

        driver.findElement(bazaarTabPath).click();
    }

    // get locator by index
    public By itemBuyBtnPath(int id){
        return By.xpath(itemPath+"[@id='"+id+"']");
    }

    public void buyItem(int id, String qty){
        new WebDriverWait(driver, Global.Timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(itemBuyBtnPath(id)));
        WebElement itemBuyBtnElement = driver.findElement(itemBuyBtnPath(id));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", itemBuyBtnElement);
        driver.findElement(amountFieldPath).clear(); // clear the field for the default value
        driver.findElement(amountFieldPath).sendKeys(qty);
        if(isButtonEnabled()){
            driver.findElement(payBtnPath).click();
        }

    }

    public boolean isButtonEnabled(){
        return driver.findElement(payBtnPath).isEnabled();
    }

    public void closeModalPay(){
        driver.findElement(closePayBtnPath).click();
    }

    public void assertPurchaseHistory(String item, String qty){
        WebElement carrotHistoryBtnElement = driver.findElement(carrotHistoryPath);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", carrotHistoryBtnElement);
        driver.findElement(purchaseHistoryPath).click();

        // check if purchase history last page btn can be clicked
        new WebDriverWait(driver, Global.Timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(lastPageBtnPath));
        WebElement lastPageBtnElement = driver.findElement(lastPageBtnPath);
        int tableRow = driver.findElements(By.xpath("(//div[@role='table'])[1]/div[2]/*")).size();
        if(tableRow>10){
            js.executeScript("arguments[0].click();", lastPageBtnElement);
        }
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(purchaseColPath));

        Assertions.assertEquals(item, driver.findElement(purchaseColPath).getText());
        Assertions.assertEquals(qty, driver.findElement(qtyColPath).getText());
    }



}
