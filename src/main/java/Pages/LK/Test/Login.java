package Pages.LK.Test;

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
        driver.get(host+"sing-in");
    }

    @FindAll({@FindBy(css = "input")})
        List<WebElement> getLoginInputs;
    @FindAll({@FindBy(css = ".bubble")})
        List<WebElement> getBubbleErrors;

    @FindBy(css = ".button.submit")
        WebElement loginpage_SubmitButton;

    public void userOpenLoginPage(){
        open(testLK);
    }

    public void userEnterLogIn(String login){
        type(getLoginInputs.get(0),login);

    }

    public void userEnterPassword(String password){
        type(getLoginInputs.get(1),password);
    }

    public void userPressButtonSubmit(){
        loginpage_SubmitButton.click();
    }

    public boolean isUserLoggedIn(){
        String expectedResult = testLK + "profile/personal";
        wait.until(ExpectedConditions
                .urlContains(expectedResult));

        return getUrl()
                .equals(expectedResult);
    }

    public void waitUntillBubbleErrorsAppears(){
        wait.until(ExpectedConditions
                .visibilityOfAllElements(
                        getBubbleErrors));
    }

    public boolean isLoginBubbleErrorAppeared(){
        return isVisible(getBubbleErrors.get(0));
    }

    public boolean loginErrorBubbleContainsText(){
        return getBubbleErrors.get(0)
                .getText()
                .contains("Поле login обязательно для заполнения.");
    }

    public boolean isPasswordBubbleErrorAppeared(){
        return isVisible(
                getBubbleErrors.get(1));
    }

    public boolean passwordErrorBubbleContainsText(){
        return getBubbleErrors.get(1)
                .getText()
                .contains("Поле Пароль обязательно для заполнения.");
    }

}
