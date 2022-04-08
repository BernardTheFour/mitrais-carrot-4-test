package Test;

import Pages.Global;
import Pages.LoginPage;
import Pages.Manager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestManagerShareCarrot {
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
    public void shareCarrotSuccess(){
        loginPage.login("user_manager_agus", "1234");
        managerShareCarrot.clickShareCarrotTab();
        managerShareCarrot.clickShareCarrotBtn();
        managerShareCarrot.recipientDropDownList(3);
        managerShareCarrot.setCarrotAmount("20");
        managerShareCarrot.setDescription("automated description");
        managerShareCarrot.clickSubmitButton();
    }

    @Test
    @Order(2)
    public void assertShareCarrot(){
        managerShareCarrot.clickShareCarrotTab();
        managerShareCarrot.assertShareCarrotSuccess("Susi", "20", "automated description");
    }

    //After all tests
    @AfterAll
    public static void closeBrowser(){
        //Delete cookies to logout user
        driver.manage().deleteAllCookies();
        //Terminate the WebDriver
        driver.quit();
    }
}
