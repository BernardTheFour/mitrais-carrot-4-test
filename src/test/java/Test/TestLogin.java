package Test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Pages.Farmer;
import Pages.Global;
import Pages.LoginPage;

public class TestLogin {
    static WebDriver driver;
    static LoginPage loginPage;
    static Farmer farmerPage;

    @BeforeClass
    public static void beforeLogin(){     
        Global.Init();
        
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        farmerPage = new Farmer(driver);
    }

    @Test
    public void farmerLogin(){
        loginPage.login("user_farmer", "1234");
        farmerPage.assertHomePage("Farmer", "SE-AP", "Training and Development", "Farmer");
    }

    @Test
    public void loginFail(){
        loginPage.login("wronguser", "wrongpassword");
        loginPage.assertErrorMessage();
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
