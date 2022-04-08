package Test;

import Pages.Global;
import Pages.LoginPage;
import Pages.Manager;
import org.junit.jupiter.api.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestManagerShareCarrot {
    static WebDriver driver;
    static LoginPage loginPage;
    static Manager manager;

    @BeforeAll
    public static void beforeLogin(){
        Global.Init();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        manager = new Manager(driver);
    }

    @Test
    @Order(1)
    public void shareCarrotSuccess(){
        loginPage.login("user_manager_agus", "1234");
        manager.shareCarrotTab().focus();
        manager.shareCarrotTab().clickShareCarrotBtn();
        manager.shareCarrotTab().recipientDropDownList("Susi");
        manager.shareCarrotTab().setCarrotAmount("20");
        manager.shareCarrotTab().setDescription("automated description");
        manager.shareCarrotTab().clickSubmitButton();
    }

    @Test
    @Order(2)
    public void assertShareCarrot(){
        manager.shareCarrotTab().assertShareCarrotSuccess("Susi", "20", "automated description");
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
