package com.kitapyurdu.methods;

import com.kitapyurdu.driver.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import static org.junit.Assert.assertTrue;


import java.time.Duration;
import java.util.List;
import java.util.Random;


public class Methods {
    WebDriver driver;
    FluentWait<WebDriver> wait;
    JavascriptExecutor jsDriver;


    public Methods() {
        driver = BaseTest.driver;
        wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(30)
                ).pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class);
        jsDriver = (JavascriptExecutor) driver;
    }


    public WebElement findElement(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> findAllElement(By by) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public void click(By by) {
        findElement(by).click();
    }

    public boolean isElementClickable(By by) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void waitBySeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isElementVisible(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void sendKeys(By by, String text) {
        findElement(by).sendKeys(text);
    }

    public void scrollWithAction(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(findElement(by)).build().perform();
    }

    public void scrollWithJavaScript(By by) {
        jsDriver.executeScript("arguments[0].scrollIntoView();", findElement(by));
    }

    public Select getSelect(By by) {
        return new Select(findElement(by));
    }

    public void selectByText(By by, String text) {
        getSelect(by).selectByVisibleText(text);
    }

    public String getAttribute(By by, String attributeName) {
        return findElement(by).getAttribute(attributeName);
    }

    public String getText(By by) {
        return findElement(by).getText();
    }

    public String getValue(By by) {
        return jsDriver.executeScript("return arguments[0].value", findElement(by)).toString();
    }

    public void mouseHover(By by) {
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void selectRandomProduct(By hover, By click) {
        Actions actions = new Actions(driver);
        Random random = new Random();

        int productIndex = random.nextInt(findAllElement(hover).size());
        actions.moveToElement(findAllElement(hover).get(productIndex)).perform();
        findAllElement(click).get(productIndex).click();
    }

    public void addFavorites(By hover, By click) {
        Actions actions = new Actions(driver);
        Random random = new Random();
        for (int i = 7; i < 11; i++) {
            actions.moveToElement(findAllElement(hover).get(i)).perform();
            findAllElement(click).get(i).click();
        }

    }

    public void selectItemFromDropBox(By by, String text) {

        WebElement element = findElement(by);
        Select dropdown = new Select(element);
        String option= text;
        dropdown.selectByVisibleText(option);
    }

    public void textControl(By by, String expectedText) {
        WebElement element = driver.findElement(by);
        String actualText = element.getText();
        System.out.println(actualText);
        System.out.println(expectedText);
        Assert.assertEquals(actualText , expectedText);

    }
}




