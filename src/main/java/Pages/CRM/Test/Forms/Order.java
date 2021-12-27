package Pages.CRM.Test.Forms;

import Pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Order extends Page {
    public Order(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open(String host) {}

    @FindAll({@FindBy(css = ".dropdown-menu.scale-up-left .form-control")})
        List<WebElement> getTextInputs;
    @FindAll({@FindBy(css = ".filled-in")})
        List<WebElement> getCheckboxes;
    @FindAll({@FindBy(css = ".dropdown-menu.scale-up-left .form-control .ng-star-inserted")})
        List<WebElement> getDropdownVars;
    @FindAll({@FindBy(css = ".btn-md.btn-success")})
        List<WebElement> getButtonsSubmit;

    @FindBy(css = ".dropdown-menu.scale-up-left")
        WebElement orderForm;
    @FindBy(css = ".ui-inputwrapper-focus.ng-untouched.ng-pristine.ng-invalid .ui-inputtext")
        WebElement getPhoneInput;
    @FindBy(css = ".btn-md.btn-danger")
        WebElement buttonCreateOrder;
    @FindBy(css = ".form-control.form-control-sm")
        WebElement RequestTypeField;

    public void waitUntillOrderFormOpens(){
        wait.until(ExpectedConditions.visibilityOf(orderForm));
    }

    public void fillInTextInputs(String fieldName, String text){
        type(
                executeWebElementByAttribute(
                        getTextInputs,"formcontrolname",fieldName),text);
    }

    public void fillInPhoneInput(String phone){
        type(getPhoneInput, phone);
    }

    public void clickOnCheckBox(String fieldname){
        clickOn(
                executeWebElementByAttribute(
                        getCheckboxes, "formcontrolname", fieldname));
    }

    public void clickOnDropdownAndSelectVar(String fieldName, String text){
        clickOn(
                executeWebElementByAttribute(
                        getTextInputs,"formcontrolname",fieldName));

        clickOn(executeWebElementByText(
                getDropdownVars,text));
    }

    public void clickOnButtonSubmit(){
        clickOn(
                executeWebElementByVisibility(
                        getButtonsSubmit));
    }

    public void createOrder(){
        clickOn(buttonCreateOrder);
    }

    public boolean redirectedToPrimaryForm(){
        wait.until(ExpectedConditions.urlContains("info"));

        return getUrl().contains("info");
    }

}
