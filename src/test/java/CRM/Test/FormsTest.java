package CRM.Test;

import Basic.BasicCase;
import Pages.CRM.Test.*;
import Pages.CRM.Test.Forms.*;
import Tools.User;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FormsTest extends BasicCase {
    private User user = new User();
    private Dashboard dashboard;
    private Order ordr;
    private Login lgn;
    private Primary prmr;
    private PrimaryTask prmrTsk;
    private PrimaryConsultation cnst;
    private PrimaryAll prmrAll;
    private AllOrders allordrs;
    private Bankruptcy bnkrt;
    private BankruptcyTask bnkrtTsk;
    private BankruptcyAll bnkrtAll;

    private String orderURL;

    @BeforeClass
    @Override
    public void set_Up() {
        dashboard = PageFactory.initElements(getDriver(), Dashboard.class);
        ordr = PageFactory.initElements(getDriver(), Order.class);
        lgn = PageFactory.initElements(getDriver(), Login.class);
        prmr = PageFactory.initElements(getDriver(), Primary.class);
        prmrTsk = PageFactory.initElements(getDriver(), PrimaryTask.class);
        cnst = PageFactory.initElements(getDriver(), PrimaryConsultation.class);
        prmrAll = PageFactory.initElements(getDriver(), PrimaryAll.class);
        allordrs = PageFactory.initElements((getDriver()), AllOrders.class);
        bnkrt = PageFactory.initElements((getDriver()), Bankruptcy.class);
        bnkrtTsk = PageFactory.initElements((getDriver()), BankruptcyTask.class);
        bnkrtAll = PageFactory.initElements((getDriver()), BankruptcyAll.class);
    }

    @Test
    public void PreConditions(){
        //Login
        lgn.userOpenLoginPage();

        lgn.userEnterLogin(
                user.getAdminLogin);
        lgn.userEnterPassword(
                user.getAdminPassword);
        lgn.userPressButtonSubmit();

        Assert.assertTrue(lgn.isUserLoggedIn());

        //Delete order
        dashboard.goToAdministrationSelection();

        dashboard.deleteOrder("7"+user.dataPhone);
        dashboard.deleteOrder("7"+user.dataPhone);
    }

    @Link(name = "Ссылка в confluence", url = "https://example.com")
    @Test(description = "Создание Ордера",priority = 1)
    public void OrderForm(){

        //Step 1
        dashboard.createOrder();

        //Step 2
        System.out.println(user.dataOffice);
        ordr.clickOnDropdownAndSelectVar("branch_id",user.dataOffice);

        //Step 3
        ordr.fillInTextInputs("first_name",user.dataFirstName);
        ordr.fillInTextInputs("last_name",user.dataLastName);
        ordr.fillInTextInputs("third_name",user.dataThirdName);
        ordr.fillInPhoneInput(user.dataPhone);

        //Step 4
        ordr.clickOnButtonSubmit();

        //Step 5
        ordr.clickOnButtonSubmit();

        //Step 6
        ordr.createOrder();

        Assert.assertTrue(ordr.redirectedToPrimaryForm());

        orderURL = driver.getCurrentUrl();
        System.out.println(orderURL);
    }

    @Link(name = "Ссылка в confluence", url = "https://example.com")
    @Test(description = "Наполнение данными", priority = 2)
    public void PrimaryTask(){
        //Step 1
        prmr.clickOnDropdownAndSelectVar("Проведена квалификация");
        prmr.saveChanges();

        //Step 2
        prmrTsk.clickOnDropdownAndSelectVar("priority_id",user.dataPrimaryTasklPriority);
        prmrTsk.clickOnDropdownAndSelectVar("time_notify",user.dataPrimaryTaskNotifyTime);
        prmrTsk.fillInTextInputs("task_name",user.dataPrimaryTaskTaskName);
//        tsk.fillInTextInputs("date_start",user.dataTaskStartDate);
        prmrTsk.fillInTheTime("время начала",user.dataPrimaryTaskStartTime);
//        tsk.fillInTextInputs("date_deadline",user.dataTaskEndDate);
        prmrTsk.fillInTheTime("время окончания",user.dataPrimaryTaskEndTime);
        prmrTsk.fillInTextAreas("task_desc", user.userOK);
        prmrTsk.fillInTextAreas("resolved_comment",user.userOK);
        prmrTsk.fillInTextAreas("message",user.userOK);
        prmrTsk.saveChanges();
    }

    @Link(name = "Ссылка в confluence", url = "https://example.com")
    @Test(description = "Наполнение данными", priority = 3)
    public void PrimaryForm(){
        //Отдел Информация

        //Step 3
        //Вкладка "Общая"
        prmr.fillInTextInputs("basecost",user.dataPrimaryCost);
        prmr.fillInTextInputs("region",user.dataPrimaryRegion);
        prmr.clickOnDropdownAndSelectVar("company_id",user.dataPrimaryCompany);
        prmr.clickOnDropdownAndSelectVar("assigned_lawyer",user.dataPrimaryLawyer);
        prmr.clickOnDropdownAndSelectVar("user_display_status",user.dataPrimaryDisplayStatus);
        prmr.clickOnDropdownAndSelectVar("common_arrears",user.dataPrimaryCommonArrears);
        prmr.fillInTheTags("Теги","выбрать все");
        prmr.saveChanges();

        //Step 4
        //Вкладка "Заказчик"
        prmr.swithNavigationTabTo("Заказчик");

        prmr.fillInTextInputs("customer_birthday", user.dataBirthDate);
        prmr.fillInTextInputs("customer_passport_series", user.dataPassportSerial);
        prmr.fillInTextInputs("customer_passport_number", user.dataPassportNumber);
        prmr.fillInTextInputs("customer_issued_by", user.dataPassportIssuedBy);
        prmr.fillInTextInputs("customer_address_reg", user.dataRegistrationAdress);
        prmr.fillInTextInputs("customer_issued_date", user.dataPassportIssuedDate);
        prmr.fillInTextInputs("customer_email", user.dataEmail);
        prmr.fillInTextInputs("customer_inn", user.dataINN);
        prmr.fillInTextInputs("customer_snils", user.dataSNILS);
        prmr.fillInTextInputs("customer_birth_place", user.dataBirthPlace);
        prmr.saveChanges();

        //Step 5
        //Вкладка консультации
        prmr.switchDownNavigationTabTo("консультации");
        prmr.createConsultation();

        //Step 6
        cnst.pickTimeOfDate(user.dataPrimaryConsultationTimeOfDate);
        cnst.fillInTextAreas("note_consultation","txt");
        cnst.clickOnDropdownAndSelectVar("type",user.dataPrimaryConsultationType);
        cnst.saveChanges();
    }

    @Link(name = "Ссылка в confluence", url = "https://example.com")
    @Test(description = "Фильтрация в первичке", priority = 4)
    public void OrderFiltrationInPrimary() throws InterruptedException {
        //Step 1
        prmrAll.openPrimaryAllPage();

        //Step 2
        prmrAll.fillInTheTags("Теги","выбрать все");
        prmrAll.fillInTextInputs("region", user.dataPrimaryRegion);
        prmrAll.search();

        Thread.sleep(2000);

        Assert.assertTrue(prmrAll.isSearchResultCorrect());
    }


    @Link(name = "Ссылка в confluence", url = "https://example.com")
    @Test(description = "Фильтрация ордера во всех заказах", priority = 5)
    public void PrimaryOrderFiltrationInAllOrders() throws InterruptedException {
        //Step 1
        allordrs.openAllOrders();

        //Step 2
        allordrs.fillInTheTags("Теги","выбрать все");
        allordrs.search();

        Thread.sleep(2000);

        Assert.assertTrue(allordrs.isSearchResultCorrect());
    }

    @Link(name = "Ссылка в confluence", url = "https://example.com")
    @Test(description = "Копирование ордера в банкротство", priority = 6)
    public void MigrateToBankruptcy(){
        //Step 1
        driver.get(orderURL);

        //Step 2
        prmr.clickOnCreateCardAndSelectVar("Банкротство");
    }

    @Link(name = "Ссылка в confluence", url = "https://example.com")
    @Test(description = "Дозаполнение данных в банкростве", priority = 7)
    public void BankruptcyTask(){
        driver.navigate().refresh();

        //Step 1
        bnkrt.saveChanges();

        //Step 2

        //Дата прозвона клиенту по первой оплате.
        bnkrtTsk.clickOnDropdownAndSelectVar(
                "Дата прозвона клиенту по первой оплате.",user.dataPrimaryLawyer);

        bnkrtTsk.fillInTextInputs("task_payment_date_start", user.dataBankruptcyPaymentStartDate);
        bnkrtTsk.fillInTimeInputs("task_payment_date_start_time", user.dataBankruptcyPaymentStartTime);

        bnkrtTsk.fillInTextInputs("task_payment_date_end", user.dataBankruptcyPaymentEndDate);
        bnkrtTsk.fillInTimeInputs("task_payment_date_end_time", user.dataBankruptcyPaymentEndTime);

        //Дата прозвона клиенту по сбору документов (на сопровожденца).
        bnkrtTsk.clickOnDropdownAndSelectVar(
                "Дата прозвона клиенту по сбору документов (на сопровожденца).",user.dataPrimaryLawyer);

        bnkrtTsk.fillInTextInputs("task_documents_date_start", user.dataBankruptcyDocumentsStartDate);
        bnkrtTsk.fillInTimeInputs("task_documents_date_start_time", user.dataBankruptcyDocumentsStartTime);

        bnkrtTsk.fillInTextInputs("task_documents_date_end", user.dataBankruptcyDocumentsEndDate);
        bnkrtTsk.fillInTimeInputs("task_documents_date_end_time", user.dataBankruptcyDocumentsEndTime);

        bnkrtTsk.saveChanges();
    }

    @Link(name = "Ссылка в confluence", url = "https://example.com")
    @Test(description = "Дозаполнение данных в банкростве", priority = 8)
    public void BankruptcyForm() {
        //Step 3
        //Вкладка "Документы"
        bnkrt.swithNavigationTabTo("Документы");
        bnkrt.fillInTheRow("ЧЕК на 25000", user.userOK);
        bnkrt.fillInTheRow("ЧЕК на 300", user.userOK);
        bnkrt.fillInTheRow("СНИЛС", user.dataSNILS);
        bnkrt.fillInTheRow("ИНН", user.dataINN);
        bnkrt.fillInTheRow("ПФР", user.dataPFR);
        bnkrt.fillInTheRow("Св-во о браке", user.userOK);
        bnkrt.fillInTheRow("Св-во о рождении детей", user.userOK);
        bnkrt.fillInTheRow("Трудовая книжка", user.userOK);
        bnkrt.fillInTheRow("2 НДФЛ", user.userOK);
        bnkrt.fillInTheRow("Подтверждение финансирования процедуры", user.userOK);
        bnkrt.saveChanges();

        //Step 4
        //Вкладка "Суд"
        bnkrt.swithNavigationTabTo("Суд");
        bnkrt.clickOnDropdownAndSelectVar("difficulty", user.dataBankruptcyDifficulty);
        bnkrt.clickOnDropdownAndSelectVar("cpo_id", user.dataBankruptcyCPO);
        bnkrt.clickOnDropdownAndSelectVar("assistant_manager", user.dataBankruptcyFUAssistant);
        bnkrt.clickOnDropdownAndSelectVar("court_id", user.dataBankruptcyCourt);
//        bnkrt.clickOnDropdownAndSelectVar("judge_id",user.dataBankruptcyJudge);
        bnkrt.fillInTextInputs("court_case", user.dataBankruptcyCourtCase);
        bnkrt.fillInTextInputs("date_send_in_court", user.dataBankruptcyDateSendInCourt);
        bnkrt.fillInTextInputs("date_result_court", user.dataBankruptcyDateResultCourt);
//        bnkrt.fillInTextInputs("link_publication_court_act",user.dataBankruptcyLinkPublicationCourtAct);
        bnkrt.fillInTextAreas("comment_send_in_court", user.userOK);
        bnkrt.saveChanges();
    }

    @Link(name = "Ссылка в confluence", url = "https://example.com")
    @Test(description = "Формирование графика платежей", priority = 9)
    public void BankruptcyPayments() {
        //Step 1
        bnkrt.switchDownNavigationTabTo("Оплаты");
        bnkrt.swithNavigationTabTo("График платежей");
        bnkrt.fillInTextInputs("payment_period_sum", user.dataBankruptcyPaymentSum);
        bnkrt.clickOnDropdownAndSelectVar("payment_period_type", user.dataBankruptcyPaymentPeriod);
        bnkrt.fillInTextInputs("payment_period_date", user.dataPrimaryTaskStartDate);
        bnkrt.addSchedule();
    }

    @Link(name = "Ссылка в confluence", url = "https://example.com")
    @Test(description = "Заполнение примечаний", priority = 10)
    public void FillingTheNotices() {
        //Step 1
        bnkrt.switchDownNavigationTabTo("Примечания");
        bnkrt.fillInTextAreas("notice_message",user.userOK);
        bnkrt.clickOnDropdownAndSelectVar("priority_id",user.dataBankruptcyNoticePriority);
        bnkrt.sendNotice();
    }

    //Поиск и фильтрация ордера в банкростве
    @Link(name = "Ссылка в confluence", url = "https://example.com")
    @Test(description = "Поиск и фильтрация ордера в банкростве", priority = 11)
    public void OrderFiltrationInBankruptcyAll() throws InterruptedException {
        //Step 1
        bnkrtAll.openBankruptcyAllPage();

        //Step 2
        bnkrtAll.fillInTextInputs("basecost",user.dataPrimaryCost);
        bnkrtAll.clickOnDropdownAndSelectVar("cpo_id", user.dataBankruptcyCPO);
        bnkrtAll.search();

        Thread.sleep(2000);

        Assert.assertTrue(bnkrtAll.isSearchResultCorrect());
    }

    @Link(name = "Ссылка в confluence", url = "https://example.com")
    @Test(description = "Поиск и фильтрация ордера во всех заказах", priority = 12)
    public void BankruptcyOrderFiltrationInAllOrders() throws InterruptedException {
        //Step 1
        allordrs.openAllOrders();

        //Step 2
        allordrs.fillInTextInputs("basecost",user.dataPrimaryCost);
        allordrs.clickOnDropdownAndSelectVar("cpo_id", user.dataBankruptcyCPO);
        allordrs.fillInTheTags("Теги","выбрать все");
        allordrs.search();

        Thread.sleep(2000);

        Assert.assertTrue(allordrs.isSearchResultCorrect());
    }

    //Копирование ордера в арбитраж
    @Link(name = "Ссылка в confluence", url = "https://example.com")
    @Test(description = "Копирование ордера в арбитраж", priority = 13)
    public void MigrateToArbitration(){
        //Step 1
        driver.get(orderURL);

        //Step 2
        bnkrt.clickOnCreateCardAndSelectVar("Арбитраж");
        }
}
