package Test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Dto.BarnItem;
import Pages.Farmer;
import Pages.Global;
import Pages.LoginPage;

public class TestCannotCreateBarn {
    /**
     * TEST SCENARIO
     * 1. Login as a farmer
     * 2. Click barn tab
     * 3. Click add barn button
     * 4. Fill form, some of them not filled
     * 5. Check if submit button is clickable
     * 6. Clear cookies, quit driver
     */

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static Farmer farmerPage;

    @BeforeAll
    public static void precondition() {
        Global.Init();

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        farmerPage = new Farmer(driver);

        // step-1
        loginPage.login("user_farmer", "1234");
        // step-2
        farmerPage.barnTab().focus();
    }

    @Test
    public void noName() {
        // step-3 to step-4
        BarnItem item = new BarnItem(
                "",
                "150000",
                "250",
                "01/01/2023",
                "31/12/2023");
        Assertions.assertEquals(false, farmerPage.barnTab().canSubmit(item));
        farmerPage.barnTab().closeBarnPopUp();
    }

    @Test
    public void unsupportedInitialCarrot() {
        // null initial carrot
        BarnItem item = new BarnItem(
                "Barn 2",
                "",
                "250",
                "01/01/2023",
                "31/12/2023");
        // negative number
        BarnItem item2 = new BarnItem(
                "Barn 2",
                "-1",
                "250",
                "01/01/2023",
                "31/12/2023");
        Assertions.assertEquals(false, farmerPage.barnTab().canSubmit(item));
        farmerPage.barnTab().closeBarnPopUp();
        Assertions.assertEquals(false, farmerPage.barnTab().canSubmit(item2));
        farmerPage.barnTab().closeBarnPopUp();
    }

    @Test
    public void unsupportedBirthdayCarrot() {
        // null initial carrot
        BarnItem item = new BarnItem(
                "Barn 2",
                "100000",
                "",
                "01/01/2023",
                "31/12/2023");
        // negative number
        BarnItem item2 = new BarnItem(
                "Barn 2",
                "100000",
                "-1",
                "01/01/2023",
                "31/12/2023");
        Assertions.assertEquals(false, farmerPage.barnTab().canSubmit(item));
        farmerPage.barnTab().closeBarnPopUp();
        Assertions.assertEquals(false, farmerPage.barnTab().canSubmit(item2));
        farmerPage.barnTab().closeBarnPopUp();
    }

    @Test
    public void unsupportedDate() {
        // start date
        BarnItem item = new BarnItem(
                "Barn 2",
                "100000",
                "200",
                "",
                "31/12/2023");
        // end date
        BarnItem item2 = new BarnItem(
                "Barn 2",
                "100000",
                "50",
                "01//2023",
                "");                
        Assertions.assertEquals(false, farmerPage.barnTab().canSubmit(item));
        farmerPage.barnTab().closeBarnPopUp();
        Assertions.assertEquals(false, farmerPage.barnTab().canSubmit(item2));
        farmerPage.barnTab().closeBarnPopUp();
    }   

    @AfterAll
    public static void closeBrowser(){        
        //Terminate the WebDriver
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
