package Basic;

import Tools.YamlReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class BasicCase {
    public WebDriver driver;
    public WebDriver getDriver() {
        return driver;
    }

    private String SysPath = "src/config/SystemConfig";
    private YamlReader yamlReader = new YamlReader();
    private String chromedriver = yamlReader.sysGetObj(SysPath, "Settings", "Chrome", "webdriver");
//    private String firefoxdriver = yamlReader.sysGetObj(SysPath, "Settings", "Firefox", "webdriver");
    private String path = yamlReader.sysGetObj(SysPath, "Settings", "Chrome", "path");
    private String crx = yamlReader.sysGetObj(SysPath, "Settings", "Chrome", "crx");
//    private String serverUrl = yamlReader.sysGetObj(SysPath, "Settings", "Server", "url");

    /**This method respond for driver. You can change the driver or configurate him from this method, but for choosen type, you should go to TestNG.xml and change the "value"
     * to change the driver.
     *
     *  <parameter name="driverName" value="Iphone X"></parameter>*/
    @BeforeClass
    @Parameters("driverName")
    public void driverStart(@Optional("driverName") String driverName) {

        ChromeDriverService service = new ChromeDriverService.Builder().
                usingDriverExecutable(new File(chromedriver)).
                usingAnyFreePort().
                build();
        ChromeOptions chromeOptions = new ChromeOptions();
//        System.setProperty("webdriver.gecko.driver",firefoxdriver);
//        FirefoxOptions firefoxOptions = new FirefoxOptions();

        switch(driverName)
        {
            case "Chrome":
                chromeOptions.addArguments("start-maximized");
                driver = new ChromeDriver(service,chromeOptions);
                break;

            case "Iphone":
                Map<String, String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", "iPhone X");
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                driver = new ChromeDriver(service,chromeOptions);
                break;

//            case "Firefox":
//                driver = new FirefoxDriver();
//                driver.manage().window().maximize();
//                break;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @BeforeClass
    public abstract void set_Up();

    @AfterClass
    public void driverQuit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
