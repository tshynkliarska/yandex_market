package yandexMarketTest;


import Pages.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class AcceptanceTest extends SetUp {
    @Test
    public void acceptanceTests() throws InterruptedException, IOException {

        //Step 1. Go to yandex.market
        MainPage mainPage = new MainPage(driver,wait);
        mainPage.openMainPage();
        mainPage.login(login,password);

        //Step 3. Perform Search
        mainPage.searchProduct(productNameForSearch);
        mainPage.goToProductTab();
        mainPage.sortByPrice();

        //Check if ResultSet has appropriate product
        Assert.assertTrue(mainPage.verifyThatResultSetContainsProduct(productNameForSearch));

        //Step 4. Add product to cart
        mainPage.AddProductToCart();

        //Step 5. Go to Cart
        mainPage.goToCart();
        CartPage cartPage = new CartPage(driver, wait);
        Assert.assertTrue(cartPage.productCountInCart().contains("за 1 товар"));

        //Step 5. Checkout
        cartPage.clickOnCheckoutButton();
        Assert.assertEquals(cartPage.getEmail(), email);
        Assert.assertEquals(cartPage.getUserName(), userName);
        Assert.assertTrue(cartPage.getProductName().contains(productNameInCart));

        //Step 7. Clear cart
        cartPage.clearCart();
        Assert.assertTrue(cartPage.getPageSource().contains("Нет товаров для заказа."));

        //Step 8. Logout
        mainPage.logout();
        Assert.assertTrue(mainPage.loginButtonIsEnabled());
    }
}



