package Test;

import Dto.BazaarItem;
import Pages.Global;
import Pages.LoginPage;
import Pages.Merchant;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCreateNewBazaarItem {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static Merchant merchantPage;

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
    @BeforeAll
    public static void beforeLogin() {
        Global.Init();

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        merchantPage = new Merchant(driver);

        // STEP-1
        loginPage.login("user_merchant", "1234");

        // STEP-2
        merchantPage.goToMerchTab();
    }

    @Test
    public void createItemSuccess() throws InterruptedException {
        // STEP-3,4,5
        BazaarItem item = new BazaarItem(
            "New Item",
            "Description of new item",
            "http://tny.im/rQb",
            "2500","9", "1",
            "07/04/2022",
            "07/05/2022"
        );
        merchantPage.createItem(item);

        // STEP-6
        Thread.sleep(1000);
        BazaarItem newItem = merchantPage.getLastItem();

        // STEP-7

        Assertions.assertEquals(item.getName(), newItem.getName());
        Assertions.assertEquals(item.getPrice(), newItem.getPrice());
        Assertions.assertEquals(item.getStock(), newItem.getStock());
    }

    // After all tests
    @AfterAll
    public static void clearAll() {
        // STEP-8
        driver.manage().deleteAllCookies();
        driver.quit();

    }
}
