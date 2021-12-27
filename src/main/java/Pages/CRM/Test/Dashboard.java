package Pages.CRM.Test;

import Pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class Dashboard extends Page {
    public Dashboard(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open(String host) {
        driver.get(host + "office/orders/common");
    }

    @FindAll({@FindBy(css = ".btn.btn-danger")})
    List<WebElement> buttonDelete;

    @FindBy(id = "createLead")
        WebElement buttonCreateOrder;
    @FindBy(css = ".col-md-9 .form-control")
        WebElement deleteOrderInput;
    @FindBy(css = ".ui-toast-message-content")
        WebElement deletionAlert;

    public void createOrder(){
        clickOn(buttonCreateOrder);
    }

    public void goToAdministrationSelection(){
        driver.get(testCRM + "office/administration");
    }

    public void deleteOrder(String number){
        type(deleteOrderInput, number);

        executeWebElementByVisibility(buttonDelete).click();

        wait.until(ExpectedConditions.visibilityOf(deletionAlert));
    }
}
