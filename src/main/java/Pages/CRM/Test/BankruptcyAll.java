package Pages.CRM.Test;

import Pages.Page;
import Tools.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BankruptcyAll extends Page {
    public BankruptcyAll(WebDriver driver) {
        super(driver);
    }

    @FindAll({@FindBy(css = ".col-md-12 input.form-control")})
        List<WebElement> getTextInputs;
    @FindAll({@FindBy(css = ".col-md-3 a")})
        List<WebElement> getResultNames;
    @FindAll({@FindBy(css = ".col-md-12 select.form-control")})
        List<WebElement> getSelectInputs;

    @FindBy(css = ".col-md-12 .btn-success")
        WebElement buttonSearch;



    @Override
    public void open(String host) {
        driver.get(host+"office/orders/bankruptcy/all");
    }

    public void openBankruptcyAllPage(){
        open(testCRM);
    }

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

    public void search(){
        clickOn(buttonSearch);
    }

    public boolean isSearchResultCorrect(){
        User user = new User();
        WebElement element = null;

        element = executeWebElementByText(getResultNames,user.dataLastName);

        return !(element == null);
    }


}
