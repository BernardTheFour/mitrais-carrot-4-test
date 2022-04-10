package Test;



import com.github.javafaker.Faker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Dto.BarnItem;
import Pages.Global;
import Pages.LoginPage;
import Pages.Farmer;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCreateNewBarn {
    /**Test Scenario
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

    @BeforeAll
    public static void precondition() {
        Global.Init();

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        farmerPage = new Farmer(driver);

        // step-1
        loginPage.login("user_farmer", "1234");          
    }

    @Test
    @Order(1)
    public void createBarnSuccess() {
        // step-2
        farmerPage.barnTab().focus();
        
        // step-3 to step-5
        Faker faker = new Faker();
        BarnItem item = new BarnItem(
                faker.ancient().god(),
                String.valueOf(faker.number().numberBetween(10000, 100000)),
                String.valueOf(faker.number().numberBetween(500, 5000)),
                "01/01/2023",
                "31/12/2023");
        farmerPage.barnTab().createBarn(item);

        // step-6
        BarnItem newItem = farmerPage.barnTab().getLastItem();

        // step-7
        farmerPage.barnTab().assertEquals(item, newItem);
    }

    @AfterAll
    public static void clearAll(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
