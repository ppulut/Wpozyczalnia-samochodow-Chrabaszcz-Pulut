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
    public void loginClientTrue() throws SQLException {

        String[] tokens = {null,"UserTest","UserTest1","user"};
        String[] tokens2 = StringUtils.split(handleLogTables.handleLogin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Poprawne logowanie administratora
    @Test
    public void loginClientTrue2() throws SQLException {

        String[] tokens = {null,"AdminTest","AdminTest1","admin"};
        String[] tokens2 = StringUtils.split(handleLogTables.handleLogin(tokens));

        assertEquals("ok",tokens2[0]);

    }
    //Proba logogwania uzytkownika gdy podamy niepoprawne dane
    @Test
    public void loginClientFalse() throws SQLException {

        String[] tokens = {null,"usera","userb","user"};

        String[] tokens2 = StringUtils.split(handleLogTables.handleLogin(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Proba logowania gdy podamy zbyt malo danych
    @Test
    public void loginClientFalse2() throws SQLException {

        String[] tokens = {"usera","userb","user"};

        String[] tokens2 = StringUtils.split(handleLogTables.handleLogin(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Proba logowania gdy podamy zbyt malo danych
    @Test
    public void loginClientFalse3() throws SQLException {

        String[] tokens = {null,"usera","userb"};

        String[] tokens2 = StringUtils.split(handleLogTables.handleLogin(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Poprawne tworzenie tabeli dla kolekcji samochodow
    @Test
    public void createTableTrue() throws SQLException, IOException {
        String[] tokens = {null,"spis"};
        assertNotNull(handleLogTables.table(tokens));
    }


    //Proba tworzenia taeli gdy podamy niepoprawny typ
    @Test
    public void createTableFalse() throws SQLException, IOException {
        String[] tokens = {null,"niepoprawna_wartosc"};
          assertNull(handleLogTables.table(tokens));

    }


}


//Testowanie polaczenia z baza danych
class Database{
    ConnectDataBase connectDataBase = new ConnectDataBase();

    @Test
    public void loginClientFalse3() {
        assertEquals(connectDataBase.connect(), connectDataBase.getConnection());
    }
}


class InsertsTest{

    InsertsClientData insertsClientData = new InsertsClientData();
    ClientDataInformation clientDataInformation = new ClientDataInformation();

    //Poprawne dodanie rezerwacji
    @Test
    public void insertReservationTrue() throws SQLException, ParseException {
 
        String[] tokens = {null,"1","1","2021-05-12","2021-05-12","300"};

        String[] tokens2 = StringUtils.split(insertsClientData.insertReservation(tokens));

        assertEquals("ok",tokens2[0]);

    }

    //Proba dodania rezerwacji bez wymaganych informacji

    @Test
    public void insertReservationFalse() throws SQLException, ParseException {
 
        String[] tokens = {"1","1","2021-05-08","2021-05-09"};

        String[] tokens2 = StringUtils.split(insertsClientData.insertReservation(tokens));

        assertEquals("error",tokens2[0]);

    }
    //Proba dodania rezerwacji do niestniejacego samochodu

    @Test
    public void insertReservationFalse2() throws SQLException, ParseException {
 
        String[] tokens = {"Nie_istotne","1","25","2021-05-08","2021-05-09","1500"};

        String[] tokens2 = StringUtils.split(insertsClientData.insertReservation(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Proba dodania rezerwacji do niestniejacego uzytkownika
    @Test
    public void insertReservationFalse3() throws SQLException, ParseException {
 
        String[] tokens = {null,"20","1","2021-05-08","2021-05-09","1500"};

        String[] tokens2 = StringUtils.split(insertsClientData.insertReservation(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Proba dodania rezerwacji z niepoprawna iloscia gotowki
    @Test
    public void insertReservationFalse4() throws SQLException, ParseException {
 
        String[] tokens = {null,"1","1","2021-05-08","2021-05-09","1500222"};

        String[] tokens2 = StringUtils.split(insertsClientData.insertReservation(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Proba dodania rezerwacji z niepoprawna data
    @Test
    public void insertReservationFalse5() throws SQLException, ParseException {
 
        String[] tokens = {null,"1","1","2021-04-08","2021-05-09","1500"};

        String[] tokens2 = StringUtils.split(insertsClientData.insertReservation(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Poprawna rejestracja uzytkownika
    @Test
    public void insertRegisterTrue() throws SQLException, ParseException {
 
        String[] tokens = {null,"user","Konrad","chrabaszcz","user123","Password123"};

        String[] tokens2 = StringUtils.split(insertsClientData.handleRegister(tokens));

        assertEquals("ok",tokens2[0]);

    }

    //Poprawna rejestracja admina
    @Test
    public void insertRegisterTrue2() throws SQLException {
 
        String[] tokens = {null,"admin","Pawel","Pulut","admin123","Password123"};

        String[] tokens2 = StringUtils.split(insertsClientData.handleRegister(tokens));

        assertEquals("ok",tokens2[0]);

    }

    //Niepoprawne haslo admina
    @Test
    public void insertRegisterFalse() throws SQLException {
 
        String[] tokens = {null,"admin","Pawel","Pulut","admin123","password123"};

        String[] tokens2 = StringUtils.split(insertsClientData.handleRegister(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Niepoprawne haslo uzytkownika
    @Test
    public void insertRegisterFalse2() throws SQLException {
 
        String[] tokens = {null,"user","Konrad","Chrabaszcz","user123","password123"};

        String[] tokens2 = StringUtils.split(insertsClientData.handleRegister(tokens));

        assertEquals("error",tokens2[0]);

    }


    //Niepoprawny login
    @Test
    public void insertRegisterFalse3() throws SQLException {
 
        String[] tokens = {null,"user","Konrad","Chrabaszcz","us","Password123"};

        String[] tokens2 = StringUtils.split(insertsClientData.handleRegister(tokens));

        assertEquals("error",tokens2[0]);

    }

    //Niepoprawna liczba informacji <= 5
    @Test
    public void insertRegisterFalse4() throws SQLException {
 
        String[] tokens = {"user","Konrad","Chrabaszcz","user123","Password123"};
        System.out.println(tokens.length);

        String[] tokens2 = StringUtils.split(insertsClientData.handleRegister(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Poprawne splacenie dlugu jezeli uzytkownik go posiada
    //Proba splacenia dlugu dla nieistniejacego uzytkownika
    @Test
    public void insertPayDebtFalse() throws SQLException {
 
        String[] tokens = {null,"100","200"};

        String[] tokens2 = StringUtils.split(insertsClientData.handlePayDebt(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba splacenia dlugu gy mamy zbyt malo danych
    @Test
    public void insertPayDebtFalse2() throws SQLException {
 
        String[] tokens = {null,"100"};

        String[] tokens2 = StringUtils.split(insertsClientData.handlePayDebt(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Poprawne dodanie salda
    @Test
    public void insertModifySaldoTrue() throws SQLException {
 
        String[] tokens = {null,"1","200"};

        String[] tokens2 = StringUtils.split(insertsClientData.handleModifySaldo(tokens));

        assertEquals("ok",tokens2[0]);
    }


    //Proba dodania salda do niestniejacego klienta
    @Test
    public void insertModifySaldoFalse() throws SQLException {
 
        String[] tokens = {null,"100","2000"};
        String[] tokens2 = StringUtils.split(insertsClientData.handleModifySaldo(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba dodania salda gdy podamy zbyt malo informacji
    @Test
    public void insertModifySaldoFalse2() throws SQLException {
 
        String[] tokens = {null,"100"};
        String[] tokens2 = StringUtils.split(insertsClientData.handleModifySaldo(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Poprawne dodanie samochodu po stronie admina
    @Test
    public void insertAdminSideCarTrue() throws SQLException, ParseException {
 
        String[] tokens = {null,"car","TestMarka","TestModel","555","N"};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Proba dodania samochodu gdy podamy zbyt malo danych
    @Test
    public void insertAdminSideCarFalse() throws SQLException, ParseException {
 
        String[] tokens = {"TestMarka","TestModel","555","N"};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba dodania samochodu gdy niepoprawne dane
    @Test
    public void insertAdminSideCarFalse2() throws SQLException, ParseException {
 
        String[] tokens = {null,"car","TestMarka","TestModel","a","t"};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba dodania wartosci gdy podamy niepoprawny typ
    @Test
    public void insertAdminSideCarFalse3() throws SQLException, ParseException {
 
        String[] tokens = {null,"Zla_wartosc","TestMarka","TestModel","a","t"};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("error",tokens2[0]);
    }


    //Poprawne dodanie kary do uzytkownika 1
    @Test
    public void insertDebtTrue() throws SQLException, ParseException {
 
        String[] tokens = {null,"fee","1","222","2021-05-11",null};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("ok",tokens2[0]);
    }


    //Proba dodania kary gdgy jest za malo danych
    @Test
    public void insertDebtFalse() throws SQLException, ParseException {
 
        String[] tokens = {"fee","1","222","2021-05-11"};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba dodania kary do nieistniejacego uzytkownika
    @Test
    public void insertDebtFalse2() throws SQLException, ParseException {
 
        String[] tokens = {null,"fee","55","222","2021-05-11",null};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("error",tokens2[0]);
    }


    //Proba dodania kary do z niepoprawna data
    @Test
    public void insertDebtFalse3() throws SQLException, ParseException {
 
        String[] tokens = {null,"fee","55","222","2021-02-11",null};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba dodania kary do z niepoprawna iloscia pieniedzy
    @Test
    public void insertDebtFalse4() throws SQLException, ParseException {
 
        String[] tokens = {null,"fee","55","niepoprawna_wartosc","2021-02-11",null};
        String[] tokens2 = StringUtils.split(insertsClientData.insertNewData(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Poprawne odswiezenie danych
    @Test
    public void handleRefreshTrue() throws SQLException {
          

        String[] tokens = {null,"1"};
        String[] tokens2 = StringUtils.split(clientDataInformation.handleRefreshData(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Proba odsiwezenia danych dla nie istniejacego uzytkownika
    @Test
    public void handleRefreshFalse() throws SQLException {
          

        String[] tokens = {null,"100"};
        String[] tokens2 = StringUtils.split(clientDataInformation.handleRefreshData(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba odsiwezenia danych gdy podamy zbyt malo danych
    @Test
    public void handleRefreshFalse2() throws SQLException {
          

        String[] tokens = {"100"};
        String[] tokens2 = StringUtils.split(clientDataInformation.handleRefreshData(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Poprawne zwrocenie liczby dostepnych samochodow w wypozyczalnii
    @Test
    public void handleNumberCarsTrue() throws SQLException {
          

        String[] tokens = {null,"dst"};
        String[] tokens2 = StringUtils.split(clientDataInformation.handleNumberCars(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Poprawne zwrocenie liczby niedostepnych samochodow w wypozyczalnii
    @Test
    public void handleNumberCarsTrue2() throws SQLException {
          

        String[] tokens = {null,"ndst"};
        String[] tokens2 = StringUtils.split(clientDataInformation.handleNumberCars(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Proba zwrocenia liczby samochodow w wypozyczalnii ggdy podamy zbyt malo danych
    @Test
    public void handleNumberCarsFalse() throws SQLException {
          

        String[] tokens = {"ndst"};
        String[] tokens2 = StringUtils.split(clientDataInformation.handleNumberCars(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba zwrocenia liczby samochodow w wypozyczalnii ggdy podamy niepoprawny typ
    @Test
    public void handleNumberCarsFalse2() throws SQLException {
          

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
    public void checkUniqueLoginFalse() throws SQLException {
          

        String[] tokens = {null,"user","UserTest"};
        String[] tokens2 = StringUtils.split(clientDataInformation.checkUniqueLogin(tokens));

        assertEquals("error",tokens2[0]);
    }

}


class DeleteTest{
    DeteleClientData deteleClientData = new DeteleClientData();

    //Poprawne usuniecie uzytkownika
    @Test
    public void deleteUserTrue() throws SQLException {
           
        String[] tokens = {null,"user","2"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Poprawne usuniecie uzytkownika
    @Test
    public void deleteAdminTrue() throws SQLException {
           
        String[] tokens = {null,"admin","2"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Poprawne usuniecie samochodu
    @Test
    public void deleteCarTrue() throws SQLException {
           
        String[] tokens = {null,"car","21"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Poprawne usuniecie wypozyczenia
    @Test
    public void deleteRentTrue() throws SQLException {
           
        String[] tokens = {null,"rent","1"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }


    @Test
    public void deleteDebtTrue() throws SQLException {
           
        String[] tokens = {null,"fee","1"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Proba usuniecia gdy podamy zbyt malo danych
    @Test
    public void deleteFalse() throws SQLException {
           
        String[] tokens = {"fee","1"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba usuniecia uzytkownika gdy takowy nie istnieje
    @Test
    public void deleteUserFalse() throws SQLException {
           
        String[] tokens = {null,"user","20"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba usuniecia Admina gdy takowy nie istnieje
    @Test
    public void deleteAdminFalse() throws SQLException {
           
        String[] tokens = {null,"admin","20"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba usuniecia Samochodu gdy takowy nie istnieje
    @Test
    public void deleteCarFalse() throws SQLException {
           
        String[] tokens = {null,"car","20"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba usuniecia Kary gdy takowwa nie istnieje
    @Test
    public void deleteDebtFalse() throws SQLException {
           
        String[] tokens = {null,"fee","20"};
        String[] tokens2 = StringUtils.split(deteleClientData.deleteAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }
}

class EditValuesClass{

    ManipulateClientData manipulateClientData = new ManipulateClientData();

    //Poprawna edycja konta administratora
    @Test
    public void editAdminTrue() throws SQLException, ParseException {

        String[] tokens = {null,"admin","1","Konrad","Chrabaszcz","AdminTest12","AdminTest12"};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //prba edycji konta administratora gdy administrator nie istnieje
    @Test
    public void editAdminFalse() throws SQLException, ParseException {

        String[] tokens = {null,"admin","100","Konrad","Chrabaszcz","AdminTest12","AdminTest12"};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }


    //Poprawna edycja konta uzytkownika
    @Test
    public void editUserTrue() throws SQLException, ParseException {

        String[] tokens = {null,"user","1","Konrad","Chrabaszcz","UserTest12","UserTest12"};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Proba edycji konta uzytkownika,gdy podany uzytkownik nie istnieje
    @Test
    public void editUserFalse() throws SQLException, ParseException {

        String[] tokens = {null,"user","111","Konrad","Chrabaszcz","UserTest12","UserTest12"};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }


    //Poprawna edycja samochodu
    @Test
    public void editCarTrue() throws SQLException, ParseException {

        String[] tokens = {null,"car","1","Ford","Fiesta","100","T"};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Proba edycji samochodu, gdgy podany samochod nie istnieje
    @Test
    public void editCarFalse() throws SQLException, ParseException {

        String[] tokens = {null,"car","111","Ford","Fiesta","100","T"};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Poprawna edycja kary
    @Test
    public void editDebtTrue() throws SQLException, ParseException {
        DateInformation dateInformation = new DateInformation();

        String date = dateInformation.getPatternDate().format(dateInformation.getActuallDate());

        String[] tokens = {null,"debt","1","1","555",date,null};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("ok",tokens2[0]);
    }

    //Proba edycji kary,gdy podana kara nie istnieje
    @Test
    public void editDebtFalse() throws SQLException, ParseException {

        DateInformation dateInformation = new DateInformation();

        String date = dateInformation.getPatternDate().format(dateInformation.getActuallDate());

        String[] tokens = {null,"debt","111","1","555",date,null};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba edycji kary,gdy podany uzytkownik nie istnieje
    @Test
    public void editDebtFalse2() throws SQLException, ParseException {

        DateInformation dateInformation = new DateInformation();

        String date = dateInformation.getPatternDate().format(dateInformation.getActuallDate());

        String[] tokens = {null,"debt","1","111","555",date,null};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }

    //Proba edycji gdy podamy zbyt malo danych
    @Test
    public void editFalse() throws SQLException, ParseException {

        DateInformation dateInformation = new DateInformation();

        String date = dateInformation.getPatternDate().format(dateInformation.getActuallDate());

        String[] tokens = {"debt","1","1","555",date};
        String[] tokens2 = StringUtils.split(manipulateClientData.editAdmin(tokens));

        assertEquals("error",tokens2[0]);
    }
}

