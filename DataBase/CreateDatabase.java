package DataBase;

import Server.ServerClasses.ReturnedMessage;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase extends ReturnedMessage {

    public boolean CreateDatabaseStandard() throws SQLException {
        ConnectDataBase conn = new ConnectDataBase();
        Statement stmt;
        ResultSet rs;
        String dbName = "wypozyczalnia";
        InsertsForTablesDB insertsForTablesDB = new InsertsForTablesDB();

            rs = conn.connect().getMetaData().getCatalogs();

        while (rs.next()) {
                String catalogs = rs.getString(1);
                if (dbName.equals(catalogs)) {
                    return true;
                }
            }

                stmt = conn.connect().createStatement();
                String sqlCreate = "CREATE DATABASE " + dbName + "";
                stmt.executeUpdate(sqlCreate);
                stmt.executeUpdate("USE " + dbName + "");

                String userTable = "CREATE TABLE Uzytkownicy " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " Imie VARCHAR(50), " +
                        " Nazwisko VARCHAR(50), " +
                        " Login VARCHAR(20), " +
                        " Haslo VARCHAR(20), " +
                        " Saldo double(6,2), " +
                        " PRIMARY KEY ( id ))";

                String adminTable = "CREATE TABLE Admin  " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " Imie VARCHAR(50), " +
                        " Nazwisko VARCHAR(50), " +
                        " Login VARCHAR(20), " +
                        " Haslo VARCHAR(20), " +
                        " PRIMARY KEY ( id ))";

                String numberOfCars = "CREATE TABLE Spis_samochodow  " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " Marka VARCHAR(255), " +
                        " Model VARCHAR(255), " +
                        " Cena INT, " +
                        " Dostepny varchar(1), " +
                        " PRIMARY KEY ( id ))";

                String rentsCarTable = "CREATE TABLE Wypozyczenia_samochodow_przez_klientow  " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " id_uzytkownika int references Uzytkownicy(id), " +
                        " id_samochodu int references spis_samochodow(id), " +
                        " Data_wypozyczenia date, " +
                        " Data_oddania date, " +
                        " PRIMARY KEY ( id ))";

                String karyTable = "CREATE TABLE kary  " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " Klient_id int, " +
                        " Kwota double(7,2), " +
                        " Data date, " +
                        " CONSTRAINT fk_data FOREIGN KEY(Klient_id) REFERENCES uzytkownicy(id), "+
                        " PRIMARY KEY ( id ))";

                stmt.executeUpdate(userTable);
                stmt.executeUpdate(adminTable);
                stmt.executeUpdate(numberOfCars);
                stmt.executeUpdate(rentsCarTable);
                stmt.executeUpdate(karyTable);

                boolean checkUserInsert = insertsForTablesDB.InsertsForUsers();
                boolean checkAdminInsert = insertsForTablesDB.InsertsForAdmins();
                boolean checkCarsInsert = insertsForTablesDB.insertCarsTable();
                boolean checkRentsInsert = insertsForTablesDB.insertCarsRent();
                boolean checkKaryInsert = insertsForTablesDB.insertKaryTable();

                DatabaseMetaData dbm = conn.connect().getMetaData();


                ResultSet  checkUserRs = dbm.getTables(null, null, "Uzytkownicy", null);
                ResultSet  checkAdmnRs = dbm.getTables(null, null, "Admin", null);
                ResultSet  checkCarsRs = dbm.getTables(null, null, "spis_samochodow", null);
                ResultSet  chceckCarsRentRs = dbm.getTables(null, null, "wypozyczenia_samochodow_przez_klientow", null);
                ResultSet  checkKaryRs = dbm.getTables(null, null, "Kary", null);

                boolean checkUserTable = checkUserRs.next();
                boolean checkAdmnTable = checkAdmnRs.next();
                boolean checkCarsTable = checkCarsRs.next();
                boolean chceckCarsRentTable = chceckCarsRentRs.next();
                boolean checkKaryTable = checkKaryRs.next();

        if(checkAdminInsert && checkUserInsert && checkCarsInsert && checkRentsInsert && checkKaryInsert && checkUserTable && super.checkClose(conn)
                && checkAdmnTable && checkCarsTable && chceckCarsRentTable && checkKaryTable){
            return true;
        }else {
            return false;
        }

        }

    }




