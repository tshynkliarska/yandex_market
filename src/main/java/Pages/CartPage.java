package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver,wait);
    }

    private String deleteProductFromCartButtonXPath = "/html/body/div[1]/div[2]/div[1]/div[3]/div/div/div/div/div/div[2]/div/div/div/div/div/div/div[5]/div";
    private String checkoutButtonXPath = "/html/body/div[1]/div[3]/div/div[2]/div/div/div[1]/div[1]/div/div[2]/div[2]/div/div[2]/div/div[2]/button";
    private String emailInputXpath ="//input[@type=\"email\"]";
    private String nameInputXpath ="//input[@name=\"user-name\"]";
    private String productNameInputXpath ="/html/body/div[1]/div[2]/div[1]/div[3]/div/div/div/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div/div/div";
    private String totalProductCount="//div[@class=\"w2Wj5pwydB\"]";

    public void clickOnCheckoutButton() throws InterruptedException {
        click(By.xpath(checkoutButtonXPath));
        Thread.sleep(4000);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(emailInputXpath))).click();

    }

    public void clearCart () {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(deleteProductFromCartButtonXPath))).click();
    }


    public String getEmail () {
        return driver.findElement(By.xpath(emailInputXpath)).getAttribute("value");
    }

    public String getUserName () {
        return driver.findElement(By.xpath(nameInputXpath)).getAttribute("value");
    }

    public String getProductName () {
        return readText(By.xpath(productNameInputXpath));
    }

    public String productCountInCart () {
       return readText(By.xpath(totalProductCount));
    }

}
