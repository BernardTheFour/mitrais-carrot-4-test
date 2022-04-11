package Pages;

import Tab.BazaarTab;
import Tab.CarrotDashBoard;
import Tab.ShareCarrotTab;
import org.openqa.selenium.WebDriver;


public class Manager extends HomePage {
    private ShareCarrotTab shareCarrotTab;
    private CarrotDashBoard carrotDashboard;
    private BazaarTab bazaarTab;

    public ShareCarrotTab shareCarrotTab(){return shareCarrotTab;}
    public CarrotDashBoard carroDashBoard(){return carrotDashboard;}
    public BazaarTab bazaarTab(){return bazaarTab;}

    public Manager(WebDriver driver){
        super.driver=driver;
        shareCarrotTab = new ShareCarrotTab(driver);
        carrotDashboard = new CarrotDashBoard(driver);
        bazaarTab = new BazaarTab(driver);
    }
}