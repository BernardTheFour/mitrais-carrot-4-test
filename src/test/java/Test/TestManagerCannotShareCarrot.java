package Test;
import Pages.Global;
import Pages.LoginPage;
import Pages.Manager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestManagerCannotShareCarrot {
    static WebDriver driver;
    static LoginPage loginPage;
    static Manager manager;


    @BeforeAll
    public static void beforeLogin(){
        Global.Init();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        manager = new Manager(driver);
        loginPage.login("user_manager_agus", "1234");

    }

    @Test
    @Order(1)
    public void negativeValue(){
        manager.shareCarrotTab().focus();
        manager.shareCarrotTab().clickShareCarrotBtn();
        manager.shareCarrotTab().recipientDropDownList("Staff");
        manager.shareCarrotTab().setCarrotAmount("-123"); // negative value sent
        manager.shareCarrotTab().setDescription("negative value frozen carrot sent automated");
        manager.shareCarrotTab().clickSubmitButton();
    }

    @Test
    @Order(2)
    public void assertShareCarrotNegValue(){
        manager.shareCarrotTab().assertShareValue( "-123");
    }

    @Test
    @Order(3)
    public void exceedFrozenCarrot(){
        manager.shareCarrotTab().clickShareCarrotBtn();
        manager.shareCarrotTab().recipientDropDownList("Staff");
        manager.shareCarrotTab().setCarrotAmount("999999999"); // exceed frozen carrot of manager
        manager.shareCarrotTab().setDescription("exceed frozen carrot automated");
        manager.shareCarrotTab().clickSubmitButton();
    }

    @Test
    @Order(4)
    public void assertShareCarrotExceed(){
        manager.shareCarrotTab().assertShareValue("999999999");
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
