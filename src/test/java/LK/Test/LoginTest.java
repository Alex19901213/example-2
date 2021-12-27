package LK.Test;

import Basic.BasicCase;
import Pages.LK.Test.Login;
import Tools.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends BasicCase {

    private User user = new User();
    private Login lgn;

    @BeforeClass
    @Override
    public void set_Up() {
        lgn = PageFactory.initElements(getDriver(), Login.class);
    }

    @Test
    public void LogInTest(){
        Given:
        lgn.userOpenLoginPage();

        When:
        lgn.userEnterLogIn(
                user.getUserLogin);
        lgn.userEnterPassword(
                user.getUserPassword);
        lgn.userPressButtonSubmit();

        Then:
        Assert.assertTrue(
                lgn.isUserLoggedIn());
    }

    @Test(priority = 1)
    public void BubbleErrorsTest(){

        Given:
        lgn.userOpenLoginPage();

        When:
        lgn.userPressButtonSubmit();

        Then:
        lgn.waitUntillBubbleErrorsAppears();

        Assert.assertTrue(
                lgn.isLoginBubbleErrorAppeared());
        Assert.assertTrue(
                lgn.isPasswordBubbleErrorAppeared());
        Assert.assertTrue(
                lgn.loginErrorBubbleContainsText());
        Assert.assertTrue(
                lgn.passwordErrorBubbleContainsText());
    }

}
