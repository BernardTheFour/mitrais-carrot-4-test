package Tab;

import org.openqa.selenium.WebDriver;

public class BazaarTab implements IHompageTab {
    WebDriver driver;

    public BazaarTab(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void focus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Not supported yet");
    }

}
