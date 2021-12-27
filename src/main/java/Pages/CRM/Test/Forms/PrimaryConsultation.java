package Pages.CRM.Test.Forms;

import Pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PrimaryConsultation extends Page {
    public PrimaryConsultation(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open(String host) {

    }

    @FindAll({@FindBy(css = ".mb-2 .col-md-9")})
        List<WebElement> getTimePickersRows;
    @FindAll({@FindBy(css = ".modal-content .btn-success")})
        List<WebElement> buttonSave;
    @FindAll({@FindBy(css = ".modal-content textarea")})
        List<WebElement> getListOfTextAreas;
    @FindAll({@FindBy(css = ".modal-content select.form-control")})
        List<WebElement> getSelectInputs;

    public void pickTimeOfDate(String time){
        clickOn(
                executeWebElementByText(
                getTimePickersRows
                        .get(0)
                        .findElements(
                                By.cssSelector(".btn-info.btn-xs")),time));
    }

    public void saveChanges(){
        clickOn(executeWebElementByAttribute(
                buttonSave,"type","submit"));
    }

    public void fillInTextAreas(String fieldName, String text){
        type(
                executeWebElementByAttribute(
                        getListOfTextAreas,"formcontrolname",fieldName),text);
    }

    public void clickOnDropdownAndSelectVar(String fieldName, String text){
        WebElement select = executeWebElementByAttribute(
                getSelectInputs,"formcontrolname",fieldName);

        clickOn(select);

        List<WebElement> vars = select.findElements(By.cssSelector("option"));

        clickOn(executeWebElementByText(vars,text));
    }

}
