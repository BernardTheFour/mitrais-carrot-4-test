package Tab;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Dto.ShareBarn;
import Pages.Global;

public class DistributeBarnTab implements IHompageTab {
    WebDriver driver;

    // Page locator
    By distributeTabPath = By.xpath("//*[contains(text(), 'Distribute')][1]");

    // Locators for distribute carrot
    By shareBtnPath = By.xpath("//button[normalize-space()='Share Carrot']");
    By availableCarrotPath = By
            .xpath("//body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/section[1]/table[1]/tbody[1]/tr[2]/td");
    By receiverCarrotPath = By.xpath("//select[@class='form-select']");
    By carrotAmountPath = By.xpath("//input[@name='CarrotAmount']");
    By submitBtnPath = By.xpath("//button[normalize-space()='Submit']");
    By closeBtnPath = By.xpath("//button[@aria-label='Close']");

    public DistributeBarnTab(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void focus() {
        new WebDriverWait(driver, Global.Timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(distributeTabPath));

        driver.findElement(distributeTabPath).click();
    }

    public void closeSharePopUp() {
        if (driver.findElement(closeBtnPath).isDisplayed()) {
            driver.findElement(closeBtnPath).click();
        }
    }

    public void shareCarrot(ShareBarn item) {
        driver.findElement(shareBtnPath).click();
        fillShareForm(item);
        driver.findElement(submitBtnPath).click();
    }

    private void fillShareForm(ShareBarn item) {
        Select drop = new Select(driver.findElement(receiverCarrotPath));
        drop.selectByVisibleText(item.getReceiver());
        driver.findElement(carrotAmountPath).sendKeys(item.getCarrot());
    }

    private boolean canSubmit(ShareBarn item) {
        driver.findElement(shareBtnPath).click();
        fillShareForm(item);

        return driver.findElement(submitBtnPath).isEnabled();
    }

    public void assertCanSubmitForm(boolean expected, ShareBarn item) {
        Assertions.assertEquals(expected, canSubmit(item));
    }
}
