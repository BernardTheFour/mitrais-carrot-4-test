package Test;

import com.github.javafaker.Faker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Dto.ShareBarn;
import Pages.Farmer;
import Pages.Global;
import Pages.LoginPage;
import Pages.Manager;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestShareBarnCarrot {
    /**
     * TEST SCENARIO
     * 1. Login as carrot receiver
     * 2. Get carrot balance
     * 3. Logout
     * 4. Login as farmer
     * 5. To distribute tab
     * 6. Click share carrot button
     * 7. Input receiver name
     * 8. Fill carrot amount
     * 9. Click submit button
     * 10. Logout
     * 11. Login as carrot receiver
     * 12. Check carrot balance + step 8
     */

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static Farmer farmerPage;
    private static Manager managerPage;

    @BeforeAll
    public static void precondition() {
        Global.Init();

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        farmerPage = new Farmer(driver);
        managerPage = new Manager(driver);        
    }

    @Test
    @Order(1)
    public void shareBarnCarrotSuccess() {
        // step-1
        loginPage.login("user_manager_alfi", "1234");

        // step-2
        managerPage.carroDashBoard().focus();
        int balance = managerPage.carroDashBoard().getCarrotBalance();

        // step-3
        managerPage.logout();

        // step-4
        loginPage.login("user_farmer", "1234");

        // step-5
        farmerPage.distributeTab().focus();

        // step-6 to step-9
        Faker faker = new Faker();
        int randNumber = (int) faker.number().numberBetween(10, 50);

        ShareBarn share = new ShareBarn("Manager Alfi",
                String.valueOf(randNumber));
        farmerPage.distributeTab().shareCarrot(share);

        // step-10
        farmerPage.logout();

        // step-11
        loginPage.login("user_manager_alfi", "1234");

        // step-12
        managerPage.carroDashBoard().focus();
        int balanceNew = managerPage.carroDashBoard().getCarrotBalance();
        managerPage.carroDashBoard().assertCarrotBalance(balance + randNumber, balanceNew);
    }

    @AfterAll
    public static void closeBrowser(){
         // Terminate the WebDriver
         driver.manage().deleteAllCookies();
         driver.quit();
    }
}
