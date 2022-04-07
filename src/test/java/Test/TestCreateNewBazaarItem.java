package Test;

import Dto.BazaarItem;
import Pages.Global;
import Pages.LoginPage;
import Pages.MerchantHomePage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCreateNewBazaarItem {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static MerchantHomePage merchantPage;

    /* Test Scenario
    * 1. Login into Mitrais Carrot as Merchant
    * 2. Click merchandise tab
    * 3. Click add item button
    * 4. Fill in form, record form input
    * 5. Click submit button
    * 6. Record newly inserted data in the table
    * 7. Compare 4 & 6
    * 8. Clear cookies, quit driver
    * */

    // Before all tests
    @BeforeClass
    public static void beforeLogin() {
        Global.Init();

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        merchantPage = new MerchantHomePage(driver);

        // STEP-1
        loginPage.login("user_merchant", "1234");
        // STEP-2
        merchantPage.goToMerchTab();
    }

//     Before each tests
//    @Before
//    public static void goToLoginPage() {
//        // STEP-2
//        loginPage.login("user_merchant", "1234");
//    }
    @Test
    public void createItemSuccess() {
        // STEP-3
        BazaarItem item = new BazaarItem(
            "New Item",
            "Description of new item",
            "http://tny.im/rQb",
            "2500","9", "1",
            "07/04/2022",
            "07/05/2022"
        );
        merchantPage.createItem(item);
    }

    // After all tests
    @AfterClass
    public static void clearAll() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}