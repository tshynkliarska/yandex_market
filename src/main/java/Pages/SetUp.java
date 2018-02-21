package Pages;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SetUp {

    public static WebDriverWait wait;
    public static WebDriver driver;
    public String baseUrl = "https://market.yandex.ru/";
    public String login = "y4ndextest1";
    public String email = "y4ndextest1@yandex.ru";
    public String userName = "Test Test";
    public String password = "Qwerty+1";
    public String productNameForSearch = "iPhone 7 128Gb";
    public String productNameInCart = "Apple iPhone 7 128Gb";


    @BeforeClass
    public static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", AppProperties.getProps().getProperty("driver.home"));
        driver = new ChromeDriver();

        //driver = new FirefoxDriver();
        //System.setProperty("webdriver.gecko.driver", "/Users/tetianash/Downloads/geckodriver");

        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void cleanUp() {
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public static void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    //Take screenshot
    public void captureScreenshot() throws IOException {
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File scrFile=((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        FileUtils.copyFile(scrFile, new File(currentDir + "\\screenshots\\" + System.currentTimeMillis() + ".png"));
    }
}