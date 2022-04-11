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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestUpdateBazaarItem {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static Merchant merchantPage;


    @BeforeAll
    public static void beforeLogin() {
        Global.Init();

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        merchantPage = new Merchant(driver);

        loginPage.login("user_merchant", "1234");
    }

    @BeforeEach
    public void beforeUpdate() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, -document.body.scrollHeight)");

        merchantPage.merchTab().focus();
    }

    @Test
    @Order(1)
    public void updateItemSuccess() {
        Faker faker = new Faker();
        BazaarItem item = new BazaarItem(
                faker.commerce().productName(),
                faker.company().catchPhrase(),
                faker.company().logo(),
                String.valueOf(faker.number().numberBetween(500, 5000)),
                String.valueOf(faker.number().numberBetween(1, 20)),
                "08/04/2022",
                "08/05/2022"
        );

        merchantPage.merchTab().updateItem(item);
        BazaarItem updatedItem = merchantPage.merchTab().getLastItem();

        Assertions.assertEquals(item.getName(), updatedItem.getName());
        Assertions.assertEquals(item.getPrice(), updatedItem.getPrice());
        Assertions.assertEquals(item.getStock(), updatedItem.getStock());
    }

    @Test
    @Order(2)
    public void updateItemFailed() {
        merchantPage.merchTab().updateEmptyItem();
        String expectedErrorMsg = "All field must be filled!";
        merchantPage.merchTab().assertErrorMessage(expectedErrorMsg);
    }

    @AfterAll
    public static void clearAll() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
