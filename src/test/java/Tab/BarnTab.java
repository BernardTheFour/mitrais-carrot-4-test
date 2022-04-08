package Tab;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Dto.BarnItem;
import Pages.Global;

public class BarnTab implements IHompageTab {
    WebDriver driver;

    // Locator for create barn
    By barnTabPath = By.xpath("//h3[normalize-space()='Barn']");
    By addBarnPath = By.xpath("//button[normalize-space()='Add Barn']");
    By submitBtnPath = By.xpath("//button[normalize-space()='Submit']");

    // Create new barn locators
    By itemNamePath = By.xpath("//input[@name='BarnName']");
    By initCarrotPath = By.xpath("//input[@name='CarrotAmount']");
    By birthCarrotPath = By.xpath("//input[@name='BirthdayCarrot']");
    By startDatePath = By.xpath("//input[@name='ItemStartDate']");
    By endDatePath = By.xpath("//input[@name='ItemEndDate']");

    // Barn table locators
    String barnTablePath = "(//table[@class='table table-bordered table striped'])[1]/tbody/tr";
    // By barnTblLastPageBtnPath = By.xpath(xpathExpression);
    // Barn last row
    By lastTblBarnNamePath = By
            .xpath("(//table[@class='table table-bordered table striped'])[1]/tbody/tr[last()]/td[1]");
    By lastTblAvailablePath = By
            .xpath("(//table[@class='table table-bordered table striped'])[1]/tbody/tr[last()]/td[2]");;
    By lastTblExpiredPath = By
            .xpath("(//table[@class='table table-bordered table striped'])[1]/tbody/tr[last()]/td[4]");;

    public BarnTab(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void focus() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(barnTabPath));

        driver.findElement(barnTabPath).click();
    }

    public void createBarn(BarnItem item) {
        driver.findElement(addBarnPath).click();
        fillCreateForm(item);
        driver.findElement(submitBtnPath).click();
        ;
    }

    private void fillCreateForm(BarnItem item) {
        driver.findElement(itemNamePath).sendKeys(item.getName());
        driver.findElement(initCarrotPath).sendKeys(item.getInitialCarrot());
        driver.findElement(birthCarrotPath).sendKeys(item.getBirthdayCarrot());
        driver.findElement(startDatePath).sendKeys(item.getStartDate());
        driver.findElement(endDatePath).sendKeys(item.getEndDate());
    }

    public BarnItem getLastItem() {
        BarnItem barnItem = new BarnItem();

        int rowCount = driver.findElements(By.xpath(barnTablePath + "/*")).size();
        WebElement farmerTblLastRowElement = driver.findElement(By.xpath(barnTablePath + "[last()]"));

        if (rowCount > 10) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", farmerTblLastRowElement);
        }

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(lastTblBarnNamePath));

        barnItem.setName(driver.findElement(lastTblBarnNamePath).getText());
        barnItem.setInitialCarrot(driver.findElement(lastTblAvailablePath).getText());
        barnItem.setEndDate(driver.findElement(lastTblExpiredPath).getText());

        return barnItem;
    }
}
