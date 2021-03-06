package Pages.CRM.Test.Forms;

import Pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Locale;

public class Primary extends Page {
    public Primary(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open(String host) {}

    @FindAll({@FindBy(css = ".col-md-8 .c-btn .ng-star-inserted")})
        List<WebElement> getTagsInputs;
    @FindAll({@FindBy(css = ".col-md-8 .active input.form-control")})
        List<WebElement> getTextInputs;

    @FindAll({@FindBy(css = ".col-md-8 select.form-control")})
        List<WebElement> getSelectInputs;
    @FindAll({@FindBy(css = ".col-md-8 .filled-in")})
        List<WebElement> getCheckboxInputs;
    @FindAll({@FindBy(css = ".col-md-8 .filter-select-all")})
        List<WebElement> getAllFilteredCheckboxes;
    @FindAll({@FindBy(css = ".c-input")})
        List<WebElement> getCheckboxFilterInput;
    @FindAll({@FindBy(css = ".col-md-8 .select-all")})
        List<WebElement> getCheckboxSelectAll;
    @FindAll({@FindBy(css = ".nav .nav-item")})
        List<WebElement> getNavTabs;
    @FindAll({@FindBy(css = ".list-group-item")})
        List<WebElement> getDownNavTabs;
    @FindAll({@FindBy(css = ".show .dropdown-item.ng-star-inserted")})
        List<WebElement> getDropdownVars;

    @FindBy(css = ".col-md-12 .form-control-sm.ng-pristine")
        WebElement statusDropdown;
    @FindBy(css = ".col-md .btn.btn-success")
        WebElement buttonSave;
    @FindBy(css = "#list-consultations .btn-success")
        WebElement createConsultation;
    @FindBy(css = ".btn-group .btn")
        WebElement createCard;


    public void fillInTextInputs(String fieldName, String text){
        type(
                executeWebElementByAttribute(
                        getTextInputs,"formcontrolname",fieldName),text);
    }

    public void fillInTheTags(String text, String tagname){

        clickOn(executeWebElementByText(
                getTagsInputs,text));

        if (tagname.toLowerCase(Locale.ROOT).equals("?????????????? ??????")){
            clickOn(
                    executeWebElementByVisibility(
                            getCheckboxSelectAll));
        }
        else {
            type(
                    executeWebElementByVisibility(
                        getCheckboxFilterInput), tagname);

            clickOn(
                    executeWebElementByVisibility(
                        getAllFilteredCheckboxes));
        }
    }

    public void swithNavigationTabTo(String tabname){
        Integer tab = null;

        switch (tabname.toLowerCase(Locale.ROOT)){
            case "??????????": tab = 0;
                            break;
            case "????????????????": tab = 1;
                            break;
            case "??????????????????": tab = 2;
                            break;
            case "????????????": tab = 3;
                            break;
            case "??????????????????": tab = 4;
                            break;
            case "????????????": tab = 5;
                            break;
        }
        clickOn(getNavTabs.get(tab));
    }

    public void switchDownNavigationTabTo(String fieldName){
        String name = null;

        switch (fieldName.toLowerCase(Locale.ROOT)){
            case "????????????????????": name = "info";
                break;
            case "????????????": name = "payments";
                break;
            case "????????????????????????": name = "consultations";
                break;
            case "????????????????????": name = "notices";
                break;
            case "??????????????????(??????)": name = "sms";
                break;
            case "????????": name = "chats";
                break;
            case "??????????": name = "files";
                break;
            case "????????????": name = "tasks";
                break;
            case "????????": name = "history";
                break;
            case "??????????????": name = "templates";
                break;
        }

        WebElement select = executeWebElementByAttribute(
                getDownNavTabs,"data-action",name);

        clickOn(select);
    }

    public void clickOnDropdownAndSelectVar(String fieldName, String text){
        WebElement select = executeWebElementByAttribute(
                getSelectInputs,"formcontrolname",fieldName);

        clickOn(select);

        List<WebElement> vars = select.findElements(By.cssSelector("option"));

        clickOn(executeWebElementByText(vars,text));
    }

    public void clickOnCreateCardAndSelectVar(String text){
        clickOn(createCard);

        clickOn(executeWebElementByText(getDropdownVars,text));
    }

    public void clickOnDropdownAndSelectVar(String text){

        clickOn(statusDropdown);

        List<WebElement> vars = statusDropdown.findElements(By.cssSelector("option"));

        clickOn(executeWebElementByText(vars,text));
    }

    public void saveChanges(){
        clickOn(buttonSave);
    }

    public void createConsultation(){
        clickOn(createConsultation);
    }
}
