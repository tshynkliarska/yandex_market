package yandexMarketTest;


import Pages.*;
import org.junit.Test;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.io.IOException;


public class AcceptanceTests extends SetUp {
    @Test
    public void acceptanceTests() throws InterruptedException, IOException {

        //Step 1. Go to yandex.market
        MainPage mainPage = new MainPage(driver,wait);
        mainPage.openMainPage();

        Assert.assertEquals(driver.getCurrentUrl(),baseUrl);
        captureScreenshot();
        String parentHandle = driver.getWindowHandle();

        //Step 2. Login
        mainPage.clickOnLogin();
        captureScreenshot();

        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login(login, password);
        captureScreenshot();
        driver.switchTo().window(parentHandle);

        //Step 3. Perform Search
        mainPage.searchProduct(productNameForSearch);
        captureScreenshot();
        mainPage.sortByPrice();

        //Check if ResultSet has appropriate product
        Assert.assertTrue(mainPage.verifyThatResultSetContainsProduct(productNameForSearch),"Product not found");

        //Step 4. Add product to cart
        mainPage.AddProductToCart();
        captureScreenshot();

        //Step 5. Go to Cart
        mainPage.goToCart();
        captureScreenshot();
        CartPage cartPage = new CartPage(driver, wait);
        Assert.assertTrue(cartPage.productCountInCart().contains("за 1 товар"));

        //Step 5. Checkout
        cartPage.clickOnCheckoutButton();
        captureScreenshot();
        Assert.assertEquals(cartPage.getEmail(), email);
        Assert.assertEquals(cartPage.getUserName(), userName);
        Assert.assertTrue(cartPage.getProductName().contains(productNameInCart));

        //Step 7. Clear cart
        cartPage.clearCart();
        captureScreenshot();
        Assert.assertTrue(driver.getPageSource().contains("Нет товаров для заказа."));

        //Step 8. Logout
        mainPage.logout();
        captureScreenshot();
        Assert.assertTrue(driver.findElement(By.xpath(mainPage.loginButtonXpath)).isEnabled());
    }
}



