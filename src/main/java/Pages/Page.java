package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public abstract class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Random random;
    protected Actions actions;
    protected JavascriptExecutor js;



    protected String testLK = "https://example.com";
    protected String testCRM = "https://example.com";

    public abstract void open(String host);

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        random = new Random();
    }

    protected void type(WebElement element, String text) {
        wait.until(ExpectedConditions
                .elementToBeClickable(element));

        element.clear();
        element.sendKeys(text);
    }

    protected void clickOn(WebElement element){
        wait.until(ExpectedConditions
                .elementToBeClickable(element));

        element.click();
    }

    protected void clickAndSelectElementinDropdown(WebElement element, WebElement element2){
        clickOn(element);

        wait.until(ExpectedConditions.visibilityOf(element2));

        clickOn(element2);
    }

    protected void hideElement(WebElement element) {
        if (element != null) {
            String script = "arguments[0].setAttribute('style', 'display:none')";
            js.executeScript(script, element);
        }
    }

    protected boolean isVisible(WebElement element) {

        Dimension dimension = element.getSize();
        return dimension.getHeight() > 0 && dimension.getWidth() > 0;
    }

    protected boolean isElementPresent(WebElement webElement) {
        try {
            webElement.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementClickable(WebElement element) {
        WebDriverWait waitClick = new WebDriverWait(driver, Duration.ofSeconds(5));;
        try {
            waitClick.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected WebElement executeWebElementByAttribute(List<WebElement> list, String attribute, String input){
        WebElement result = null;

       for (WebElement element:list){
            if (element
                    .getAttribute(attribute)
                    .equals(input)){
                result = element;
                break;
            }
        }
        return result;
    }

    protected WebElement executeWebElementByText(List<WebElement> list, String input){
        WebElement result = null;

        for (WebElement element:list){
            if (element
                    .getText()
                    .contains(input)){
                result = element;
                break;
            }
        }
        return result;
    }

    protected WebElement executeWebElementByVisibility(List<WebElement> list){
        WebElement result = null;

        for (WebElement element:list){
            if (isVisible(element)){
                result = element;
                break;
            }
        }
        return result;
    }

    protected String getUrl() {
        return driver.getCurrentUrl();
    }

}
