package Pages.CRM.Test;

import Pages.Page;
import Tools.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Locale;

public class AllOrders extends Page {
    public AllOrders(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open(String host) {
        driver.get(host+"office/orders/common");
    }

    @FindAll({@FindBy(css = ".c-btn .ng-star-inserted")})
        List<WebElement> getTagsInputs;
    @FindAll({@FindBy(css = ".col-12 .select-all")})
        List<WebElement> getCheckboxSelectAll;
    @FindAll({@FindBy(css = ".c-input")})
        List<WebElement> getCheckboxFilterInput;
    @FindAll({@FindBy(css = ".col-12 .filter-select-all")})
        List<WebElement> getAllFilteredCheckboxes;
    @FindAll({@FindBy(css = ".col-12 input.form-control")})
        List<WebElement> getTextInputs;
    @FindAll({@FindBy(css = ".row.list.row")})
        List<WebElement> getRows;
    @FindAll({@FindBy(css = ".col-md-3 a")})
        List<WebElement> getResultNames;
    @FindAll({@FindBy(css = ".col-12 select.form-control")})
        List<WebElement> getSelectInputs;


    @FindBy(css = ".col-12 .btn-success")
    WebElement buttonSearch;


    public void openAllOrders(){
        open(testCRM);
    }

    public void fillInTheTags(String text, String tagname){

        clickOn(executeWebElementByText(
                getTagsInputs,text));

        if (tagname.toLowerCase(Locale.ROOT).equals("выбрать все")){
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

        driver.findElement(By.cssSelector(".c-list.ng-star-inserted")).click();
    }

    public void fillInTextInputs(String fieldName, String text){
        type(
                executeWebElementByAttribute(
                        getTextInputs,"formcontrolname",fieldName),text);
    }

    public void search(){
        clickOn(buttonSearch);
    }

    public boolean isSearchResultCorrect(){
        User user = new User();
        WebElement element = null;


        element = executeWebElementByText(getResultNames,user.dataLastName);

        return !(element == null);
    }

    public void clickOnDropdownAndSelectVar(String fieldName, String text){
        WebElement select = executeWebElementByAttribute(
                getSelectInputs,"formcontrolname",fieldName);

        clickOn(select);

        List<WebElement> vars = select.findElements(By.cssSelector("option"));

        clickOn(executeWebElementByText(vars,text));
    }

}
