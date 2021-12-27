package CRM.Test;

import Basic.BasicCase;
import Pages.CRM.Test.Login;
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

    @Test(priority = 1)
    public void LoginTest(){
        Given:
        lgn.userOpenLoginPage();

        When:
        lgn.userEnterLogin(
                user.getAdminLogin);
        lgn.userEnterPassword(
                user.getAdminPassword);
        lgn.userPressButtonSubmit();

        Then:
        Assert.assertTrue(lgn.isUserLoggedIn());
    }

    public void NegativeLoginTest(){
        Given:
        lgn.userOpenLoginPage();

        When:
        lgn.userEnterLogin(
                user.getAdminLogin);
        lgn.userEnterPassword(
                user.getWrongPassword);
        lgn.userPressButtonSubmit();

        Then:
        Assert.assertTrue(lgn.isAllertNotificationVisible());
        Assert.assertTrue(!lgn.isUserLoggedIn());
    }
}
