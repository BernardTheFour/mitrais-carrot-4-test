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
import Pages.Merchant;

public class TestLogin {
    static WebDriver driver;
    static LoginPage loginPage;
    static Farmer farmerPage;
    static Merchant merchant;

    @BeforeClass
    public static void beforeLogin(){     
        Global.Init();
        
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        farmerPage = new Farmer(driver);
        merchant = new Merchant(driver);
    }

    @Test
    public void farmerLogin(){
        loginPage.login("user_farmer", "1234");
        farmerPage.assertHomePage("Farmer", "SE-AP", "Training and Development", "Farmer");
        farmerPage.logout();
    }

    @Test
    public void merchantLogin(){
        loginPage.login("user_merchant", "1234");
        merchant.assertHomePage("Merchant", "SE-AP", "Training and Development", "Merchant");
        merchant.logout();
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
