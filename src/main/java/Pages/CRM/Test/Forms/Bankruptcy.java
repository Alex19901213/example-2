package Pages.CRM.Test.Forms;

import Pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Locale;

public class Bankruptcy extends Page {
    public Bankruptcy(WebDriver driver) {
        super(driver);
    }

    @FindAll({@FindBy(css = ".nav .nav-link")})
        List<WebElement> getNavTabs;
    @FindAll({@FindBy(css = ".col-md-8 .mt-3")})
        List<WebElement> getDocumentRows;
    @FindAll({@FindBy(css = ".mt-3 textarea")})
        List<WebElement> getListOfDocumentTabTextAreas;
    @FindAll({@FindBy(css = "textarea.ui-inputtext")})
        List<WebElement> getListOfTextAreas;
    @FindAll({@FindBy(css = ".col-md-8 select.form-control")})
        List<WebElement> getSelectInputs;
    @FindAll({@FindBy(css = ".col-md-8 .active input.form-control")})
        List<WebElement> getTextInputs;
    @FindAll({@FindBy(css = ".list-group-item")})
        List<WebElement> getDownNavTabs;
    @FindAll({@FindBy(css = ".active .btn.btn-primary")})
        List<WebElement> buttonInTab;
    @FindAll({@FindBy(css = ".show .dropdown-item")})
        List<WebElement> getDropdownVars;

    @FindBy(css = ".col-md .btn.btn-success")
        WebElement buttonSave;
    @FindBy(css = ".btn.btn-warning")
        WebElement buttonAdd;
    @FindBy(css = ".btn-group .btn-secondary")
        WebElement createCard;

    @Override
    public void open(String host) {

    }

    public void saveChanges(){
        clickOn(buttonSave);
    }

    public void swithNavigationTabTo(String tabname){
        Integer tab = null;

        switch (tabname.toLowerCase(Locale.ROOT)){
            case "общая": tab = 0;
                break;
            case "заказчик": tab = 1;
                break;
            case "кредиторы": tab = 2;
                break;
            case "документы": tab = 3;
                break;
            case "суд": tab = 4;
                break;
            case "звонки": tab = 5;
                break;
            case "обращения": tab = 6;
                break;
            case "клиент": tab = 7;
                break;
            case "список": tab = 8;
                break;
            case "график платежей": tab = 9;
                break;
        }
        clickOn(getNavTabs.get(tab));
    }

    public void fillInTheRow(String name, String text){
        js = (JavascriptExecutor) driver;

        for (int i = 0; i < getDocumentRows.size(); i++) {
            if (getDocumentRows.get(i)
                    .findElement(By.cssSelector("small.ng-star-inserted"))
                    .getText()
                    .contains(name)){
                js.executeScript(
                        "$('.mt-3 [type=\"checkbox\"]').get("+i+").click()");

                type(getListOfDocumentTabTextAreas.get(i),text);
                break;
            }
        }
    }

    public void clickOnDropdownAndSelectVar(String fieldName, String text){
        WebElement select = executeWebElementByAttribute(
                getSelectInputs,"formcontrolname",fieldName);

        clickOn(select);

        List<WebElement> vars = select.findElements(By.cssSelector("option"));

        clickOn(executeWebElementByText(vars,text));
    }

    public void fillInTextInputs(String fieldName, String text){
        type(
                executeWebElementByAttribute(
                        getTextInputs,"formcontrolname",fieldName),text);
    }

    public void fillInTextAreas(String fieldName, String text){
        type(
                executeWebElementByAttribute(
                        getListOfTextAreas,"formcontrolname",fieldName),text);
    }

    public void switchDownNavigationTabTo(String fieldName){
        String name = null;

        switch (fieldName.toLowerCase(Locale.ROOT)){
            case "информация": name = "info";
                break;
            case "оплаты": name = "payments";
                break;
            case "консультации": name = "consultations";
                break;
            case "примечания": name = "notices";
                break;
            case "сообщения(смс)": name = "sms";
                break;
            case "чаты": name = "chats";
                break;
            case "файлы": name = "files";
                break;
            case "задачи": name = "tasks";
                break;
            case "логи": name = "history";
                break;
            case "шаблоны": name = "templates";
                break;
        }

        WebElement select = executeWebElementByAttribute(
                getDownNavTabs,"data-action",name);

        clickOn(select);
    }

    public void addSchedule(){
        saveChanges();

        clickOn(
                executeWebElementByVisibility(buttonInTab));
    }

    public void sendNotice(){
        clickOn(executeWebElementByVisibility(buttonInTab));
    }

    public void clickOnCreateCardAndSelectVar(String text){
        clickOn(createCard);

        clickOn(executeWebElementByText(getDropdownVars,text));

        driver.switchTo().alert().accept();
    }
}
