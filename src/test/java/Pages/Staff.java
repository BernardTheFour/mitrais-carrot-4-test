package Pages;

import org.openqa.selenium.WebDriver;

import Tab.BazaarTab;

public class Staff extends HomePage {
    private BazaarTab bazaarTab;

    public BazaarTab bazaarTab() {
        return this.bazaarTab;
    }

    public Staff(WebDriver driver) {
        super.driver = driver;
    }
}
