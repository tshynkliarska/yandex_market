package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private By deleteProductFromCartButtonXPath = By.xpath("//div[@class=\"image image_name_trash\"]");
    private By checkoutButtonXPath = By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/div/div[1]/div[1]/div/div[2]/div[2]/div/div[2]/div/div[2]/button");
    private By emailInputXpath = By.xpath("//input[@type=\"email\"]");
    private By nameInputXpath = By.xpath("//input[@name=\"user-name\"]");
    private By productNameInputXpath = By.xpath("//div[@class=\"n-checkout-offer__item-name\"]");
    private By totalProductCount = By.xpath("//div[@class=\"w2Wj5pwydB\"]");

    public void clickOnCheckoutButton() throws InterruptedException, IOException {
        click(checkoutButtonXPath);
        Thread.sleep(4000);
        captureScreenshot();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(emailInputXpath))).click();

    }

    public void clearCart() throws IOException {
        click(deleteProductFromCartButtonXPath);
        captureScreenshot();
    }


    public String getEmail() {
        return driver.findElement(emailInputXpath).getAttribute("value");
    }

    public String getUserName() {
        return driver.findElement(nameInputXpath).getAttribute("value");
    }

    public String getProductName() {
        return readText(productNameInputXpath);
    }

    public String productCountInCart() {
        return readText(totalProductCount);
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

}
