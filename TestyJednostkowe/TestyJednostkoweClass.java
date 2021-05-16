package TestyJednostkowe;

import DataBase.ConnectDataBase;
import Server.ServerClasses.*;
import SharedUserAdminClasses.DateInformation;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.*;

class LoginTest{
    HandleLogTables handleLogTables = new HandleLogTables();

    //Poprawne logowanie Uzytkownika
    @Test
    public void loginClientUserCorrectData() throws SQLException {

        String[] tokens = {null,"UserTest","UserTest1","user"};
        String[] tokens2 = StringUtils.split(handleLogTables.handleLogin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Poprawne logowanie administratora
    @Test
    public void loginClientAdminCorrectData() throws SQLException {

        String[] tokens = {null,"AdminTest","AdminTest1","admin"};
        String[] tokens2 = StringUtils.split(handleLogTables.handleLogin(tokens));

        assertEquals("ok",tokens2[0]);

    }
    //Proba logogwania uzytkownika gdy podamy niepoprawne dane
    @Test
    public void loginClientIncorrecData() throws SQLException {

        String[] tokens = {null,"usera","userb","user"};

        String[] tokens2 = StringUtils.split(handleLogTables.handleLogin(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Proba logowania gdy podamy zbyt malo danych
    @Test
    public void loginClientLittleInformation() throws SQLException {

        String[] tokens = {"usera","userb","user"};

        String[] tokens2 = StringUtils.split(handleLogTables.handleLogin(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Proba logowania gdy podamy zbyt malo danych
    @Test
    public void loginClientWithoutType() throws SQLException {

        String[] tokens = {null,"usera","userb"};

        String[] tokens2 = StringUtils.split(handleLogTables.handleLogin(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Poprawne tworzenie tabeli dla kolekcji samochodow
    @Test
    public void createTableCorrect() throws SQLException, IOException {
        String[] tokens = {null,"spis"};
        assertNotNull(handleLogTables.table(tokens));
    }


    //Proba tworzenia taeli gdy podamy niepoprawny typ
    @Test
    public void createTableIncorrect() throws SQLException, IOException {
        String[] tokens = {null,"niepoprawna_wartosc"};
          assertNull(handleLogTables.table(tokens));

    }


}


//Testowanie polaczenia z baza danych
class Database{
    ConnectDataBase connectDataBase = new ConnectDataBase();

    @Test
    public void correctConnectionToDatabase() {
        assertEquals(connectDataBase.connect(), connectDataBase.getConnection());
    }
}


class InsertsTest{

    InsertsClientData insertsClientData = new InsertsClientData();
    ClientDataInformation clientDataInformation = new ClientDataInformation();

    //Poprawne dodanie rezerwacji
    @Test
    public void insertReservationCorrect() throws SQLException, ParseException {

        DateInformation dateInformation = new DateInformation();
        String date = dateInformation.getPatternDate().format(dateInformation.getActuallDate());

        String[] tokens = {null,"1","1",date,date,"300"};

        String[] tokens2 = StringUtils.split(insertsClientData.insertReservation(tokens));

        assertEquals("ok",tokens2[0]);

    }

    //Proba dodania rezerwacji bez wymaganych informacji

    @Test
    public void insertReservationIncorrectLittleInformation() throws SQLException, ParseException {
 
        String[] tokens = {"1","1","2021-05-08","2021-05-09"};

        String[] tokens2 = StringUtils.split(insertsClientData.insertReservation(tokens));

        assertEquals("error",tokens2[0]);

    }
    //Proba dodania rezerwacji do niestniejacego samochodu

    @Test
    public void insertReservationWrongType() throws SQLException, ParseException {
 
        String[] tokens = {"Nie_istotne","1","25","2021-05-08","2021-05-09","1500"};

        String[] tokens2 = StringUtils.split(insertsClientData.insertReservation(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Proba dodania rezerwacji do niestniejacego uzytkownika
    @Test
    public void insertReservationWrongUser() throws SQLException, ParseException {
 
        String[] tokens = {null,"20","1","2021-05-08","2021-05-09","1500"};

        String[] tokens2 = StringUtils.split(insertsClientData.insertReservation(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Proba dodania rezerwacji z niepoprawna iloscia gotowki
    @Test
    public void insertReservationWrongMoney() throws SQLException, ParseException {
 
        String[] tokens = {null,"1","1","2021-05-08","2021-05-09","1500222"};

        String[] tokens2 = StringUtils.split(insertsClientData.insertReservation(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Proba dodania rezerwacji z niepoprawna data
    @Test
    public void insertReservationWrongDate() throws SQLException, ParseException {
 
        String[] tokens = {null,"1","1","2021-04-08","2021-05-09","1500"};

        String[] tokens2 = StringUtils.split(insertsClientData.insertReservation(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Poprawna rejestracja uzytkownika
    @Test
    public void insertRegisterUserCorrect() throws SQLException, ParseException {
 
        String[] tokens = {null,"user","Konrad","chrabaszcz","user123","Password123"};

        String[] tokens2 = StringUtils.split(insertsClientData.handleRegister(tokens));

        assertEquals("ok",tokens2[0]);

    }

    //Poprawna rejestracja admina
    @Test
    public void insertRegisterAdminCorrect() throws SQLException {
 
        String[] tokens = {null,"admin","Pawel","Pulut","admin123","Password123"};

        String[] tokens2 = StringUtils.split(insertsClientData.handleRegister(tokens));

        assertEquals("ok",tokens2[0]);

    }

    //Niepoprawne haslo admina
    @Test
    public void insertRegisterWrongAdminPassword() throws SQLException {
 
        String[] tokens = {null,"admin","Pawel","Pulut","admin123","password123"};

        String[] tokens2 = StringUtils.split(insertsClientData.handleRegister(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Niepoprawne haslo uzytkownika
    @Test
    public void insertRegisterWrongUserPassword() throws SQLException {
 
        String[] tokens = {null,"user","Konrad","Chrabaszcz","user123","password123"};

        String[] tokens2 = StringUtils.split(insertsClientData.handleRegister(tokens));

        assertEquals("error",tokens2[0]);

    }


    //Niepoprawny login
    @Test
    public void insertRegisterUserWrongLogin() throws SQLException {
 
        String[] tokens = {null,"user","Konrad","Chrabaszcz","us","Password123"};

        String[] tokens2 = StringUtils.split(insertsClientData.handleRegister(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Niepoprawna liczba informacji <= 5
    @Test
    public void insertRegisterLittleInformation() throws SQLException {
 
        String[] tokens = {"user","Konrad","Chrabaszcz","user123","Password123"};
        System.out.println(tokens.length);

        String[] tokens2 = StringUtils.split(insertsClientData.handleRegister(tokens));

        assertEquals("error",tokens2[0]);
    }


    //Proba splacenia dlugu gy mamy zbyt malo danych
    @Test
    public void insertPayDebtLittleInformation() throws SQLException {
 
        String[] tokens = {null,"100"};

        String[] tokens2 = StringUtils.split(insertsClientData.handlePayDebt(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Poprawne dodanie salda
    @Test
    public void insertModifySaldoCorrect() throws SQLException {
 
        String[] tokens = {null,"1","200"};

        String[] tokens2 = StringUtils.split(insertsClientData.handleModifySaldo(tokens));

        assertEquals("ok",tokens2[0]);
    }


    //Proba dodania salda do niestniejacego klienta
    @Test
    public void insertModifySaldoWrongUser() throws SQLException {
 
        String[] tokens = {null,"100","2000"};
        String[] tokens2 = StringUtils.split(insertsClientData.handleModifySaldo(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba dodania salda gdy podamy zbyt malo informacji
    @Test
    public void insertModifySaldoLittleInfomration() throws SQLException {
 
        String[] tokens = {null,"100"};
        String[] tokens2 = StringUtils.split(insertsClientData.handleModifySaldo(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Poprawne dodanie samochodu po stronie admina
    @Test
    public void insertAdminSideCarCorrect() throws SQLException, ParseException {
 
        String[] tokens = {null,"car","TestMarka","TestModel","555","N"};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Proba dodania samochodu gdy podamy zbyt malo danych
    @Test
    public void insertAdminSideCarLittleInformation() throws SQLException, ParseException {
 
        String[] tokens = {"TestMarka","TestModel","555","N"};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba dodania samochodu gdy podamy niepoprawne dane
    @Test
    public void insertAdminSideCarWrongPrice() throws SQLException, ParseException {
 
        String[] tokens = {null,"car","TestMarka","TestModel","a","t"};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba dodania wartosci gdy podamy niepoprawny typ
    @Test
    public void insertAdminSideCarWrongType() throws SQLException, ParseException {
 
        String[] tokens = {null,"Zla_wartosc","TestMarka","TestModel","a","t"};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("error",tokens2[0]);
    }


    //Poprawne dodanie kary do uzytkownika 1
    @Test
    public void insertDebtCorrect() throws SQLException, ParseException {
 
        String[] tokens = {null,"fee","1","222","2021-05-11",null};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("ok",tokens2[0]);
    }


    //Proba dodania kary gdy jest za malo danych
    @Test
    public void insertDebtLittleInformation() throws SQLException, ParseException {
 
        String[] tokens = {"fee","1","222","2021-05-11"};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba dodania kary do nieistniejacego uzytkownika
    @Test
    public void insertDebtWrongUser() throws SQLException, ParseException {
 
        String[] tokens = {null,"fee","55","222","2021-05-11",null};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("error",tokens2[0]);
    }


    //Proba dodania kary do z niepoprawna data
    @Test
    public void insertDebtWrongDate() throws SQLException, ParseException {
 
        String[] tokens = {null,"fee","55","222","2021-02-11",null};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba dodania kary do z niepoprawna iloscia pieniedzy
    @Test
    public void insertDebtWrongMoney() throws SQLException, ParseException {
 
        String[] tokens = {null,"fee","55","niepoprawna_wartosc","2021-02-11",null};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Poprawne odswiezenie danych
    @Test
    public void handleRefreshCorrect() throws SQLException {
          

        String[] tokens = {null,"1"};
        String[] tokens2 = StringUtils.split(clientDataInformation.handleRefreshData(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Proba odsiwezenia danych dla nie istniejacego uzytkownika
    @Test
    public void handleRefreshWrongUser() throws SQLException {
          

        String[] tokens = {null,"100"};
        String[] tokens2 = StringUtils.split(clientDataInformation.handleRefreshData(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba odsiwezenia danych gdy podamy zbyt malo danych
    @Test
    public void handleRefreshLittleInformation() throws SQLException {
          

        String[] tokens = {"100"};
        String[] tokens2 = StringUtils.split(clientDataInformation.handleRefreshData(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Poprawne zwrocenie liczby dostepnych samochodow w wypozyczalnii
    @Test
    public void handleNumberCarsDstCorrect() throws SQLException {
          

        String[] tokens = {null,"dst"};
        String[] tokens2 = StringUtils.split(clientDataInformation.handleNumberCars(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Poprawne zwrocenie liczby niedostepnych samochodow w wypozyczalnii
    @Test
    public void handleNumberCarsNdstCorrect() throws SQLException {
          

        String[] tokens = {null,"ndst"};
        String[] tokens2 = StringUtils.split(clientDataInformation.handleNumberCars(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Proba zwrocenia liczby samochodow w wypozyczalnii gdy podamy zbyt malo danych
    @Test
    public void handleNumberCarsLittleInformation() throws SQLException {
          

        String[] tokens = {"ndst"};
        String[] tokens2 = StringUtils.split(clientDataInformation.handleNumberCars(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba zwrocenia liczby samochodow w wypozyczalnii gdy podamy niepoprawny typ
    @Test
    public void handleNumberCarsWrongType() throws SQLException {
          

        String[] tokens = {null,"niepoprawny_typ"};
        String[] tokens2 = StringUtils.split(clientDataInformation.handleNumberCars(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Poprawna walidacja poprawnego loginu
    @Test
    public void checkUniqueLoginTrue() throws SQLException {
          

        String[] tokens = {null,"user","UserTestTrue"};
        String[] tokens2 = StringUtils.split(clientDataInformation.checkUniqueLogin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Niepoprawna walidacja loginu
    @Test
    public void checkUniqueLoginIncorrect() throws SQLException {
          

        String[] tokens = {null,"user","UserTest"};
        String[] tokens2 = StringUtils.split(clientDataInformation.checkUniqueLogin(tokens));

        assertEquals("error",tokens2[0]);
    }

}


class DeleteTest{
    DeteleClientData deteleClientData = new DeteleClientData();

    //Poprawne usuniecie uzytkownika
    @Test
    public void deleteUserCorrect() throws SQLException {
           
        String[] tokens = {null,"user","2"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Poprawne usuniecie Admina
    @Test
    public void deleteAdminCorrect() throws SQLException {
           
        String[] tokens = {null,"admin","2"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Poprawne usuniecie samochodu
    @Test
    public void deleteCarCorrect() throws SQLException {
           
        String[] tokens = {null,"car","21"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Poprawne usuniecie wypozyczenia
    @Test
    public void deleteRentCorrect() throws SQLException {
           
        String[] tokens = {null,"rent","1"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }


    @Test
    public void deleteDebtCorrect() throws SQLException {
           
        String[] tokens = {null,"fee","1"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Proba usuniecia gdy podamy zbyt malo danych
    @Test
    public void deleteLittleInformation() throws SQLException {
           
        String[] tokens = {"fee","1"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba usuniecia uzytkownika gdy takowy nie istnieje
    @Test
    public void deleteUserWrongUser() throws SQLException {
           
        String[] tokens = {null,"user","20"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba usuniecia Admina gdy takowy nie istnieje
    @Test
    public void deleteAdminWrongAdmin() throws SQLException {
           
        String[] tokens = {null,"admin","20"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba usuniecia Samochodu gdy takowy nie istnieje
    @Test
    public void deleteCarWrongCar() throws SQLException {
           
        String[] tokens = {null,"car","21"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba usuniecia Kary gdy takowwa nie istnieje
    @Test
    public void deleteDebtWrongDebt() throws SQLException {
           
        String[] tokens = {null,"fee","20"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }
}

class EditValuesClass{

    ManipulateClientData manipulateClientData = new ManipulateClientData();

    //Poprawna edycja konta administratora
    @Test
    public void editAdminCorrect() throws SQLException, ParseException {

        String[] tokens = {null,"admin","1","Konrad","Chrabaszcz","AdminTest12","AdminTest12"};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //proba edycji konta administratora gdy administrator nie istnieje
    @Test
    public void editAdminWrongAdmin() throws SQLException, ParseException {

        String[] tokens = {null,"admin","100","Konrad","Chrabaszcz","AdminTest12","AdminTest12"};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }


    //Poprawna edycja konta uzytkownika
    @Test
    public void editUserCorrect() throws SQLException, ParseException {

        String[] tokens = {null,"user","1","Konrad","Chrabaszcz","UserTest12","UserTest12"};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Proba edycji konta uzytkownika,gdy podany uzytkownik nie istnieje
    @Test
    public void editUserIncorrect() throws SQLException, ParseException {

        String[] tokens = {null,"user","111","Konrad","Chrabaszcz","UserTest12","UserTest12"};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }


    //Poprawna edycja samochodu
    @Test
    public void editCarCorrect() throws SQLException, ParseException {

        String[] tokens = {null,"car","1","Ford","Fiesta","100","T"};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Proba edycji samochodu, gdgy podany samochod nie istnieje
    @Test
    public void editCarWrongCar() throws SQLException, ParseException {

        String[] tokens = {null,"car","111","Ford","Fiesta","100","T"};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Poprawna edycja kary
    @Test
    public void editDebtCorrect() throws SQLException, ParseException {
        DateInformation dateInformation = new DateInformation();

        String date = dateInformation.getPatternDate().format(dateInformation.getActuallDate());

        String[] tokens = {null,"debt","1","1","555",date,null};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Proba edycji kary,gdy podana kara nie istnieje
    @Test
    public void editDebtWrongDebt() throws SQLException, ParseException {

        DateInformation dateInformation = new DateInformation();

        String date = dateInformation.getPatternDate().format(dateInformation.getActuallDate());

        String[] tokens = {null,"debt","111","1","555",date,null};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba edycji kary,gdy podany uzytkownik nie istnieje
    @Test
    public void editDebtWrongUser() throws SQLException, ParseException {

        DateInformation dateInformation = new DateInformation();

        String date = dateInformation.getPatternDate().format(dateInformation.getActuallDate());

        String[] tokens = {null,"debt","1","111","555",date,null};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba edycji gdy podamy zbyt malo danych
    @Test
    public void editLittleInformation() throws SQLException, ParseException {

        DateInformation dateInformation = new DateInformation();

        String date = dateInformation.getPatternDate().format(dateInformation.getActuallDate());

        String[] tokens = {"debt","1","1","555",date};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }
}

