package Test;

import Pages.Global;
import Pages.LoginPage;
import Pages.Manager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestManagerCannotBuy {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static Manager manager;

    @BeforeAll
    public static void beforeLogin(){
        Global.Init();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        manager = new Manager(driver);
        loginPage.login("user_manager_agus","1234");
    }

    @Test
    public void cannotBuyItem(){
        manager.bazaarTab().buyItem(4,"2");
        Assertions.assertEquals(false, manager.bazaarTab().isButtonEnabled());
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
