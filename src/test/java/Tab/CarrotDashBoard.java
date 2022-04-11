package Tab;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Pages.Global;

public class CarrotDashBoard implements IHompageTab {

    WebDriver driver;

    public CarrotDashBoard(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By bazaarTab = By.xpath("//*[contains(text(), 'Bazaar')]");
    By carrotBalance = By.xpath("//div[2]//div[1]//div[2]//div");
    By historyDetailPath = By.xpath("//button[normalize-space()='Detail']");

    @Override
    public void focus() {
        new WebDriverWait(driver, Global.Timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(bazaarTab));
        driver.findElement(bazaarTab).click();
    }

    public int getCarrotBalance() {
        String text = driver.findElement(carrotBalance).getText();
        text = text.replace("\n", "");
        text = text.replace(" ", "");
        text = text.replace("Balance", "");
        text = text.replace("carrot(s)", "");
        return Integer.parseInt(text);
    }

    public void assertCarrotBalance(boolean isEqual, int expected, int actual) {
        if (isEqual) {
            Assertions.assertEquals(expected, actual);
            return;
        }

        Assertions.assertNotEquals(expected, actual);
    }
}
