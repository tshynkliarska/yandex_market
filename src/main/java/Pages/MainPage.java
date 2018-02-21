package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String loginButtonXpath = "//div[@class=\"header2-nav__user\"]//a[contains(@class,\"button2\")]";
    public String searchInputId = "header-search";
    private String submitSearchButtonXpath = "/html/body/div/div[1]/noindex/div/div/div[2]/div/div[1]/form/span[2]/button";
    private String priceTabXPath = "//*[@class=\"n-product-tabs__item n-product-tabs__item_name_offers\"]";
    private String sortByPriceLabelXPath = "/html/body/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[3]/a";
    private String addToCartButtonInChosenProductPopUpXPath = "/html/body/div[1]/div[5]/div[2]/div[1]/div[1]/div[1]/div[4]/a";
    private String paymentOnMarketButtonXpath = "/html/body/div[1]/div[5]/div[2]/div[2]/div[3]/div/div[2]/div/div[1]/div/span/label";
    public String profileTabXpath = "//div[@class=\"n-passport-suggest-popup-opener\"]//span[contains(@class,\"user__icon user__icon_loaded_yes\")]";
    private String logoutButtonXpath = "//a[contains(.,'Выйти')]";
    private String searchResultsTitle = "//span[@class=\"snippet-card__header-text\"]";
    private String cartXpath = "//span[contains(.,'Корзина')]";
    public String baseUrl = "https://market.yandex.ru/";
    private String continueShopping = "/html/body/div[3]/div/div/div[1]/div[1]/div/div/div/div[2]/div[2]/a[1]";

    public void openMainPage() throws IOException {
        driver.get(baseUrl);
        captureScreenshot();
    }

    public void clickOnLogin() throws IOException {
        click(By.xpath(loginButtonXpath));
        captureScreenshot();
    }

    public void searchProduct(String productName) throws IOException {
        writeText(By.id(searchInputId), productName);
        click(By.xpath(submitSearchButtonXpath));
        captureScreenshot();
    }

    public void goToProductTab() {
        click(By.xpath(priceTabXPath));
    }

    public boolean verifyThatResultSetContainsProduct(String productName) {
        List<WebElement> listofItems = driver.findElements(By.xpath(searchResultsTitle));
        for (WebElement product : listofItems) {
            if (product.getText().contains(productName)) {
                return true;
            }
        }
        return false;
    }

    public void sortByPrice() throws InterruptedException, IOException {
        click(By.xpath(paymentOnMarketButtonXpath));
        click(By.xpath(sortByPriceLabelXPath));
        Thread.sleep(2000);
        captureScreenshot();
    }

    public void AddProductToCart() throws IOException {
        click(By.xpath(addToCartButtonInChosenProductPopUpXPath));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(continueShopping))).click();
        captureScreenshot();
    }

    public void logout() throws IOException {
        click(By.xpath(profileTabXpath));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(logoutButtonXpath))).click();
        captureScreenshot();
    }

    public void goToCart() throws IOException {
        click(By.xpath(cartXpath));
        captureScreenshot();
    }

    public boolean loginButtonIsEnabled() {
        return driver.findElement(By.xpath(loginButtonXpath)).isEnabled();
    }

    public void login(String login, String password) throws IOException {
        clickOnLogin();
        String parentHandle = driver.getWindowHandle();

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterLogin(login);
        loginPage.enterPassword(password);
        captureScreenshot();
        loginPage.submit();
        driver.switchTo().window(parentHandle);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(profileTabXpath)));
        captureScreenshot();
    }
}
