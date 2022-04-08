package Pages;

import Tab.ShareCarrotTab;
import org.openqa.selenium.WebDriver;


public class Manager extends HomePage {
    private ShareCarrotTab shareCarrotTab;
    public ShareCarrotTab shareCarrotTab(){return shareCarrotTab;}

    public Manager(WebDriver driver){
        super.driver=driver;
        shareCarrotTab = new ShareCarrotTab(driver);
    }
}