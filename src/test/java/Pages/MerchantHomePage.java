package Pages;

import Dto.BazaarItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MerchantHomePage {
    WebDriver driver;

    // Locators
    By merchTabPath = By.xpath("//h3[normalize-space()='Merchandise']");
    By createBtnPath = By.xpath("//button[normalize-space()='Add Item']");
    By itemNamePath = By.xpath("//input[@name='ItemName']");
    By itemDescPath = By.xpath("//input[@name='ItemDescription']");
    By itemImgPath = By.xpath("//input[@name='ItemImg']");
    By itemPricePath = By.xpath("//input[@name='ItemPrice']");
    By itemStockPath = By.xpath("//input[@name='ItemStock']");
    By itemBazaarPath = By.xpath("//input[@name='BazaarId']");
    By itemStartDatePath = By.xpath("//input[@name='ItemStartDate']");
    By itemEndDatePath = By.xpath("//input[@name='ItemEndDate']");
    By submitBtnPath = By.xpath("//button[normalize-space()='Submit']");

    public MerchantHomePage(WebDriver driver) {
        this.driver = driver;
    }

    private void fillCreateForm(BazaarItem item) {
        driver.findElement(itemNamePath).sendKeys(item.getName());
        driver.findElement(itemDescPath).sendKeys(item.getDescription());
        driver.findElement(itemImgPath).sendKeys(item.getImage());
        driver.findElement(itemPricePath).sendKeys(item.getPrice());
        driver.findElement(itemStockPath).sendKeys(item.getStock());
        driver.findElement(itemBazaarPath).sendKeys(item.getBazaarId());
        driver.findElement(itemStartDatePath).sendKeys(item.getStartDate());
        driver.findElement(itemEndDatePath).sendKeys(item.getEndDate());
    }

    public void goToMerchTab() {
        WebDriverWait waitWeb = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitWeb.until(ExpectedConditions.visibilityOfElementLocated(merchTabPath));

        driver.findElement(merchTabPath).click();
    }

    public void createItem(BazaarItem item) {
        driver.findElement(createBtnPath).click();
        fillCreateForm(item);
        driver.findElement(submitBtnPath).click();
    }
}
