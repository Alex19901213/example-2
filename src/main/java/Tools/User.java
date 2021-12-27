package Tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class User {
    private String sysPath = "src/config/UsersConfig";
    private YamlReader yamlReader = new YamlReader();

    //Wrong
    public String getWrongPassword = "12345677";

    //User
    public  String getUserLogin = yamlReader.sysGetObj(sysPath, "User", "login");
    public  String getUserPassword = yamlReader.sysGetObj(sysPath, "User", "password");

    //Admin
    public  String getAdminLogin = yamlReader.sysGetObj(sysPath, "Admin", "login");
    public  String getAdminPassword = yamlReader.sysGetObj(sysPath, "Admin", "password");

    //User Info
    public String userOK = "ОК";
    public String dataOffice = "Казань";
    public String dataFirstName = "Анна";
    public String dataLastName = "Каренина";
    public String dataThirdName = "Аркадьевна";
    public String dataBirthDate = "02051996";
    public String dataPhone = "9848448484";
    public String dataPassportSerial = "1234";
    public String dataPassportNumber = "123456";
    public String dataPassportIssuedBy = "Паспорт выдан 02.05.2010";
    public String dataPassportIssuedDate = "02052010";
    public String dataINN = "123456789012";
    public String dataSNILS = "12345678901";
    public String dataPFR = "123456789012";
    public String dataBirthPlace = "Санкт-Петербург";
    public String dataRegistrationAdress = "Санкт-Петербург";
    public String dataEmail = "a.adaktilos@riverstart.ru";

    //Primary data
    public String dataPrimaryCost = "100000";
    public String dataPrimaryRegion = "Казанский";
    public String dataPrimaryCompany = "Современная защита";
    public String dataPrimaryLawyer = "Admin";
    public String dataPrimaryDisplayStatus = "загрузка";
    public String dataPrimaryContractDate = getCurrentDate();
    public String dataPrimaryCommonArrears = "100";

    //Primary Task
    public String dataPrimaryTaskTaskName = "Изменить жизнь Каренины Анны к лучшему.";
    public String dataPrimaryTasklPriority = "Средний";
    public String dataPrimaryTaskNotifyTime = "Без";
    public String dataPrimaryTaskStartDate = getCurrentDate();
    public String dataPrimaryTaskStartTime = "12:00";
    public String dataPrimaryTaskEndDate = "15.10.2022";
    public String dataPrimaryTaskEndTime = "17:00";

    //Primary Consultation
    public String dataPrimaryConsultationTimeOfDate = "13:30";
    public String dataPrimaryConsultationType = "Первичная";

    //Bankruptcy Task
    public String dataBankruptcyPaymentStartDate = getCurrentDate();
    public String dataBankruptcyPaymentStartTime = "12:00";
    public String dataBankruptcyPaymentEndDate = "15.10.2022";
    public String dataBankruptcyPaymentEndTime = "17:00";
    public String dataBankruptcyDocumentsStartDate = getCurrentDate();
    public String dataBankruptcyDocumentsStartTime = "12:00";
    public String dataBankruptcyDocumentsEndDate = "15.10.2022";
    public String dataBankruptcyDocumentsEndTime = "17:00";

    //Bankruptcy Data
    public String dataBankruptcyDifficulty = "5";
    public String dataBankruptcyCPO = "МСОПАУ";
    public String dataBankruptcyFUAssistant = "test1";
    public String dataBankruptcyCourt = "Архангельской";
    public String dataBankruptcyJudge = "Зенькова";
    public String dataBankruptcyCourtCase = "13";
    public String dataBankruptcyDateSendInCourt = getCurrentDate();
    public String dataBankruptcyDateResultCourt = "15.10.2022";
    public String dataBankruptcyLinkPublicationCourtAct = "https://crm.sz.dev.riverstart.ru/";
    public String dataBankruptcyPaymentSum = "100000";
    public String dataBankruptcyPaymentPeriod = "10";
    public String dataBankruptcyNoticePriority = "Средний";

    private String getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter= new SimpleDateFormat("dd.MM.yyyy");

        return formatter.format(calendar.getTime());
    }
}
