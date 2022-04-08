package Pages;

import org.junit.jupiter.api.Assertions;
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
    By logoutPath = By.xpath("//a[normalize-space()='Logout']");

    By fullnamePath(String fullname) {
        return By.xpath("//h3[normalize-space()='" + fullname + "']");
    }

    // Elements
    protected WebElement fullnameElement,
            userDescElement,
            profileDropdownElement,
            logoutElement;

    // method
    protected void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    protected void assignPageElement() {
        if (profileDropdownElement != null) {
            return;
        }

        new WebDriverWait(driver, Global.Timeout)
                .until(ExpectedConditions.urlContains("home"));

        profileDropdownElement = driver.findElement(profileDropdownPath);
        profileDropdownElement.click();
        
        userDescElement = driver.findElement(userDescPath);
        logoutElement = driver.findElement(logoutPath);
        profileDropdownElement.click();
    }

    protected void assignProfileElement(String fullname) {
        if (!logoutElement.isDisplayed()) {
            profileDropdownElement.click();
        }

        fullnameElement = driver.findElement(fullnamePath(fullname));
    }

    public void logout() {
        assignPageElement();

        if (!logoutElement.isDisplayed()) {
            profileDropdownElement.click();
        }

        logoutElement.click();
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

        Assertions.assertEquals(expectedFullname, fullnameElement.getText());
        Assertions.assertEquals(userDesc, userDescElement.getText().replace("\n", "")); // delete newline
    }

    public void assertHomePage(
            String expectedFullname,
            String expectedGrade,
            String expectedPosition,
            String expectedRole) {

        // wait until page finish loading
        new WebDriverWait(driver, Global.Timeout)
                .until(ExpectedConditions.urlContains(expectedRole.toLowerCase()));

        assignPageElement();
        assignProfileElement(expectedFullname);

        String expectedURL = Global.WebURL + "home/" + expectedRole.toLowerCase();
        Assertions.assertEquals(expectedURL, driver.getCurrentUrl());

        assertUser(expectedFullname, expectedGrade, expectedPosition, expectedRole);
    }
}
