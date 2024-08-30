package com.kitapyurdu.page;

import com.kitapyurdu.methods.Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class CartPage {

    Methods methods;

    public CartPage() {
        methods = new Methods();
    }

    //Sepetim sayfasına gitme
    public void goToCart() {
        methods.click(By.id("cart"));
        methods.waitBySeconds(2);
        methods.click(By.id("js-cart"));
    }

    //Ürün adedini arttırma
    public void raiseQuantity() {
        methods.findElement(By.name("quantity")).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE), "2");
        methods.click(By.cssSelector("[title='Güncelle']"));
        methods.waitBySeconds(3);
    }

    public double readTotalPriceAndSave() {

        String totalPrice = methods.getText(By.cssSelector("tr:nth-of-type(1) > td:nth-of-type(2) > b"));
        String replacedPrice = totalPrice.replace(",",".");
        Double convertedPrice = Double.parseDouble(replacedPrice);
        return convertedPrice;


    }

    public void clearCart(){
        methods.click(By.cssSelector(".alt > i[title='Kaldır']"));
    }

    public void isCartEmpty(){
        methods.textControl(By.id("cart-items"),"0");
    }

}
