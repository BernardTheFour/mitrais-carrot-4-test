package Pages;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    protected WebDriver driver;

    // Locators for navbar (every page have navbar)
    By userDescPath = By.xpath("//div[@class='dropdown-menu show dropdown-menu-end']//div//div[1]");

    By profileDropdownPath = By.xpath("//a[@id='basic-nav-dropdown']");

    By fullnamePath(String fullname) {
        return By.xpath("//h3[normalize-space()='" + fullname + "']");
    }

    // Elements
    protected WebElement fullnameElement,
            userDescElement,
            profileDropdownElement;
            //logoutElement;

    // method
    protected void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    protected void assignPageElement(String fullname) {
        profileDropdownElement = driver.findElement(profileDropdownPath);
        profileDropdownElement.click();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(fullnamePath(fullname)));
        fullnameElement = driver.findElement(fullnamePath(fullname));
        userDescElement = driver.findElement(userDescPath);
    }

    public void assertUser(
            String expectedFullname,
            String expectedGrade,
            String expectedPosition,
            String expectedRole) {

        // ex: "StaffJunior Programmer, Training and DevelopmentStaff"
        String userDesc = expectedFullname
                + expectedGrade + ", "
                + expectedPosition
                + expectedRole;

        Assert.assertEquals(expectedFullname, fullnameElement.getText());
        Assert.assertEquals(userDesc, userDescElement.getText().replace("\n", "")); // delete newline
    }
}
