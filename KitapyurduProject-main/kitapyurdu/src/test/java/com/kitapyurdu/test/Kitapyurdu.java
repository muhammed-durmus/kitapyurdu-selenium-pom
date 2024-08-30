package com.kitapyurdu.test;

import com.kitapyurdu.driver.BaseTest;
import com.kitapyurdu.page.*;
import org.junit.Assert;
import org.junit.Test;

public class Kitapyurdu extends BaseTest {

    @Test
    public void kitapyurduHappyPath() {
        LoginPage loginPage = new LoginPage();
        AccountPage accountPage = new AccountPage();
        ProductPage productPage = new ProductPage();
        CartPage cartPage = new CartPage();
        ProductDetailPage productDetailPage = new ProductDetailPage();


        //loginPage.login();
        //accountPage.loginControl();
        productPage.search("Sabahattin Ali");
        productPage.sort();
        productPage.choseInStock();
        productPage.secondPage();
        productPage.choseThirdProduct();
        double priceForOne = productDetailPage.readPriceAndSave();
        productDetailPage.addCart();
        productDetailPage.checkAddingToCart();
        cartPage.goToCart();
        cartPage.raiseQuantity();
        double totalPrice = cartPage.readTotalPriceAndSave();
        double priceForTwo = priceForOne*2;
        comparePrice(priceForTwo,totalPrice);
        cartPage.clearCart();
        cartPage.isCartEmpty();



    }

    public void comparePrice(double x, double y) {

        Assert.assertEquals("Tutarlar eşittir",x,y);

    }


}
