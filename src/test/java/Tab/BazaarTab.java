package Tab;

import org.openqa.selenium.WebDriver;

public abstract class BazaarTab {
    WebDriver driver;

    public BazaarTab(WebDriver driver){
        this.driver = driver;
    }    //loosely
}
