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

    public By loginButtonXpath = By.xpath("//div[@class=\"header2-nav__user\"]//a[contains(@class,\"button2\")]");
    public By searchInputId = By.id("header-search");
    public By submitSearchButtonXpath = By.xpath("//span[@class=\"search2__button\"]//button[contains(@type,\"submit\")]");
    public By priceTabXPath = By.xpath("//*[@class=\"n-product-tabs__item n-product-tabs__item_name_offers\"]");
    public By sortByPriceLabelXPath = By.xpath("/html/body/div[1]/div[5]/div[1]/div[2]/div[1]/div[1]/div[3]/a");
    public By addToCartButtonInChosenProductPopUpXPath = By.xpath("/html/body/div[1]/div[5]/div[2]/div[1]/div[1]/div[1]/div[4]/a");
    public By paymentOnMarketButtonXpath = By.xpath("/html/body/div[1]/div[5]/div[2]/div[2]/div[3]/div/div[2]/div/div[1]/div/span/label");
    public By profileTabXpath = By.xpath("//div[@class=\"n-passport-suggest-popup-opener\"]//span[contains(@class,\"user__icon user__icon_loaded_yes\")]");
    public By logoutButtonXpath = By.xpath("//a[contains(.,'Выйти')]");
    public By searchResultsTitle = By.xpath("//span[@class=\"snippet-card__header-text\"]");
    public By cartXpath = By.xpath("//span[@class=\"header2-menu__icon header2-menu__icon_type_cart\"]");
    public By continueShopping = By.xpath("/html/body/div[3]/div/div/div[1]/div[1]/div/div/div/div[2]/div[2]/a[1]");

    public void openMainPage() throws IOException {
        driver.get(baseUrl);
        captureScreenshot();
    }

    public void clickOnLogin() throws IOException {
        click(loginButtonXpath);
        captureScreenshot();
    }

    public void searchProduct(String productName) throws IOException {
        writeText((searchInputId), productName);
        click(submitSearchButtonXpath);
        captureScreenshot();
    }

    public void goToProductTab() {
        click(priceTabXPath);
    }

    public boolean verifyThatResultSetContainsProduct(String productName) {
        List<WebElement> listofItems = driver.findElements(searchResultsTitle);
        for (WebElement product : listofItems) {
            if (product.getText().contains(productName)) {
                return true;
            }
        }
        return false;
    }

    public void sortByPrice() throws InterruptedException, IOException {
        click(paymentOnMarketButtonXpath);
        click(sortByPriceLabelXPath);

        Thread.sleep(2000);
        captureScreenshot();
    }

    public void addProductToCart() throws IOException {
        click(addToCartButtonInChosenProductPopUpXPath);
        click(continueShopping);
        captureScreenshot();
    }

    public void logout() throws IOException {
        click(profileTabXpath);
        click(logoutButtonXpath);
        captureScreenshot();
    }

    public void goToCart() throws IOException {
        click(cartXpath);
        captureScreenshot();
    }

    public boolean loginButtonIsEnabled() {
        return driver.findElement(loginButtonXpath).isEnabled();
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
        wait.until(ExpectedConditions.presenceOfElementLocated(profileTabXpath));
        captureScreenshot();
    }
}
