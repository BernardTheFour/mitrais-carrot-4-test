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
import Pages.Staff;

public class TestLogin {
    static WebDriver driver;
    static LoginPage loginPage;
    static Farmer farmerPage;
    static Merchant merchant;
    static Staff staff;

    /** All login scenario
     * 1. Input username
     * 2. Input password
     * 3. Assert homepage
     * 4. Logout
     */

    @BeforeClass
    public static void beforeLogin(){     
        Global.Init();
        
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        farmerPage = new Farmer(driver);
        merchant = new Merchant(driver);
        staff = new Staff(driver);
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
    public void staffLogin(){
        loginPage.login("user_budi", "1234");
        staff.assertHomePage("Budi", "Junior Programmer", "Training and Development", "Staff");
        staff.logout();
    }

    @Test
    public void loginFail(){
        loginPage.login("wronguser", "wrongpassword");
        loginPage.assertErrorMessage();
    }

    @Test
    public void wrongUsername(){
        loginPage.login("wronguser", "1234");
        loginPage.assertErrorMessage();
    }

    @Test
    public void wrongPassword(){
        loginPage.login("user_farmer", "wrongpassword");
        loginPage.assertErrorMessage();
    }

    @Test
    public void noPassword(){        
        loginPage.login("user_farmer", "");
        loginPage.assertErrorMessage();
    }

    @Test
    public void noUsername(){        
        loginPage.login("", "1234");
        loginPage.assertErrorMessage();
    }

    @Test
    public void noUsernameAndPassword(){        
        loginPage.login("", "");
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
