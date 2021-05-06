package DataBase;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckTables {
    //Obiekt na polaczenie z baza danych
    private final ConnectDataBase conn = new ConnectDataBase();
    //obiekt na inserty do poszczegolnych tabel
    private final InsertsForTablesDB insertsForTablesDB = new InsertsForTablesDB();

    public void checkDatabase(){
        try {
            //Inicjacja statmentu
            Statement stmt = conn.connect().createStatement();

            DatabaseMetaData dbm = conn.connect().getMetaData();

            stmt.executeUpdate("USE wypozyczalnia");

            //ResultSet sprawdzający czy zadane tabele istnieją
            ResultSet checkUser = dbm.getTables(null, null, "Uzytkownicy", null);
            ResultSet checkAdmn = dbm.getTables(null, null, "Admin", null);
            ResultSet checkCars = dbm.getTables(null, null, "spis_samochodow", null);
            ResultSet chceckCarsRent = dbm.getTables(null, null, "wypozyczenia_samochodow_przez_klientow", null);
            ResultSet checkKary = dbm.getTables(null, null, "Kary", null);

            if (checkUser.next()) {
                System.out.println("Table Uzytkownicy exists");
            } else {
                String userTable = "CREATE TABLE Uzytkownicy " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " Imie VARCHAR(50), " +
                        " Nazwisko VARCHAR(50), " +
                        " Login VARCHAR(20), " +
                        " Haslo VARCHAR(20), " +
                        " Saldo double(6,2), " +
                        " PRIMARY KEY ( id ))";
                stmt.executeUpdate(userTable);
                insertsForTablesDB.InsertsForUsers();

            }

            if (checkAdmn.next()) {
                System.out.println("Admin exists");
            } else {
                String Admin_table = "CREATE TABLE Admin  " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " Imie VARCHAR(50), " +
                        " Nazwisko VARCHAR(50), " +
                        " Login VARCHAR(20), " +
                        " Haslo VARCHAR(20), " +
                        " PRIMARY KEY ( id ))";
                stmt.executeUpdate(Admin_table);
                insertsForTablesDB.InsertsForAdmins();
            }

            if (checkCars.next()) {
                System.out.println("Table Spis_samochodow exists");
            } else {
                String carsTable = "CREATE TABLE Spis_samochodow  " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " Marka VARCHAR(255), " +
                        " Model VARCHAR(255), " +
                        " Cena INT, " +
                        " Data_wypozyczenia date, " +
                        " Data_zwrotu date, " +
                        " Dostepny varchar(1), " +
                        " PRIMARY KEY ( id ))";
                stmt.executeUpdate(carsTable);
                insertsForTablesDB.insertCarsTable();
            }

            if (chceckCarsRent.next()) {
                System.out.println("Table wypozyczenia_samochodow_przez_klientow exists");
            } else {
                String rentCars = "CREATE TABLE Wypozyczenia_samochodow_przez_klientow  " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " id_uzytkownika int references Uzytkownicy(id), " +
                        " id_samochodu int references spis_samochodow(id), " +
                        " PRIMARY KEY ( id ))";
                stmt.executeUpdate(rentCars);
                insertsForTablesDB.insertCarsTable();
            }

            if (checkKary.next()) {
                System.out.println("Table Kary exists");
            } else {
                String karyTable = "CREATE TABLE kary  " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " Klient_id int REFERENCES uzytkownicy(id), " +
                        " Kwota int, " +
                        " Data date, " +
                        " PRIMARY KEY ( id ))";
                stmt.executeUpdate(karyTable);
                insertsForTablesDB.insertKaryTable();

            }

            if(!conn.closeDatabase()){
                System.err.println("Blad podczas rozłączania z bazą danych");
            }

            if(!stmt.isClosed()) {
                stmt.close();
            }

            if(!checkKary.isClosed() && !checkAdmn.isClosed() && !checkCars.isClosed() && !checkUser.isClosed()){
                checkKary.close();
                checkAdmn.close();
                checkCars.close();
                checkUser.close();
            }

        }catch (SQLException ex) {

            ex.printStackTrace();

        }

    }

}
