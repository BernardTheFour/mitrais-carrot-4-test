package Test;

import Pages.Global;
import Pages.LoginPage;
import Pages.ManagerShareCarrot;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestManagerShareCarrot {
    static WebDriver driver;
    static LoginPage loginPage;
    static ManagerShareCarrot managerShareCarrot;

    @BeforeClass
    public static void beforeLogin(){
        Global.Init();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        managerShareCarrot = new ManagerShareCarrot(driver);
        driver.get(Global.WebURL);
    }


    @Test
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
    public void assertShareCarrot(){
        loginPage.login("user_manager_agus", "1234");
        managerShareCarrot.clickShareCarrotTab();
        managerShareCarrot.assertShareCarrotSuccess("Susi", "20", "automated description");
    }

    @After
    public void clearCache(){
        //Delete cookies to logout user
        driver.manage().deleteAllCookies();
    }

    //After all tests
    @AfterClass
    public static void closeBrowser(){
        //Terminate the WebDriver
        driver.quit();
    }
}
