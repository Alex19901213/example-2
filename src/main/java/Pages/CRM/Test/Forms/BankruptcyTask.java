package Pages.CRM.Test.Forms;

import Pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Locale;

public class BankruptcyTask extends Page {
    public BankruptcyTask(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open(String host) {

    }

    @FindAll({@FindBy(css = ".modal-content .form-control")})
    List<WebElement> getTextInputs;
    @FindAll({@FindBy(css = ".modal-content input.form-control")})
    List<WebElement> getTextInputsForTime;
    @FindAll({@FindBy(css = ".ng-input")})
    List<WebElement> getSelectInputs;
    @FindAll({@FindBy(css = "[role=\"option\"]")})
    List<WebElement> getSelectOptions;

    @FindBy(css = ".modal-content .btn-success")
    WebElement buttonSave;

    public void saveChanges(){
        clickOn(buttonSave);
    }

    public void fillInTextInputs(String fieldName, String text){
        type(
                driver.findElement(By.id(fieldName)),text);
    }

    public void fillInTimeInputs(String fieldName, String text){
        Integer tab = null;

        switch (fieldName.toLowerCase(Locale.ROOT)){
            case "task_payment_date_start_time": tab = 1;
                break;
            case "task_payment_date_end_time": tab = 3;
                break;
            case "task_documents_date_start_time": tab = 5;
                break;
            case "task_documents_date_end_time": tab = 7;
                break;
        }
        type(getTextInputsForTime.get(tab),text);

//TODO
//        type(
//                executeWebElementByAttribute(
//                        getTextInputsForTime,"formcontrolname",fieldName)
//                        .findElement(By.cssSelector("input")),text);
    }

    public void clickOnDropdownAndSelectVar(String fieldName, String text){
        Integer tab = null;

        switch (fieldName.toLowerCase(Locale.ROOT)){
            case "дата прозвона клиенту по первой оплате.": tab = 0;
                break;
            case "дата прозвона клиенту по сбору документов (на сопровожденца).": tab = 1;
                break;
        }
        clickOn(getSelectInputs.get(tab));

        clickOn(executeWebElementByText(getSelectOptions,text));
    }

}
