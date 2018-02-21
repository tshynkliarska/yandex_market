package Pages;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SetUp {

    public static WebDriverWait wait;
    public static WebDriver driver;
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
}