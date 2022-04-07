package Test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Pages.HomePage;
import Pages.LoginPage;

public class TestLogin {
    static WebDriver driver;
    static LoginPage loginPage;
    static HomePage homePage;

    @BeforeClass
    public static void beforeLogin(){        
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void userFarmer(){
        loginPage.login("user_farmer", "1234");
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
