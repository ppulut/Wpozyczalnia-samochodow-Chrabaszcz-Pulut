package Server.ServerClasses;

import DataBase.ConnectDataBase;
import Server.ValidateServerData.ValidateAdminData;
import SharedServerClientClasses.ValidateAddOtherData;
import SharedServerClientClasses.ValidateDate;
import SharedServerClientClasses.ValidateRegisterData;

import java.io.PrintStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class InsertsClientData extends ReturnedMessage {

    PrintStream ps;
    ValidateAdminData validateAdminData = new ValidateAdminData();
    ValidateDate validateDate = new ValidateDate();
    ValidateAddOtherData validateAddOtherData = new ValidateAddOtherData();

    private static final ConnectDataBase conn = new ConnectDataBase();

    public InsertsClientData(PrintStream ps){
       this.ps = ps;
    }

    public InsertsClientData() {}

    //Metoda dodająca Rezerwacje
    public String insertReservation(String[] tokens) throws SQLException, ParseException {
        //Zmienne przechowujace informacje o wypozyczeniu
        if (tokens.length == 6) {
            String userId = tokens[1];
            String carId = tokens[2];
            String firstDate = tokens[3];
            String secondDate = tokens[4];
            String sFinalCost = tokens[5];

            if(!validateAdminData.checkUserExist(userId)
                    || !validateAdminData.checkCarExist(carId) || !validateAdminData.checkIdSize(userId) || !validateAdminData.checkIdSize(carId)
                            || !validateDate.validateDate("user",firstDate) || !validateDate.validateDate("user",secondDate) || !validateAddOtherData.validePrice(sFinalCost))  {
                     return super.errorStr;
            }else{
                //Stworzenie polaczenia  z baza
                Statement stmt = conn.connect().createStatement();
                stmt.executeUpdate("USE wypozyczalnia");

                //Dodaje nowa rezerwacje do bazy danych
                String insertReservation = "Insert into wypozyczenia_samochodow_przez_klientow(id_uzytkownika,id_samochodu,data_wypozyczenia,data_oddania)"
                        + "VALUES('" + userId + "','" + carId + "','" + firstDate + "','" + secondDate + "')";

                String updateCar = "Update spis_samochodow SET dostepny = 'N' WHERE id = '" + carId + "'";
                String modifySaldo = "Update uzytkownicy set saldo = saldo - '" + sFinalCost + "' where id = '" + userId + "'";

                //Dodanie rezerwacji do bazy
                int rows = stmt.executeUpdate(insertReservation);
                int rowsUpdate = stmt.executeUpdate(updateCar);
                int rowsModifySaldo = stmt.executeUpdate(modifySaldo);

                //Zamkniecie polaczen z bazą
                if(!super.checkClose(conn)){
                    return super.errorStr;
                }

                //Obsluga bledow do zadanego widoku z bazy
                if (rows > 0 && rowsUpdate > 0 && rowsModifySaldo > 0) {
                    return super.correctStr;
                } else {
                    return super.errorStr;
                }
            }
        } else {
            return super.errorStr;
        }
    }

    public String handleRegister(String[] tokens) throws SQLException {
        if (tokens.length == 6) {
            ValidateRegisterData validateRegisterData = new ValidateRegisterData();
         //   ClientDataInformation clientDataInformation = new ClientDataInformation();
            String type = tokens[1];
            String name = tokens[2];
            String surrname = tokens[3];
            String login = tokens[4];
            String password = tokens[5];
            String saldo = "0";
            String insertRegiser;

            if(validateRegisterData.validateClientData(password, login, name, surrname)){
                return super.errorStr;
            }else {

                Statement stmt = conn.connect().createStatement();
                stmt.executeUpdate("USE wypozyczalnia");

                if (type.equals("user")) {
                    insertRegiser = "INSERT INTO  uzytkownicy(imie,nazwisko,login,haslo,Saldo) " +
                            "VALUES('" + name + "','" + surrname + "','" + login + "','" + password + "','" + saldo + "')";

                } else if (type.equals("admin")) {

                    insertRegiser = "INSERT INTO  admin(imie,nazwisko,login,haslo) " +
                            "VALUES('" + name + "','" + surrname + "','" + login + "','" + password + "')";
                } else {
                    return super.errorStr;
                }

                int rows = stmt.executeUpdate(insertRegiser);

                //Zamkniecie polaczen z bazą
                if(!super.checkClose(conn)){
                    return super.errorStr;
                }

                if (rows > 0) {
                    return super.correctStr;

                } else {
                    return super.errorStr;
                }
            }
        } else {
            return super.errorStr;
        }
    }

    public String handlePayDebt(String[] tokens) throws SQLException {
        if (tokens.length == 3) {
            String userId = tokens[1];
            String debt = tokens[2];

            if (!validateAdminData.checkUserExist(userId) || !validateAddOtherData.validePrice(debt) || !validateAdminData.checkIdSize(userId)) {
                return super.errorStr;
            } else {

                Statement stmt = conn.connect().createStatement();
                stmt.executeUpdate("USE wypozyczalnia");

                String sql = "delete from kary where Klient_id = '" + userId + "'";
                String minusSaldo = "UPDATE uzytkownicy SET saldo = saldo - '" + debt + "' where id = '" + userId + "'";

                int rows = stmt.executeUpdate(sql);
                int rowsMinusSaldo = stmt.executeUpdate(minusSaldo);


                if(!super.checkClose(conn)){
                    return super.errorStr;
                }

                if (rows > 0 && rowsMinusSaldo > 0) {
                    return super.correctStr;
                } else {
                    return super.errorStr;
                }
            }
        }
        return super.errorStr;
    }

    //Metoda obslugujaca dodawanie i usuwanie salda
    public String handleModifySaldo(String[] tokens) throws SQLException {
        if (tokens.length == 3) {

            String idKleint = tokens[1];
            String sPrice = tokens[2];

            if (!validateAdminData.checkUserExist(idKleint) || !validateAddOtherData.validePrice(sPrice) || !validateAdminData.checkIdSize(idKleint)) {
                return super.errorStr;
            } else {

                Statement stmt = conn.connect().createStatement();
                stmt.executeUpdate("USE wypozyczalnia");

                String addSaldo = "UPDATE uzytkownicy SET saldo = saldo + '" + sPrice + "' where id = '" + idKleint + "'";

                int rows = stmt.executeUpdate(addSaldo);

                if(!super.checkClose(conn)){
                    return super.errorStr;
                }

                if (rows > 0) {
                    return super.correctStr;
                } else {
                    return super.errorStr;
                }
            }
        } else {
            return super.errorStr;
        }

    }

    //Metoda dodająca nowy pojazd oraz kare
    public String insertNewData(String[] tokens) throws SQLException, ParseException {
        //Zmienne przechowujace informacje o wypozyczeniu
        if (tokens.length == 6) {
            String type = tokens[1];
            String insert_NewCar;

            int rows;

            ValidateAddOtherData validateAddOtherData = new ValidateAddOtherData();
            Statement stmt = conn.connect().createStatement();
            stmt.executeUpdate("USE wypozyczalnia");

            if (type.equalsIgnoreCase("car")) {
                String value1 = tokens[2];
                String value2 = tokens[3];
                String value3 = tokens[4];
                String value4 = tokens[5];

                if (!validateAddOtherData.validateNewCarData(value1, value2, value3, value4)) {
                    return super.errorStr;
                } else {
                    //Zmienna przechowujaca żadanie okreslonego widoku
                    insert_NewCar = "Insert into spis_samochodow(Marka,Model,Cena,Dostepny)"
                            + "VALUES('" + value1 + "','" + value2 + "','" + value3 + "','" + value4 + "')";
                }
            } else if (type.equalsIgnoreCase("fee")) {

                String value1 = tokens[2];
                String value2 = tokens[3];
                String value3 = tokens[4];

                if (!validateAdminData.checkUserExist(value1) || !validateAdminData.checkIdSize(value1) || !validateDate.validateDate("admin", value3) || !validateAddOtherData.validateNewDebtData(value1, value2)) {
                    return errorStr;
                } else {
                    insert_NewCar = "Insert into kary(Klient_id,Kwota,Data)" + "VALUES('" + value1 + "','" + value2 + "','" + value3 + "')";
                }
            } else {
                if(!super.checkClose(conn)){
                    return super.errorStr;
                }
                return errorStr;
            }

            //Żadanie okreslonego widoku z bazy
            rows = stmt.executeUpdate(insert_NewCar);

            if(!super.checkClose(conn)){
                return super.errorStr;
            }
            //Obsluga bledow do zadanego widoku z bazy
            if (rows > 0) {
                return super.correctStr;
            } else {
                return super.errorStr;
            }
        }else{
            return super.errorStr;
        }

    }
}
