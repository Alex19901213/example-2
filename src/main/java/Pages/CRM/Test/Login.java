package Pages.CRM.Test;

import Pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Login extends Page {
    public Login(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open(String host) {
        driver.get(host+"login");
    }

    @FindBy(css = ".btn-info")
        WebElement buttonSubmit;
    @FindBy(css = ".alert")
        WebElement notificationAlert;

    @FindAll({@FindBy(css = ".form-control")})
        List<WebElement> getLoginInputs;

    public void userOpenLoginPage(){
        open(testCRM);
    }

    public void userEnterLogin(String login){
        type(getLoginInputs.get(0),login);
    }

    public void userEnterPassword(String password){
        type(getLoginInputs.get(1),password);
    }

    public void userPressButtonSubmit(){
        buttonSubmit.click();
    }

    public boolean isUserLoggedIn(){
        String expectedResult = testCRM + "office/orders/common";

        wait.until(ExpectedConditions
                .urlContains(expectedResult));

        return getUrl()
                .equals(expectedResult);
    }

    public boolean isAllertNotificationVisible(){
        wait.until(ExpectedConditions.visibilityOf(notificationAlert));

        return notificationAlert.isDisplayed();
    }

}
