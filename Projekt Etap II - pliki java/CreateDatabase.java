import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase{


    public CreateDatabase() throws SQLException {
        logowanieBaza conn = new logowanieBaza();
        Statement stmt;
        ResultSet rs = null;
        String dbName = "wypozyczalnia";
        System.out.println("siema");
        InsertsForTablesDB insertsForTablesDB = new InsertsForTablesDB();


        boolean exists = false;


            rs = conn.connect().getMetaData().getCatalogs();

            while (rs.next()) {
                String catalogs = rs.getString(1);
                if (dbName.equals(catalogs)) {
                    exists = true;
                    checkTables checkTables = new checkTables();
                    checkTables.DB();

                }
            }




            if (exists == false) {
                stmt = conn.connect().createStatement();
                String sqlCreate = "CREATE DATABASE " + dbName + "";
                stmt.executeUpdate(sqlCreate);
                stmt.executeUpdate("USE " + dbName + "");

                String Uzytkownik_table = "CREATE TABLE Uzytkownicy " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " Imie VARCHAR(50), " +
                        " Nazwisko VARCHAR(50), " +
                        " Login VARCHAR(20), " +
                        " Haslo VARCHAR(20), " +
                        " PRIMARY KEY ( id ))";

                String Admin_table = "CREATE TABLE Admin  " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " Imie VARCHAR(50), " +
                        " Nazwisko VARCHAR(50), " +
                        " Login VARCHAR(20), " +
                        " Haslo VARCHAR(20), " +
                        " PRIMARY KEY ( id ))";

                String Spis_samochodow_table = "CREATE TABLE Spis_samochodow  " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " Marka VARCHAR(255), " +
                        " Model VARCHAR(255), " +
                        " Cena INT, " +
                        " Data_wypozyczenia date, " +
                        " Data_zwrotu date, " +
                        " Dostepny varchar(1), " +
                        " PRIMARY KEY ( id ))";

                String Wypozyczenia_samochodow_przez_klientow_table = "CREATE TABLE Wypozyczenia_samochodow_przez_klientow  " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " id_uzytkownika int references Uzytkownicy(id), " +
                        " id_samochodu int references spis_samochodow(id), " +
                        " PRIMARY KEY ( id ))";


                stmt.executeUpdate(Uzytkownik_table);
                insertsForTablesDB.InsertsForUsers();
                stmt.executeUpdate(Admin_table);
                insertsForTablesDB.InsertsForAdmins();
                stmt.executeUpdate(Spis_samochodow_table);
                insertsForTablesDB.InsertsForSpisSamochodow();
                stmt.executeUpdate(Wypozyczenia_samochodow_przez_klientow_table);
                insertsForTablesDB.InsertsForWypozyczeniaSamochodowPrzezKleintow();

                stmt.close();
                conn.isClose();


            }

        }

    }




