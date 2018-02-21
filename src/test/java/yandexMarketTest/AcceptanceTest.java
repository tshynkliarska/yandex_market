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

        //Step 2. Login
        mainPage.login(login,password);

        //Step 3-4. Perform Search, go to product tab
        mainPage.searchProduct(productNameForSearch);
        mainPage.goToProductTab();

        //Step 5. Check if ResultSet contain appropriate product
        Assert.assertTrue(mainPage.verifyThatResultSetContainsProduct(productNameForSearch));


        //Step 6-7. Add product with lowest price to cart
        mainPage.sortByPrice();
        mainPage.addProductToCart();

        //Step 8. Go to Cart
        mainPage.goToCart();
        CartPage cartPage = new CartPage(driver, wait);
        Assert.assertTrue(cartPage.productCountInCart().contains("за 1 товар"));

        //Step 9. Checkout
        cartPage.clickOnCheckoutButton();

        //Step 10. Veriy correctness of product and user`s creds
        Assert.assertEquals(cartPage.getEmail(), email);
        Assert.assertEquals(cartPage.getUserName(), userName);
        Assert.assertTrue(cartPage.getProductName().contains(productNameInCart));

        //Step 11. Clear cart
        cartPage.clearCart();
        Assert.assertTrue(cartPage.getPageSource().contains("Нет товаров для заказа."));

        //Step 12. Logout
        mainPage.logout();
        Assert.assertTrue(mainPage.loginButtonIsEnabled());
    }
}



