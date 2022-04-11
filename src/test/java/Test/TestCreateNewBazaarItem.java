package Test;

import Dto.BazaarItem;
import Pages.Global;
import Pages.LoginPage;
import Pages.Merchant;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCreateNewBazaarItem {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static Merchant merchantPage;

    /** Test Scenario
    * 1. Login into Mitrais Carrot as Merchant
    * 2. Click merchandise tab
    * 3. Click add item button
    * 4. Fill in form, record form input
    * 5. Click submit button
    * 6. Record newly inserted data in the table
    * 7. Compare 4 & 6
    * 8. Clear cookies, quit driver
    */

    @BeforeAll
    public static void beforeLogin() {
        Global.Init();

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        merchantPage = new Merchant(driver);

        // STEP-1
        loginPage.login("user_merchant", "1234");
    }

    @BeforeEach
    public void beforeCreate() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, -document.body.scrollHeight)");

        // STEP-2
        merchantPage.merchTab().focus();
    }

    @Test
    public void createItemSuccess() {
        Faker faker = new Faker();
        // STEP-3,4,5
        BazaarItem item = new BazaarItem(
            faker.commerce().productName(),
            faker.company().catchPhrase(),
            faker.company().logo(),
            String.valueOf(faker.number().numberBetween(500, 5000)),
            String.valueOf(faker.number().numberBetween(1, 20)),
            "07/04/2022",
            "07/05/2022"
        );

        merchantPage.merchTab().createItem(item);

        // STEP-6
        BazaarItem newItem = merchantPage.merchTab().getLastItem();

        // STEP-7
        Assertions.assertEquals(item.getName(), newItem.getName());
        Assertions.assertEquals(item.getPrice(), newItem.getPrice());
        Assertions.assertEquals(item.getStock(), newItem.getStock());
    }

    @Test
    public void createItemFailed() {
        merchantPage.merchTab().createEmptyItem();
        String expectedErrorMsg = "All field must be filled!";
        merchantPage.merchTab().assertErrorMessage(expectedErrorMsg);
    }

    @AfterAll
    public static void clearAll() {
        // STEP-8
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
