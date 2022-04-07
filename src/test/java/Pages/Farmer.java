package Pages;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Farmer extends HomePage {

    public Farmer(WebDriver driver) {
        super.setDriver(driver);
    }

    public void assertHomePage(
            String expectedFullname,
            String expectedGrade,
            String expectedPosition,
            String expectedRole) {

        // wait until page finish loading
        new WebDriverWait(super.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(Global.FarmerHomePage));     

        super.assignPageElement(expectedFullname);
        
        String expectedURL = Global.WebURL + Global.FarmerHomePage;
        Assert.assertEquals(expectedURL, super.driver.getCurrentUrl());

        super.assertUser(expectedFullname, expectedGrade, expectedPosition, expectedRole);
    }
}
