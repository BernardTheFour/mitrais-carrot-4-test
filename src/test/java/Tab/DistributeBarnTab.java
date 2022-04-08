package Tab;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DistributeBarnTab implements IHompageTab {
    WebDriver driver;

    // Locators
    By distributeTabPath = By.xpath("//h3[normalize-space()='Distribute']']");

    public DistributeBarnTab(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void focus() {
        // TODO Auto-generated method stub
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(distributeTabPath));

        driver.findElement(distributeTabPath).click();
    }
}