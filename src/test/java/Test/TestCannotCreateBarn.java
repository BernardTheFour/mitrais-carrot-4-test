package Test;

import com.github.javafaker.Faker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Dto.BarnItem;
import Pages.Farmer;
import Pages.Global;
import Pages.LoginPage;

public class TestCannotCreateBarn {
    /**
     * TEST SCENARIO
     * 1. Login as a farmer
     * 2. Click barn tab
     * 3. Click add barn button
     * 4. Fill form, some of them not filled
     * 5. Check if submit button is clickable
     * 6. Clear cookies, quit driver
     */

    private static WebDriver driver;
    private static LoginPage loginPage;
    private static Farmer farmerPage;

    private Faker faker = new Faker();

    @BeforeAll
    public static void precondition() {
        Global.Init();

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        farmerPage = new Farmer(driver);

        // step-1
        loginPage.login("user_farmer", "1234");
        // step-2
        farmerPage.barnTab().focus();
    }

    private void assertAndClose(BarnItem item) {
        farmerPage.barnTab().assertCanSubmitForm(false, item);
        farmerPage.barnTab().closeBarnPopUp();
    }

    @Test
    public void noName() {
        // no name / title
        BarnItem item = new BarnItem(
                "",
                String.valueOf(faker.number().numberBetween(10000, 100000)),
                String.valueOf(faker.number().numberBetween(500, 5000)),
                "01/01/2023",
                "31/12/2023");
        assertAndClose(item);
    }

    @Test
    public void unsupportedInitialCarrot() {
        // null initial carrot
        BarnItem item = new BarnItem(
                faker.ancient().god(),
                "",
                String.valueOf(faker.number().numberBetween(500, 5000)),
                "01/01/2023",
                "31/12/2023");
        // negative number
        BarnItem item2 = new BarnItem(
                faker.ancient().god(),
                "-1",
                String.valueOf(faker.number().numberBetween(500, 5000)),
                "01/01/2023",
                "31/12/2023");

        assertAndClose(item);
        assertAndClose(item2);
    }

    @Test
    public void unsupportedBirthdayCarrot() {
        // null initial carrot
        BarnItem item = new BarnItem(
                faker.ancient().god(),
                String.valueOf(faker.number().numberBetween(10000, 100000)),
                "",
                "01/01/2023",
                "31/12/2023");
        // negative number
        BarnItem item2 = new BarnItem(
                faker.ancient().god(),
                String.valueOf(faker.number().numberBetween(10000, 100000)),
                "-1",
                "01/01/2023",
                "31/12/2023");

        assertAndClose(item);
        assertAndClose(item2);
    }

    @Test
    public void unsupportedDate() {
        // start date
        BarnItem item = new BarnItem(
                faker.ancient().god(),
                String.valueOf(faker.number().numberBetween(10000, 100000)),
                String.valueOf(faker.number().numberBetween(500, 5000)),
                "",
                "31/12/2023");
        // end date
        BarnItem item2 = new BarnItem(
                faker.ancient().god(),
                String.valueOf(faker.number().numberBetween(10000, 100000)),
                String.valueOf(faker.number().numberBetween(500, 5000)),
                "01//2023",
                "");

        assertAndClose(item);
        assertAndClose(item2);
    }

    @AfterAll
    public static void closeBrowser() {
        // Terminate the WebDriver
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
