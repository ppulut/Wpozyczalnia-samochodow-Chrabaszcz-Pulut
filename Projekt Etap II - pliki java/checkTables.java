import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class checkTables {
    ResultSet rsUytkownicyIsExist = null;
    ResultSet rsAdminIsExist = null;
    ResultSet rsSpisSamochodowIsExist = null;
    ResultSet rsInsertsForWypozyczeniaSamochodowPrzezKlientowIsExist = null;
    logowanieBaza conn = new logowanieBaza();
    InsertsForTablesDB insertsForTablesDB = new InsertsForTablesDB();
    Statement stmt = null;


    public void DB(){
        try {
            stmt =conn.connect(). createStatement();

            DatabaseMetaData dbm = conn.connect().getMetaData();
            stmt.executeUpdate("USE wypozyczalnia");

            rsUytkownicyIsExist = dbm.getTables(null, null, "Uzytkownicy", null);
            rsAdminIsExist = dbm.getTables(null, null, "Admin", null);
            rsSpisSamochodowIsExist = dbm.getTables(null, null, "spis_samochodow", null);
            rsInsertsForWypozyczeniaSamochodowPrzezKlientowIsExist = dbm.getTables(null, null, "wypozyczenia_samochodow_przez_klientow", null);

            if (rsUytkownicyIsExist.next()) {
                System.out.println("Uzytkownicy exists");
            } else {
                String Uzytkownik_table = "CREATE TABLE Uzytkownicy " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " Imie VARCHAR(50), " +
                        " Nazwisko VARCHAR(50), " +
                        " Login VARCHAR(20), " +
                        " Haslo VARCHAR(20), " +
                        " PRIMARY KEY ( id ))";
                stmt.executeUpdate(Uzytkownik_table);
                insertsForTablesDB.InsertsForUsers();

            }

            if (rsAdminIsExist.next()) {
                System.out.println("Admin exists");
            } else {
                String Admin_table = "CREATE TABLE Admin  " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " login VARCHAR(255), " +
                        " haslo VARCHAR(255), " +
                        " PRIMARY KEY ( id ))";
                stmt.executeUpdate(Admin_table);
                insertsForTablesDB.InsertsForAdmins();
            }

            if (rsSpisSamochodowIsExist.next()) {
                System.out.println("Spis_samochodow exists");
            } else {
                String Spis_samochodow_table = "CREATE TABLE Spis_samochodow  " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " Marka VARCHAR(255), " +
                        " Model VARCHAR(255), " +
                        " Cena INT, " +
                        " Data_wypozyczenia date, " +
                        " Data_zwrotu date, " +
                        " Dostepny varchar(1), " +
                        " PRIMARY KEY ( id ))";
                stmt.executeUpdate(Spis_samochodow_table);
                insertsForTablesDB.InsertsForSpisSamochodow();
            }

            if (rsInsertsForWypozyczeniaSamochodowPrzezKlientowIsExist.next()) {
                System.out.println("wypozyczenia_samochodow_przez_klientow exists");
            } else {
                String Wypozyczenia_samochodow_przez_klientow_table = "CREATE TABLE Wypozyczenia_samochodow_przez_klientow  " +
                        "(id INTEGER not NULL AUTO_INCREMENT, " +
                        " id_uzytkownika int references Uzytkownicy(id), " +
                        " id_samochodu int references spis_samochodow(id), " +
                        " PRIMARY KEY ( id ))";
                stmt.executeUpdate(Wypozyczenia_samochodow_przez_klientow_table);
                insertsForTablesDB.InsertsForSpisSamochodow();
            }

            conn.isClose();
            stmt.close();

        }catch (SQLException ex) {

            ex.printStackTrace();

        }

    }

}
