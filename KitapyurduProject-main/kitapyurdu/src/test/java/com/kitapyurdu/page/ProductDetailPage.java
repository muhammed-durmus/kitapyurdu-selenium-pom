package com.kitapyurdu.page;

import com.kitapyurdu.driver.BaseTest;
import com.kitapyurdu.methods.Methods;
import org.openqa.selenium.By;

public class ProductDetailPage extends BaseTest {

    Methods methods;


    public ProductDetailPage() {
        methods = new Methods();
    }
    public double readPriceAndSave (){
        String price = methods.getText(By.cssSelector(".price__item"));
        String replacedPrice = price.replace(",",".");
        Double convertedPrice = Double.parseDouble(replacedPrice);
        return convertedPrice;
    }

    public  void addCart (){
        methods.click(By.id("button-cart"));
    }

    public void checkAddingToCart (){
        methods.waitBySeconds(5);
        methods.textControl(By.id("cart-items"),"1");
    }


}
