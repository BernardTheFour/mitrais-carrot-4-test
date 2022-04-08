package Tab;

import Dto.BazaarItem;
import Pages.Global;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MerchandiseTab implements IHompageTab {
    WebDriver driver;

    // Locators
    By merchTabPath = By.xpath("//h3[normalize-space()='Merchandise']");
    By createBtnPath = By.xpath("//button[normalize-space()='Add Item']");
    By submitBtnPath = By.xpath("//button[normalize-space()='Submit']");

    // Create Item Form Locators
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
    By merchTblLastPageBtnPath = By.xpath("(//button[@id='pagination-last-page'])[1]");

    public MerchandiseTab(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void focus() {
        new WebDriverWait(driver, Global.Timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(merchTabPath));

        driver.findElement(merchTabPath).click();
    }

    public void createItem(BazaarItem item) {
        driver.findElement(createBtnPath).click();
        fillCreateForm(item);
        driver.findElement(submitBtnPath).click();
    }

    public BazaarItem getLastItem() {
        BazaarItem newItem = new BazaarItem();
        WebElement merchTblLastPageBtnElement = driver.findElement(merchTblLastPageBtnPath);
        int tableRow =  driver.findElements(By.xpath("(//div[@role='table'])[1]/div[2]/*")).size();

        if (tableRow == 10) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", merchTblLastPageBtnElement);
            js.executeScript("arguments[0].click();", merchTblLastPageBtnElement);
        }

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(lastTblItemNamePath));
        newItem.setName(driver.findElement(lastTblItemNamePath).getText());
        newItem.setPrice(driver.findElement(lastTblItemPricePath).getText());
        newItem.setStock(driver.findElement(lastTblItemStockPath).getText());

        return newItem;
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

}
