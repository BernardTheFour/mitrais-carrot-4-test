package Test;

import Pages.Global;
import Pages.LoginPage;
import Pages.Staff;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestStaffBuyItem {

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static Staff staff;

    @BeforeAll
    public static void beforeLogin(){
        Global.Init();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        staff= new Staff(driver);
        loginPage.login("user_staff", "1234");
    }

    @Test
    public void buyItemSuccess(){
        staff.bazaarTab().buyItem(4, "1");
        staff.bazaarTab().assertPurchaseHistory("Mi 9", "1");
    }

    @AfterEach
    public void clearCache(){
        //Delete cookies to logout user
        driver.manage().deleteAllCookies();
    }
    //After all tests
    @AfterAll
    public static void closeBrowser(){
        //Terminate the WebDriver
        driver.quit();
    }
}
