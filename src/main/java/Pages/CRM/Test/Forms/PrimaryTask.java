package Pages.CRM.Test.Forms;

import Pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Locale;

public class PrimaryTask extends Page {
    public PrimaryTask(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open(String host) {

    }


    @FindAll({@FindBy(css = ".modal-content .form-control")})
        List<WebElement> getTextInputs;
    @FindAll({@FindBy(css = ".modal-content select.form-control")})
        List<WebElement> getSelectInputs;
    @FindAll({@FindBy(css = ".modal-content textarea")})
        List<WebElement> getListOfTextAreas;
    @FindAll({@FindBy(css = ".form-control.ui-widget.ui-state-filled")})
        List<WebElement> getListOfStartAndEndTime;
    @FindBy(css = ".modal-content .form-control-sm.ng-pristine")
        WebElement statusDropdown;

    @FindBy(css = ".modal-content .fa-save")
        WebElement buttonSave;


    public void fillInTextInputs(String fieldName, String text){
        type(
                executeWebElementByAttribute(
                        getTextInputs,"formcontrolname",fieldName),text);
    }

    public void clickOnDropdownAndSelectVar(String fieldName, String text){

        WebElement select = executeWebElementByAttribute(
                getSelectInputs,"formcontrolname",fieldName);

        clickOn(select);

        List<WebElement> vars = select.findElements(By.cssSelector("option"));

        clickOn(executeWebElementByText(vars,text));

    }

    public void fillInTheTime(String tabname, String time){
        Integer tab = null;

        switch (tabname.toLowerCase(Locale.ROOT)){
            case "время начала": tab = 0;
                break;
            case "время окончания": tab = 1;
                break;
        }
        type(getListOfStartAndEndTime.get(tab), time);
    }

    public void fillInTextAreas(String fieldName, String text){
        type(
                executeWebElementByAttribute(
                        //note_consultation
                        getListOfTextAreas,"formcontrolname",fieldName),text);
    }

    public void saveChanges(){
        clickOn(buttonSave);
    }
}
