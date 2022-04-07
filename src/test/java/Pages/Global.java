package Pages;

public class Global {
    public static String WebURL = "http://localhost:3000";
    public static String FarmerHomePage = "/home/farmer";

    public static String chromeDriverName ="webdriver.chrome.driver";
    public static String chromeDriverPath ="src/test/webdriver/chromedriver_v100.exe";

    public static void Init(){
        System.setProperty(Global.chromeDriverName, Global.chromeDriverPath);
    }
}
