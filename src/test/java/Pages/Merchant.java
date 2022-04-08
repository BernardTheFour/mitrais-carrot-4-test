package Pages;

import Tab.BazaarTab;
import Tab.MerchandiseTab;
import org.openqa.selenium.WebDriver;

public class Merchant extends HomePage {

    private MerchandiseTab merchTab;
    private BazaarTab bazaarTab;

    public MerchandiseTab merchTab() {
        return this.merchTab;
    }

    public BazaarTab bazaarTab(){
        return this.bazaarTab;
    }

    public Merchant(WebDriver driver) {
        super.driver = driver;
        merchTab = new MerchandiseTab(driver);
    }    
}
