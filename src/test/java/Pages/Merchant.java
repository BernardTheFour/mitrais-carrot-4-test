package Pages;

import Dto.BazaarItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Merchant extends HomePage {

    // Locators
    By merchTabPath = By.xpath("//h3[normalize-space()='Merchandise']");
    By createBtnPath = By.xpath("//button[normalize-space()='Add Item']");
    By submitBtnPath = By.xpath("//button[normalize-space()='Submit']");    
    
    By itemNamePath = By.xpath("//input[@name='ItemName']");
    By itemDescPath = By.xpath("//input[@name='ItemDescription']");
    By itemImgPath = By.xpath("//input[@name='ItemImg']");
    By itemPricePath = By.xpath("//input[@name='ItemPrice']");
    By itemStockPath = By.xpath("//input[@name='ItemStock']");
    By itemBazaarPath = By.xpath("//input[@name='BazaarId']");
    By itemStartDatePath = By.xpath("//input[@name='ItemStartDate']");
    By itemEndDatePath = By.xpath("//input[@name='ItemEndDate']");

    // Merchandise Table Locators
    By lastTblItemNamePath = By.xpath("(//div[@role='table'])[1]/div[2]/div[last()]/div[@data-column-id='1']/div");
    By lastTblItemPricePath = By.xpath("(//div[@role='table'])[1]/div[2]/div[last()]/div[@data-column-id='2']/div");
    By lastTblItemStockPath = By.xpath("(//div[@role='table'])[1]/div[2]/div[last()]/div[@data-column-id='3']/div");

    public Merchant(WebDriver driver) {
        super.driver = driver;
    }

    private void fillCreateForm(BazaarItem item) {
        super.driver.findElement(itemNamePath).sendKeys(item.getName());
        super.driver.findElement(itemDescPath).sendKeys(item.getDescription());
        super.driver.findElement(itemImgPath).sendKeys(item.getImage());
        super.driver.findElement(itemPricePath).sendKeys(item.getPrice());
        super.driver.findElement(itemStockPath).sendKeys(item.getStock());
        super.driver.findElement(itemBazaarPath).sendKeys(item.getBazaarId());
        super.driver.findElement(itemStartDatePath).sendKeys(item.getStartDate());
        super.driver.findElement(itemEndDatePath).sendKeys(item.getEndDate());
    }

    public void goToMerchTab() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(merchTabPath));

        super.driver.findElement(merchTabPath).click();
    }

    public void createItem(BazaarItem item) {
        super.driver.findElement(createBtnPath).click();
        fillCreateForm(item);
        super.driver.findElement(submitBtnPath).click();
    }

    public BazaarItem getLastItem() {
        BazaarItem newItem = new BazaarItem();
        newItem.setName(driver.findElement(lastTblItemNamePath).getText());
        newItem.setPrice(driver.findElement(lastTblItemPricePath).getText());
        newItem.setStock(driver.findElement(lastTblItemStockPath).getText());

        return newItem;
    }
}
