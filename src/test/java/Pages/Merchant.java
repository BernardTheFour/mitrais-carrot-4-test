package Pages;

import Tab.MerchandiseTab;
import org.openqa.selenium.WebDriver;

public class Merchant extends HomePage {

    public MerchandiseTab merchTab;

    public Merchant(WebDriver driver) {
        super.driver = driver;
        merchTab = new MerchandiseTab(driver);
    }

    public MerchandiseTab merchTab() {
        return this.merchTab;
    }

}
