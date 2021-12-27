package LK.Test;

import Basic.BasicCase;
import Pages.LK.Test.Login;
import Pages.LK.Test.MyProfile;
import Tools.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MyProfileTest extends BasicCase {

    private User user = new User();
    private Login lgn;
    private MyProfile prf;

    @BeforeClass
    @Override
    public void set_Up() {
        lgn = PageFactory.initElements(getDriver(),
                Login.class);
        prf = PageFactory.initElements(getDriver(),
                MyProfile.class);
    }

    @Test(priority = 0)
    public void PreConditions(){

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
    public void FillingInProfile() throws InterruptedException {

        //Вкладка "Личные данные"
        //Step 1
        prf.fillInTextInputs(
                "Фамилия","Каренина");
        prf.fillInTextInputs(
                "Имя","Анна");
        prf.fillInTextInputs(
                "Отчество","Аркадьевна");

        //Step 2
        prf.fillInTextInputs(
                "Дата рождения","02051996");
        prf.fillInTextInputs(
                "Дата выдачи","02052010");

        //Step 3
        prf.fillInTextInputs(
                "Серия","1234");

        //Step 4
        prf.fillInTextInputs(
                "Номер","123456");

        //Step 5
        prf.selectFileToUpload(
                "Загрузить копию паспорта");

        //Step 6
        prf.fillInTextAreas(
                "Паспорт выдан","Паспорт выдан 02.05.2010");
        prf.fillInTextAreas(
                "Прописка","Город Х, дом 2, подъезд 5, квартира 17.");

        //Step 7
        prf.fillInTextInputs(
                "СНИЛС","12345678901");

        //Step 8
        prf.fillInTextInputs(
                "ИНН","123456789012");

        //Step 9
        prf.clickOnSubmitButton();

        //Вкладка "Имущество"
        prf.switchNavigationTabTo("имущество");

        //Step 10
        prf.fillInTextAreas(
                "Сделки с имуществом за 3 года","Сделки с имуществом.");

        //Step 11
        prf.clickOnSubmitButton();

        //Вкладка "Кредиторы"
        prf.switchNavigationTabTo("кредиторы");

        //Step 12
        prf.selectTheCreditBank("Сиббанк");

        //Step 13
//        prf.clickOnSubmitButton();

    }

}
