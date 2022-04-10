package Pages;

import Tab.CarrotDashBoard;
import Tab.ShareCarrotTab;
import org.openqa.selenium.WebDriver;


public class Manager extends HomePage {
    private ShareCarrotTab shareCarrotTab;
    private CarrotDashBoard carrotDashboard;

    public ShareCarrotTab shareCarrotTab(){return shareCarrotTab;}
    public CarrotDashBoard carroDashBoard(){return carrotDashboard;}

    public Manager(WebDriver driver){
        super.driver=driver;
        shareCarrotTab = new ShareCarrotTab(driver);
        carrotDashboard = new CarrotDashBoard(driver);
    }
}