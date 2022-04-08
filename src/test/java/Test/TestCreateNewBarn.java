package Test;



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
        BarnItem item = new BarnItem(
                "Barn 2023",
                "150000",
                "250",
                "01/01/2023",
                "31/12/2023");
        farmerPage.barnTab().createBarn(item);

        // step-6
        BarnItem newItem = farmerPage.barnTab().getLastItem();

        // step-7
        Assertions.assertEquals(item.getName(), newItem.getName());
        Assertions.assertEquals(item.getInitialCarrot(), newItem.getInitialCarrot());
        Assertions.assertEquals(item.getEndDate(), newItem.getEndDate());
    }

    @AfterAll
    public static void clearAll(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
