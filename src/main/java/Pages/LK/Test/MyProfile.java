package Pages.LK.Test;

import Pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Locale;

public class MyProfile extends Page {
    public MyProfile(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open(String host) {
        driver.get(host + "profile/personal");
    }

    @FindAll({@FindBy(css = "input")})
        List<WebElement> getListOfTextInputs;
    @FindAll({@FindBy(css = "textarea")})
        List<WebElement> getListOfTextAreas;
    @FindAll({@FindBy(css = "input[type=file]")})
        List<WebElement> getListOfFileInputs;
    @FindAll({@FindBy(css = ".tab-nav-item")})
        List<WebElement> getListOfNavigationTabs;
    @FindAll({@FindBy(css = ".option-item")})
        List<WebElement> getListOfBanks;
    @FindAll({@FindBy(css = ".form-step")})
        List<WebElement> getListOForms;

    @FindBy(css = ".button.default")
        WebElement buttonSubmit;
    @FindBy(css = ".select-creditor .select")
        WebElement selectCreditor;

    public void waitUntillPageLoading(){
        wait.until(ExpectedConditions
                .elementToBeClickable(getListOfTextInputs.get(0)));
    }

    public void fillInTextInputs(String fieldName, String text){
        type(
                executeWebElementByAttribute(
                        getListOfTextInputs,"label",fieldName),text);
    }

    public void fillInTextAreas(String fieldName, String text){
        type(
                executeWebElementByAttribute(
                        getListOfTextAreas,"label",fieldName),text);
    }

    public void selectFileToUpload(String text ){
        executeWebElementByAttribute(
                getListOfFileInputs,"label", text)
                .sendKeys(
                        "C:\\Users\\Evy\\Pictures\\Saved Pictures\\Screenshot_1.png");
    }

    public void clickOnNavigationTab(int tab){
        actions = new Actions(driver);

        actions.moveToElement(
                getListOfNavigationTabs.get(tab));
        actions.click(
                getListOfNavigationTabs.get(tab));
        actions.perform();
    }

    public void switchNavigationTabTo(String text){
        switch (text
                .toLowerCase(Locale.ROOT)){
            case "личные данные":   clickOnNavigationTab(0);
                                    break;
            case "имущество":       clickOnNavigationTab(1);
                                    break;
            case "кредиторы":       clickOnNavigationTab(2);
                                    break;
        }
        wait.until(ExpectedConditions
                .visibilityOfAllElements(getListOForms));
    }

    public void clickOnSubmitButton(){
        actions = new Actions(driver);

        actions.moveToElement(buttonSubmit);
        actions.click(buttonSubmit);
        actions.perform();
    }

    public void selectTheCreditBank(String bankname){
        clickAndSelectElementinDropdown(selectCreditor,
                executeWebElementByAttribute(
                        getListOfBanks,"title",bankname));
    }

}
