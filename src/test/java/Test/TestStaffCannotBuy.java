package Test;

import Pages.Global;
import Pages.LoginPage;
import Pages.Staff;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestStaffCannotBuy {
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
    @Order(1)
    public void qtyMoreThanStock(){
        staff.bazaarTab().buyItem(4, "9999"); // qty more than stock
        Assertions.assertEquals(false, staff.bazaarTab().isButtonEnabled());
        staff.bazaarTab().closeModalPay();
    }

    @Test
    @Order(2)
    public void qtyLessThanOne(){
        staff.bazaarTab().buyItem(4,"0");
        Assertions.assertEquals(false,staff.bazaarTab().isButtonEnabled());
        staff.bazaarTab().closeModalPay();
    }

    @Test
    @Order(3)
    public void totalPayMoreThanCarrot(){
        staff.bazaarTab().buyItem(4,"100");
        Assertions.assertEquals(false,staff.bazaarTab().isButtonEnabled());
        staff.bazaarTab().closeModalPay();
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
