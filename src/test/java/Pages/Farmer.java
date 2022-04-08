package Pages;

import org.openqa.selenium.WebDriver;

import Tab.BarnTab;
import Tab.DistributeBarnTab;

public class Farmer extends HomePage {  

    private BarnTab barnTab;
    private DistributeBarnTab distributeBarnTab;

    public BarnTab barnTab() {return barnTab;}
    public DistributeBarnTab distributeTab() {return distributeBarnTab;}

    public Farmer(WebDriver driver) {
        super.setDriver(driver);
        barnTab = new BarnTab(driver);
        distributeBarnTab = new DistributeBarnTab(driver);
    }   
}
