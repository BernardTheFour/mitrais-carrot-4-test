package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
