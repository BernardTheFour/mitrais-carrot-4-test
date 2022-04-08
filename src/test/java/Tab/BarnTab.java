package Tab;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Dto.BarnItem;

public class BarnTab {
    WebDriver driver;

    // Locator for create barn
    By barnTabPath = By.xpath("//h3[normalize-space()='Barn']");
    By addBarnPath = By.xpath("//button[normalize-space()='Add Barn']");
    By submitBtnPath = By.xpath("//button[normalize-space()='Submit']");

    By itemNamePath = By.xpath("//input[@name='BarnName']");
    By initCarrotPath = By.xpath("//input[@name='CarrotAmount']");
    By birthCarrotPath = By.xpath("//input[@name='BirthdayCarrot']");
    By startDatePath = By.xpath("//input[@name='ItemStartDate']");
    By endDatePath = By.xpath("//input[@name='ItemEndDate']");

    public BarnTab(WebDriver driver) {
        this.driver = driver;
    }

    public void focus() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(barnTabPath));

        driver.findElement(barnTabPath).click();
    }

    public void createBarn(BarnItem item) {
        driver.findElement(addBarnPath).click();
        fillCreateForm(item);
        driver.findElement(submitBtnPath);
    }

    private void fillCreateForm(BarnItem item) {
        driver.findElement(itemNamePath).sendKeys(item.getName());
        driver.findElement(initCarrotPath).sendKeys(item.getInitialCarrot());
        driver.findElement(birthCarrotPath).sendKeys(item.getBirthdayCarrot());
        driver.findElement(startDatePath).sendKeys(item.getStartDate());
        driver.findElement(endDatePath).sendKeys(item.getEndDate());
    }
}
