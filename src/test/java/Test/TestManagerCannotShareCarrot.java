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
    static Manager managerShareCarrot;

    @BeforeAll
    public static void beforeLogin(){
        Global.Init();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        managerShareCarrot = new Manager(driver);
    }

    @Test
    @Order(1)
    public void negativeValue(){
        loginPage.login("user_manager_agus", "1234");
        managerShareCarrot.clickShareCarrotTab();
        managerShareCarrot.clickShareCarrotBtn();
        managerShareCarrot.recipientDropDownList("Staff");
        managerShareCarrot.setCarrotAmount("-123"); // negative value sent
        managerShareCarrot.setDescription("negative value frozen carrot sent automated");
        managerShareCarrot.clickSubmitButton();
    }

    @Test
    @Order(2)
    public void assertShareCarrotNegValue(){
        managerShareCarrot.assertShareValue( "-123");
    }

    @Test
    @Order(3)
    public void exceedFrozenCarrot(){
        managerShareCarrot.clickShareCarrotBtn();
        managerShareCarrot.recipientDropDownList("Staff");
        managerShareCarrot.setCarrotAmount("999999999"); // exceed frozen carrot of manager
        managerShareCarrot.setDescription("exceed frozen carrot automated");
        managerShareCarrot.clickSubmitButton();
    }

    @Test
    @Order(4)
    public void assertShareCarrotExceed(){
        managerShareCarrot.assertShareValue("999999999");
    }

//    Nb: Alvian, dont think we can use this test case. recipientDropDownList(Str Recipient) needs actual recipient
//    @Test
//    @Order(5)
//    public void noReceiver(){
//        managerShareCarrot.clickShareCarrotBtn();
//        managerShareCarrot.recipientDropDownList("Choose Recipient");
//        managerShareCarrot.setCarrotAmount("120");
//        managerShareCarrot.setDescription("no receiver automated");
//        managerShareCarrot.clickSubmitButton();
//    }
//
//    @Test
//    @Order(6)
//    public void assertShareCarrotNoReceiver(){
//        managerShareCarrot.assertShareNoReceiver("Choose Employee");
//    }

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
