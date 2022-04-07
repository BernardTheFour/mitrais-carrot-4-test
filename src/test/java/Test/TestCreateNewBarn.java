package Test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Dto.BarnItem;
import Pages.Farmer;
import Pages.Global;
import Pages.LoginPage;

public class TestCreateNewBarn {
    /**
     * Test Scenario
     * 1. Login as a farmer
     * 2. Click barn tab
     * 3. Click add barn button
     * 4. Fill form, record form input
     * 5. Click submit button
     * 6. Record newly inserted data in the table
     * 7. Compare point 4 and 6
     * 8. Clear cookies, quit driver
     */

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static Farmer farmerPage;

    @BeforeClass
    public static void beforeLogin() {
        Global.Init();

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        farmerPage = new Farmer(driver);

        // step-1
        loginPage.login("user_farmer", "1234");
        // step-2
        farmerPage.goToBarnTab();
    }

    @Test
    public static void createBarnSuccess() {
        // step-3 to step-5
        BarnItem barn = new BarnItem(
                "Barn 2023",
                "150000",
                "250",
                "01/01/2023",
                "31/12/2023");
        farmerPage.createBarn(barn);
    }

    @AfterClass
    public static void clearAll(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
