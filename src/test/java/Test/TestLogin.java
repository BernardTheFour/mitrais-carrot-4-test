package Test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Pages.LoginPage;

public class TestLogin {
    static WebDriver driver;
    static LoginPage loginPage;

    @BeforeClass
    public static void beforeLogin(){        
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void userFarmer(){
        loginPage.login("user_farmer", "1234");
    }

    //After all tests
    //@AfterClass
    public static void closeBrowser(){
        //Delete cookies to logout user
        driver.manage().deleteAllCookies();
        
        //Terminate the WebDriver
        driver.quit();
    }
}
