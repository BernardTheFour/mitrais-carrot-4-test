package Test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Dto.ShareBarn;
import Pages.Farmer;
import Pages.Global;
import Pages.LoginPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCannotShareBarn {
    /**
     * TEST SCENARIO
     * 1. Login as farmer
     * 2. To distribute tab
     * 3. Click share carrot button
     * 4. Fill/empty receiver name
     * 5. Fill/empty carrot amount
     * 6. Check click submit button
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
        farmerPage.distributeTab().focus();
    }

    private void assertAndClose(ShareBarn share) {
        farmerPage.distributeTab().assertCanSubmitForm(false, share);
        farmerPage.distributeTab().closeSharePopUp();
    }

    @Test
    public void noReceiver() {
        // no receiver
        ShareBarn share = new ShareBarn("", "150");
        assertAndClose(share);
    }

    @Test
    public void unsupportedCarrot() {
        // no carrot
        ShareBarn share = new ShareBarn("Manager Alfi", "");
        // less than 1 carrot
        ShareBarn share2 = new ShareBarn("Manager Alfi", "0");
        // more than available carrot
        int availableCarrot = farmerPage.distributeTab().getAvailableCarrot() + 1;
        ShareBarn share3 = new ShareBarn("Manager Alfi", String.valueOf(availableCarrot));

        assertAndClose(share);
        assertAndClose(share2);
        assertAndClose(share3);
    }

    @AfterAll
    public static void closeBrowser(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
